package dev.papacarlo

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

object SPA {

  // val menuSections = Seq(
  //   Home,
  //   Section(
  //     file = "dist/home.json",
  //     title= "Test",
  //   ),
  //   Section(
  //     title = "What we do",
  //     SubSection(
  //       tag = "div",
  //       className = "carousel-source",
  //       (e) => {
  //         val container =
  //           SubSection(tag = "div", className = "carousel-container")
  //         val con = parse(e)(container).head
  //         for (i <- Carousel.images) {
  //           attach(con)(Section.carouselImage(i))
  //         }
  //       }
  //     )
  //   ),

  // )

  val spacer = Div(classes = Seq("spacer"))

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

  class Card(
      title: Option[String] = None,
      file: Option[String] = None,
      classes: Option[String] = None,
      children: Tag*
  ) extends Section(
        None,
        None,
        classes = Seq("centering-parent"),
        children = Section(
          title,
          file,
          classes = Seq("card"),
          children = children: _*
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

  class HomeSection(file: Option[String] = None, children: Tag*)
      extends Card(
        file = file,
        title = Some("Home"),
        children = children: _*
      )

  def init = {
    val anchor = SPA get "#root"

    val allSections = spacer +: menuSections
    for (section <- allSections) {
      section attach anchor
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
    element.innerHTML = element.innerHTML+innerHTML

}
