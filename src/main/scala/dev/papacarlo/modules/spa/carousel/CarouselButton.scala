package dev.papacarlo.modules.spa.carousel

import org.scalajs.dom
import org.scalajs.dom._
import org.scalajs.dom.html.Image
import scala.scalajs.js.annotation._
import scala.util.control.NonLocalReturns.*
import dom.document
import dev.papacarlo.modules.spa.{Tag, SubSection, Div}

class CarouselButton(id: String, child: Tag, fn: Function[dom.MouseEvent, Unit])
    extends Div(
      classes = Seq(),
      children = child
    ) {
  override def finalise(node: HTMLElement): HTMLElement = {
    node.addEventListener("click", fn)
    node.id = id
    node
  }
}