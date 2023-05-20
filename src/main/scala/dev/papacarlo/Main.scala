package dev.papacarlo

import org.scalajs.dom
import org.scalajs.dom._
import dom.document
import scala.scalajs.js.annotation.JSExportTopLevel
import dev.papacarlo.modules.SPA
import dev.papacarlo.modules.spa.OnScroll

@JSExportTopLevel("Main")
object Main {

  def modules: Seq[SiteModule] = Seq(SPA, OnScroll)

  def main(args: Array[String]): Unit = {
    dom.document.addEventListener("DOMContentLoaded", (e: dom.Event) => init())
  }

  def init() = {
    modules foreach (_.init)
  }
}
