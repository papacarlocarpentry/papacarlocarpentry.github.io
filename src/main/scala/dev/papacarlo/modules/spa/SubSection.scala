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
import SPA.*

class SubSection(
    tag: String = "div",
    subTitle: Option[String] = None,
    classes: Seq[String] = Seq(),
    children: Tag*
) extends Tag(
      tag = tag,
      file = None,
      classes = classes,
      children = (subTitle
        .map(t => Title(tag = "h2", title = (subTitle), classes = Seq()))
        .map(t => t +: children)
        .getOrElse(children))
    ) {

  def this(tag: String, classes: String) =
    this(tag = (tag), classes = Seq(classes))
  def this(tag: String) = 
    this(tag = (tag), classes = Seq())
}