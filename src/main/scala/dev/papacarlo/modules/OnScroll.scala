package dev.papacarlo.modules.spa
import org.scalajs.dom
import org.scalajs.dom._
import org.scalajs.dom.document
import org.scalajs.dom.HTMLElement
import org.scalajs.dom.html.Canvas
import scala.collection.immutable.Range
import scala.scalajs.js.annotation.JSExportTopLevel

@JSExportTopLevel("OnScroll")
object OnScroll extends dev.papacarlo.SiteModule {
  var noUpdate = false
  def init: Unit = {
    val root = document.querySelector("#root")

    when(root) {
      console.log("loaded")
      header = document.querySelector(".banner")
      height = header.getBoundingClientRect().height
    }

    registerScrollListener() { scroll =>
      val scrollTop = pageScroll()
      if (scrollTop > 0 && lastScrollTop == 0.0) {
        collapseHeader()
        noUpdate = true
        window.setTimeout(() => { noUpdate = false }, 900)
        lastScrollTop = scrollTop
      }
      if (noUpdate) {
        println("noupdating")
      } else if (scrollTop != lastScrollTop) {
        println("updating")
        if (scrollTop > 0) collapseHeader()
        else expandHeader()
        lastScrollTop = scrollTop
      }
    }
  }

  def when(node: Node)(fn: => Unit) = {
    val observer = new MutationObserver((e, _) => {
      if (e.apply(0).addedNodes.length > 0) {
        fn
      }
    })
    observer.observe(
      node,
      new MutationObserverInit {
        this.childList = true
      }
    )
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
