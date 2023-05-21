package dev.papacarlo.modules.spa
import org.scalajs.dom
import org.scalajs.dom._
import org.scalajs.dom.document
import org.scalajs.dom.HTMLElement
import org.scalajs.dom.html.Canvas
import scala.collection.immutable.Range
import scala.scalajs.js.annotation.JSExportTopLevel
import cats.effect.IO

@JSExportTopLevel("OnScroll")
object OnScroll extends dev.papacarlo.SiteModule {

  def init: Unit = {
    dom.window.setTimeout(
      () => {
        header = document.querySelector(".banner")
        height = header.getBoundingClientRect().height
      },
      0
    )
    registerScrollListener() { scroll =>
      val scrollTop = pageScroll()
      if (scrollTop != lastScrollTop) {
        lastScrollTop = scrollTop
        if (scrollTop > 0) collapseHeader()
        else expandHeader()
      }
    }
  }

  var lastScrollTop   = 0.0
  var header: Element = _
  var height: Double  = _

  def registerScrollListener()(func: Event => Unit): Unit =
    document.addEventListener("scroll", func)

  def collapseHeader() = header.classList.add("collapsed")

  def expandHeader() = header.classList.remove("collapsed")

  def pageScroll() = document.documentElement.scrollTop

  def animateScroll() = document.documentElement.scrollTop = 0
}
