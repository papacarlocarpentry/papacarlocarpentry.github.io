package dev.papacarlo

import org.scalajs.dom
import org.scalajs.dom._
import dom.document
import scala.scalajs.js.annotation._

@JSExportTopLevel("Carousel")
object Carousel {

  val carousel = document.querySelector(".carousel-container")
  val prevButton = document.querySelector("#carousel-prev")
  val nextButton = document.querySelector("#carousel-next")
  val imageWidth =
    carousel.firstElementChild.asInstanceOf[HTMLElement].offsetWidth
  console.log(carousel, prevButton, nextButton, imageWidth)
  var currentIndex = 0
  val numImages = 6

  def init(): Unit = {
    prevButton.addEventListener("click", (e: dom.MouseEvent) => movePrev(e))
    nextButton.addEventListener("click", (e: dom.MouseEvent) => moveNext(e))
  }
  
  def movePrev(e: dom.MouseEvent): Unit = {
    currentIndex = (currentIndex - 1 + numImages) % numImages
    val carouselElement = carousel.asInstanceOf[org.scalajs.dom.raw.HTMLElement]
    val slideWidth = carouselElement.getBoundingClientRect().width / numImages
    val translateX = -currentIndex * slideWidth
    carouselElement.style.transform = s"translateX(${translateX}px)"
  }

  def moveNext(e: dom.MouseEvent): Unit = {
    currentIndex = (currentIndex + 1) % numImages
    val carouselElement = carousel.asInstanceOf[org.scalajs.dom.raw.HTMLElement]
    val slideWidth = carouselElement.getBoundingClientRect().width / numImages
    val translateX = -currentIndex * slideWidth
    carouselElement.style.transform = s"translateX(${translateX}px)"
  }

}
