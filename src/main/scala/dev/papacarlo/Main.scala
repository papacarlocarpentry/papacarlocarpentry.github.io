package dev.papacarlo

import org.scalajs.dom
import org.scalajs.dom._
import dom.document
import scala.scalajs.js.annotation.JSExportTopLevel

@JSExportTopLevel("Main")
object Main {
  def main(args: Array[String]): Unit = {
    dom.document.addEventListener("DOMContentLoaded", (e: dom.Event) => init())
  }

  def init() = {
    // Banner.init
    SPA.init
    // OnScroll.init
    // Carousel.init
  }
}
