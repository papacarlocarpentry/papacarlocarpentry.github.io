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

class HomeSection(file: Option[String] = None, children: Tag*)
      extends Card(
        file = file,
        title = Some("Home"),
        children = children: _*
      )