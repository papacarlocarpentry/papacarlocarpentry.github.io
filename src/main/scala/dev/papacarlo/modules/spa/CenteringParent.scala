package dev.papacarlo.modules.spa

import org.scalajs.dom._
import org.scalajs.dom.html._
import org.scalajs.dom
import org.scalajs.dom.document
import org.scalajs.dom.Element
import org.scalajs.dom.html.Script
import org.scalajs.dom.html.Image
import org.scalajs.dom.HTMLElement
import scala.concurrent.Future
import scala.Option
import dev.papacarlo.modules.spa.*
import concurrent.ExecutionContext.Implicits.global

class CenteringParent(
      title: Option[String] = None,
      file: Option[String] = None,
      children: Tag*
  ) extends Section(
        title,
        None,
        classes = Seq("centering-parent"),
        children = children: _*
      ) {
    override def finalise(node: HTMLElement): HTMLElement = {
      for (ifTitle <- title)
        node.id = ifTitle.replace(" ", "-").toLowerCase()
      node
    }
  }