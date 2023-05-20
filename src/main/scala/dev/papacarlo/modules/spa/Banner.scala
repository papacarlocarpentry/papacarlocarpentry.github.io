package dev.papacarlo.modules.spa

import dev.papacarlo.modules.spa.Div
import org.scalajs.dom.HTMLElement

class Image(src:String, alt:String) extends SubSection(tag="img"){
    override def finalise(node: HTMLElement): HTMLElement = {
        node.asInstanceOf[org.scalajs.dom.html.Image].src = src
        node.asInstanceOf[org.scalajs.dom.html.Image].alt = alt
        node
    }
}
class Banner()
    extends Div(
      classes = "banner",
      children = Image(
        "/dist/banner.png", "Papa Carlo Carpentry logo"
      )
    ) {}
