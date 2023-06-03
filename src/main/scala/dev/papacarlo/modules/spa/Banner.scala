package dev.papacarlo.modules.spa

import dev.papacarlo.modules.spa.Div
import org.scalajs.dom.HTMLElement
import org.scalajs.dom.document

class Image(src: String, alt: String) extends SubSection(tag = "img") {
  override def finalise(node: HTMLElement): HTMLElement = {
    node.asInstanceOf[org.scalajs.dom.html.Image].src = src
    node.asInstanceOf[org.scalajs.dom.html.Image].alt = alt
    node
  }
}
class Banner()
    extends Tag(
      tag = "header",
      classes = Seq("banner"),
      Seq(Image(
        "/dist/banner.png",
        "Papa Carlo Carpentry logo"
      ),
      Div(
        classes = "menu-container",
        children = MenuButtons("#contact-us", (e) => {
          println("test")
        })
      ))
    ) {
  override def finalise(node: HTMLElement): HTMLElement = {
    node.setAttribute("role", "banner")
    node
  }
}

class MenuButtons(selector: String, fn: (org.scalajs.dom.Event) => Unit)
    extends Tag(
      tag = "ul",
      classes = Seq("menu-buttons"),
      children = Seq(new MenuScript("contact us", (e) => {}))
    )

class MenuScript(content: String, fn: (org.scalajs.dom.Event) => Unit)
    extends SubSection(tag = "li", classes = Seq("menu-link")) {
  override def finalise(node: HTMLElement): HTMLElement = {
    node.addEventListener("click", fn)
    node
  }
}

class MenuLink(content: String, target: String)
    extends Tag(
      tag = "li",
      classes = Seq("menu-link"),
      children = Seq(
        new Tag(tag = "a", content = Some(content))
      )
    ) {
  override def finalise(node: HTMLElement): HTMLElement = {
    val el = node.children.item(0)
    el.asInstanceOf[org.scalajs.dom.html.Link].href = target
    node
  }
}
// class MenuButton(content: String, address: String)
//     extends Tag(
//       tag = "li",
//       classes = Seq("menu-button"),
//       children = Seq(new MenuSocial(content, address))
//     )
class MenuSocial(content: String, address: String)
    extends Tag(
      tag = "a",
      content = Some(content)
    ) {
  override def finalise(node: HTMLElement): HTMLElement = {
    node.asInstanceOf[org.scalajs.dom.html.Link].href =
      f"https://${content}/${address}"
    node
  }
}
