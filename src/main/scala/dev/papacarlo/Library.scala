package dev.papacarlo
import org.scalajs.dom
import org.scalajs.dom._
import org.scalajs.dom.document
import org.scalajs.dom.HTMLElement
import scala.collection.immutable.Range
import scala.scalajs.js.annotation.JSExportTopLevel

@JSExportTopLevel("Library")
object Library {
  def init(): Unit = {
    registerScrollListener() { scroll =>
      if (pageScroll() > 0) collapseHeader()
      else expandHeader()
    }
  }

  def registerScrollListener()(func: Event => Unit): Unit =
    document.addEventListener("scroll", func)

  def collapseHeader() = getHeader().classList.add("collapsed")

  def expandHeader() = getHeader().classList.remove("collapsed")

  def getHeader() = document.querySelector(".banner")

  def pageScroll() = document.documentElement.scrollTop

}
