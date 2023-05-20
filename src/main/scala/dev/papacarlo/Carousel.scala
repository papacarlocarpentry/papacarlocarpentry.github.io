package dev.papacarlo

import org.scalajs.dom
import org.scalajs.dom.html.Image
import org.scalajs.dom._
import dom.document
import scala.scalajs.js.annotation._
import cats.effect.IO
import scala.util.control.NonLocalReturns.*
import dev.papacarlo.Carousel.currentIndex
import dev.papacarlo.Carousel.numImages

class Carousel()
    extends Div(
      classes = "centering",
      CarouselContainer(Carousel.images),
      CarouselButtons()
    ) {
  override def finalise(node: HTMLElement): HTMLElement = {
    node.id = "carousel-source"
    val carousel = node.children.apply(1)
    for (child <- carousel.children) {
      child.classList.add("hidden")
    }
    carousel.children.item(currentIndex).classList.remove("hidden")
    node
  }
}

class CarouselButton(id: String, child: Tag, fn: Function[dom.MouseEvent, Unit])
    extends Div(
      classes = None,
      children = child
    ) {
  override def finalise(node: HTMLElement): HTMLElement = {
    node.addEventListener("click", fn)
    node.id = id
    node
  }
}

class CarouselButtons()
    extends Div(
      classes = "buttons-container",
      CarouselButton(
        id = "carousel-prev",
        child = SubSection(tag = "i", classes = "fa-solid fa-caret-left"),
        fn = (e: dom.MouseEvent) => {
          val carousel = document.querySelector(".carousel-container")
          currentIndex = (currentIndex - 1 + numImages) % numImages
          val carouselElement =
            carousel.asInstanceOf[HTMLElement]
          val slideWidth =
            carouselElement.getBoundingClientRect().width / numImages
          val translateX = -currentIndex * slideWidth
          carouselElement.style.transform = s"translateX(${translateX}px)"
          for (child <- carouselElement.children) {
            child.classList.add("hidden")
          }
          carouselElement.children.item(currentIndex).classList.remove("hidden")
        }
      ),
      CarouselButton(
        id = "carousel-next",
        child = SubSection(tag = "i", classes = "fa-solid fa-caret-right"),
        fn = (e: dom.MouseEvent) => {
          val carousel = document.querySelector(".carousel-container")
          currentIndex = (currentIndex + 1) % numImages
          val carouselElement =
            carousel.asInstanceOf[HTMLElement]
          val slideWidth =
            carouselElement.getBoundingClientRect().width / numImages
          val translateX = -currentIndex * slideWidth
          carouselElement.style.transform = s"translateX(${translateX}px)"
          for (child <- carouselElement.children) {
            child.classList.add("hidden")
          }
          carouselElement.children.item(currentIndex).classList.remove("hidden")
        }
      )
    ) {}

class CarouselImage(image: String) extends Tag(tag = "img") {
  override def finalise(node: HTMLElement): HTMLElement = {
    node.asInstanceOf[Image].src = image
    node
  }
}

class CarouselImageContainer(image: String)
    extends Div(
      classes = "carousel-slide",
      children = CarouselImage(image)
    )

class CarouselContainer(images: Seq[String])
    extends Div(
      classes = "carousel-container",
      children = (images map (CarouselImageContainer(_))): _*
    )

object Carousel {
  val imageRange                = 1 to 6
  def imageName(i: Int): String = f"/dist/carousel/carousel ($i).JPG"
  val images                    = imageRange map imageName
  var currentIndex              = 0
  lazy val numImages            = images.size
}
