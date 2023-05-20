package dev.papacarlo

import org.scalajs.dom.Element
import org.scalajs.dom.html.Image
import org.scalajs.dom
import org.scalajs.dom.console
import org.scalajs.dom.window
import cats.effect.IO
import scala.scalajs.js.annotation.JSExportTopLevel
import org.scalajs.dom.HTMLElement
import org.scalajs.dom.document
import org.scalajs.dom.html.Anchor
import org.scalajs.dom.Event

@JSExportTopLevel("Banner")
object Banner {

//   val menu = Menu(SPA.menuSections)
//   val banner =
//     Banner("dist/banner.png", "Papa Carlo Carpentry", menu)
//   val spacer = Spacer()

//   def init = {
//     val anchor = document.querySelector("#root")
//     val page = SPAPage(
//       document.querySelector("#root"),
//       banner,
//       spacer
//     )
//     window.setTimeout(() => {
//         val spacer = document.querySelector(".spacer").asInstanceOf[HTMLElement]
//         val banner = document.querySelector(".banner")
//         val height = banner.getBoundingClientRect().height
//         spacer.style.paddingTop = f"${height}px"
//     }, 0)
//   }

//   def spacer(className: String)(func: (Event) => Unit): dom.Element = {
//     val container = SPA.element("div")
//     classList(container)(className)
//     container.onloadstart = func
//     container
//   }

//   def banner(src: String, alt: String, children: Seq[Section]): dom.Element = {
//     val container = element("div")
//     container.classList.add("banner")
//     val img = SPA.element("img").asInstanceOf[Image]
//     img.src = src
//     img.alt = alt
//     container.appendChild(img)
//     children map SPA.render foreach container.appendChild
//     container
//   }

//   def menu(sections: Seq[Section]): dom.Element = {
//     val container = element("ul")
//     classList(container)("menu-container")
//     val lines =
//       for (section <- sections) yield {
//         val lineItem = element("li").asInstanceOf[HTMLElement]
//         val link     = element("a").asInstanceOf[Anchor]
//         link.textContent = section.name
//         link.href = f"#${section.idName}"
//         lineItem.appendChild(link)
//         lineItem
//       }
//     lines foreach (container.appendChild _)
//     container
//   }
}

// case class Banner(imgFile: String, altText: String, menu: Menu)
//     extends Section(title = "Banner", menu)

// case class Spacer() extends Section() {}

// case class Menu(menuSections: Seq[Section]) extends Section(title = "Menu")
