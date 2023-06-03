package dev.papacarlo.modules.spa.carousel

import org.scalajs.dom
import org.scalajs.dom._
import org.scalajs.dom.html.Image
import scala.scalajs.js.annotation._
import scala.util.control.NonLocalReturns.*
import dom.document
import dev.papacarlo.modules.spa.{Tag, SubSection, Div}
import dev.papacarlo.modules.spa.Carousel.*

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
