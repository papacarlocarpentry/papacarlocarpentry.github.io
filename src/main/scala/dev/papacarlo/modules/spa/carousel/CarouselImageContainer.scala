package dev.papacarlo.modules.spa.carousel

import org.scalajs.dom
import org.scalajs.dom._
import org.scalajs.dom.html.Image
import scala.scalajs.js.annotation._
import scala.util.control.NonLocalReturns.*
import dom.document
import dev.papacarlo.modules.spa.{Tag, SubSection, Div}

class CarouselImageContainer(image: String)
    extends Div(
      classes = "carousel-slide",
      children = CarouselImage(image)
    )