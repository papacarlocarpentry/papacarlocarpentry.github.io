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

class Card(
    title: Option[String] = None,
    file: Option[String] = None,
    classes: Option[String] = None,
    children: Tag*
) extends Section(
      id = title.map(_.trim.replace(" ", "-").toLowerCase()),
      classes = Seq("centering-parent"),
      children = Section(
        title,
        file,
        classes = Seq("card"),
        children = children: _*
      )
    )
