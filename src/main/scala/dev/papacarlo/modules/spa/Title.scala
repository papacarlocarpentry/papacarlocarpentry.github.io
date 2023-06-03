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

class Title(
    tag: String = "h3",
    title: Option[String],
    classes: Seq[String] = Seq()
) extends Tag(
      tag = tag,
      content = title,
      classes = classes
    ) {
  def this(title: String) = this(title = Some(title))
}