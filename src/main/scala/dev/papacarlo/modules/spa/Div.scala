package dev.papacarlo.modules.spa

import dev.papacarlo.modules.SPA
import org.scalajs.dom.HTMLElement
import org.scalajs.dom.fetch
import org.scalajs.dom.console
import scalajs.js.Thenable.Implicits.thenable2future
import scala.concurrent.Future
import org.scalajs.dom.Node
import scalajs.js.JSON
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import concurrent.ExecutionContext.Implicits.global

class Div(
    content: Option[String] = None,
    classes: Seq[String] = Seq(),
    children: Tag*
) extends Tag(
      tag = ("div"),
      content = content,
      classes = classes,
      children = children
    ) {
  def this(classes: String, children: Tag*) =
    this(classes = Seq(classes), children = children: _*)
}