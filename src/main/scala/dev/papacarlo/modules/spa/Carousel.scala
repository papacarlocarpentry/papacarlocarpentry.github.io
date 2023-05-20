package dev.papacarlo.modules.spa

import org.scalajs.dom
import org.scalajs.dom._
import org.scalajs.dom.html.Image

import scala.scalajs.js.annotation._
import scala.util.control.NonLocalReturns.*

import dom.document
import dev.papacarlo.modules.spa.{Tag, SubSection, Div}
import dev.papacarlo.modules.spa.carousel.*
import dev.papacarlo.modules.spa.Carousel.currentIndex

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

object Carousel {
  val imageRange                = 1 to 6
  def imageName(i: Int): String = f"/dist/carousel/carousel ($i).JPG"
  val images                    = imageRange map imageName
  var currentIndex              = 0
  lazy val numImages            = images.size
}
