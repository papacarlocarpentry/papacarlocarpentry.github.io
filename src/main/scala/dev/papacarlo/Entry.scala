package dev.papacarlo

import org.scalajs.dom
import org.scalajs.dom._
import dom.document

object Entry {
  def main(args: Array[String]): Unit = {
    dom.document.addEventListener("DOMContentLoaded", (e: dom.Event) => init())
  }

  def init() = {
    Carousel.init()
    Library.init()
  }
}
