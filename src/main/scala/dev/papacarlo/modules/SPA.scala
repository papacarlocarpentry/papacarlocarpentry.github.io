package dev.papacarlo.modules

import org.scalajs.dom._
import org.scalajs.dom.html._
import org.scalajs.dom
import org.scalajs.dom.document
import org.scalajs.dom.Element
import org.scalajs.dom.html.Script
import org.scalajs.dom.html.Image
import org.scalajs.dom.HTMLElement
import scala.concurrent.Future
import scala.Option
import dev.papacarlo.modules.spa.*

object SPA extends dev.papacarlo.SiteModule {

  def init = {
    val anchor = SPA get "#root"

    val allSections = Seq(banner, spacer) ++ menuSections
    for (section <- allSections) {
      section attach anchor
    }
  }

  val spacer = Div(classes = Seq("spacer"))
  val banner = Banner()

  val menuSections = Seq(
    HomeSection(),
    Card(
      title = Some("What we do"),
      classes = Some("bolder"),
      file = Some("/dist/what-we-do.html")
    ),
    Card(
      title = Some("Testimonials"),
      file = Some("/dist/testimonials.html")
    ),
    Card(
      title = Some("Contact Us"),
      file = Some("/dist/contact.html")
    ),
    Card(
      title = Some("Shop"),
      children = Div(content = Some("Under construction"))
    ),
    Card(
      title = Some("Book a visit"),
      children = Div(content = Some("Under construction"))
      // file = Some("/dist/book-a-visit.html")
    ),
    Card(
      title = Some("Past Projects"),
      children = Section(
        children = Carousel()
      )
    )
  )

  class CenteringParent(
      title: Option[String] = None,
      file: Option[String] = None,
      children: Tag*
  ) extends Section(
        title,
        None,
        classes = Seq("centering-parent"),
        children = children: _*
      ) {
    override def finalise(node: HTMLElement): HTMLElement = {
      for (ifTitle <- title)
        node.id = ifTitle.replace(" ", "-").toLowerCase()
      node
    }
  }
  def get(str: String): HTMLElement = {
    document.querySelector(str).asInstanceOf[HTMLElement]
  }

  def attach(parent: Element)(children: Element*) = {
    children foreach parent.appendChild
    parent
  }

  def mkElement(name: String): HTMLElement =
    document.createElement(name).asInstanceOf[HTMLElement]

  def setClass(element: Element)(strings: String*) =
    for (string <- strings) element.classList.toggle(string)

  def setContent(element: Element)(innerHTML: String) =
    element.innerHTML = innerHTML

  def setTextContent(element: Element)(text: String) =
    element.textContent = text

  def setTitle(element: Element)(title: String, tag: String = "h3") = {
    val subElement = mkElement(tag)
    subElement.textContent = title
    element.appendChild(subElement)
  }

  def setInnerHTML(element: Element)(innerHTML: String) =
    element.innerHTML = element.innerHTML + innerHTML

}
