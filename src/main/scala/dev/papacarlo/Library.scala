package dev.papacarlo
import org.scalajs.dom._
import org.scalajs.dom.document

object Library {

  def header() = document.querySelector(".banner")

  def main(args: Array[String]): Unit = startup()

  def startup(): Unit = {
    registerScrollListener() { scroll =>
      if (pageScroll() > 0) collapseHeader()
      else expandHeader()
    }
  }

  def registerScrollListener()(func: Event => Unit): Unit =
    document.addEventListener("scroll", func)

  def pageScroll() = document.documentElement.scrollTop

  def collapseHeader() = header().classList.add("collapsed")
  def expandHeader() = header().classList.remove("collapsed")
}
