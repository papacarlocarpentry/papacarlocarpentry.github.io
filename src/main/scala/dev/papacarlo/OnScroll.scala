package dev.papacarlo
import org.scalajs.dom
import org.scalajs.dom._
import org.scalajs.dom.document
import org.scalajs.dom.HTMLElement
import org.scalajs.dom.html.Canvas
import scala.collection.immutable.Range
import scala.scalajs.js.annotation.JSExportTopLevel
import cats.effect.IO

@JSExportTopLevel("OnScroll")
object OnScroll {
  var lastScrollTop = 0.0
  var header: Element = _
  
  def init: Unit = {
    header = document.querySelector(".banner")
    registerScrollListener() { scroll =>
      val scrollTop = pageScroll()
      if (scrollTop != lastScrollTop) {
        lastScrollTop = scrollTop
        if (scrollTop > 0) collapseHeader()
        else expandHeader()
      }
    }
  }
  
  def registerScrollListener()(func: Event => Unit): Unit =
    document.addEventListener("scroll", func)
  
  def collapseHeader() = header.classList.add("collapsed")
  
  def expandHeader() = header.classList.remove("collapsed")
  
  def pageScroll() = document.documentElement.scrollTop
}