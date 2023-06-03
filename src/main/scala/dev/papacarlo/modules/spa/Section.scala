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

class Section(
    title: Option[String] = None,
    file: Option[String] = None,
    id: Option[String] = None,
    classes: Seq[String] = Seq(),
    children: Tag*
) extends Tag(
      classes = classes,
      tag = ("section"),
      file = file,
      children =
        (title.map(t => Title(t)).map(t => t +: children).getOrElse(children))
    ) {
  override def finalise(node: HTMLElement): HTMLElement = {
    for (ifId <- id) node.id = ifId
    node
  }
}
