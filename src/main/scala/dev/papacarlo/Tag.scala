package dev.papacarlo

import dev.papacarlo.SPA
import dev.papacarlo.SPA.*
import org.scalajs.dom.HTMLElement
import org.scalajs.dom.fetch
import org.scalajs.dom.console
import scalajs.js.Thenable.Implicits.thenable2future
import scala.concurrent.Future
import Tag.Callback
import org.scalajs.dom.Node
import scalajs.js.JSON
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import concurrent.ExecutionContext.Implicits.global

object Tag {
  type Callback = Function[Node, Unit]
}
case class FileContents(
    tag: String,
    title: String,
    classes: String,
    innerHTML: String
)
abstract class Tag(
    classes: Option[String] = None,
    content: Option[String] = None,
    tag: String = ("div"),
    file: Option[String] = None,
    children: Seq[Tag] = Seq()
) {

  def classList                   = classes map (_.split(" ").toIndexedSeq)
  def finalise(node: HTMLElement) = identity(node)
  def fileContents: Option[Future[String]] = {
    for (fileString <- file) yield {
      for {
        fetched <- fetch(fileString)
        text    <- fetched.text()
      } yield text
    }
  }

  def toElement: Option[HTMLElement] = {
    Option(mkElement(tag))
  }

  def attach(anchor: Node): Future[Unit] = {
    val maybeElement = toElement
    if (maybeElement.isEmpty) {
      console.debug("element was empty")
      return Future(())
    }
    val element = maybeElement.get
    val applyClass = (el: HTMLElement) =>
      for (ifClasses <- classList) {
        ifClasses foreach { c =>
          setClass(el)(c)
        }
      }
    val applyContent = (el: HTMLElement) =>
      for (ifContent <- content) {
        setTextContent(el)(ifContent)
      }
    val applyFileText = (el: HTMLElement) =>
      for (ifFileExists <- fileContents) yield {
        for (awaitFileContent <- ifFileExists) yield {
          // val content = JSON.parse(awaitFileContent).asInstanceOf[FileContents]
          setInnerHTML(el)(awaitFileContent)
        }
      }
    val applyChildren = (el: HTMLElement) =>
      Future
        .sequence {
          for (ifChild <- children) yield ifChild.attach(el)
        }

    for {
      _ <- Future(applyClass(element))
      _ <- Future(applyContent(element))
      _ <- applyChildren(element)
      _ <- applyFileText(element) match
        case None        => Future.successful(())
        case Some(value) => value

    } yield Future(anchor.appendChild(finalise(element)))
  }
}

class HomeSection(file: Option[String] = None, children: Tag*)
    extends Tag(
      tag = ("section"),
      file = None,
      classes = Some("centering-parent"),
      children = (Title(title = "Home") +: children)
    )

class Section(
    title: Option[String] = None,
    file: Option[String] = None,
    classes: Option[String] = None,
    children: Tag*
) extends Tag(
      classes = classes,
      tag = ("section"),
      file = file,
      children =
        (title.map(t => Title(t)).map(t => t +: children).getOrElse(children))
    )

class SubSection(
    tag: String = "div",
    subTitle: Option[String] = None,
    classes: Option[String] = None,
    children: Tag*
) extends Tag(
      tag = tag,
      file = None,
      classes = classes,
      children = (subTitle
        .map(t => Title(tag = "h2", title = (subTitle), classes = None))
        .map(t => t +: children)
        .getOrElse(children))
    ) {

  def this(tag: String, classes: String) =
    this(tag = (tag), classes = Some(classes))
}

class Title(
    tag: String = "h3",
    title: Option[String],
    classes: Option[String] = None
) extends Tag(
      tag = tag,
      content = title,
      classes = classes
    ) {
  def this(title: String) = this(title = Some(title))
}

class Div(
    content: Option[String] = None,
    classes: Option[String] = None,
    children: Tag*
) extends Tag(
      tag = ("div"),
      content = content,
      classes = classes,
      children = children
    ) {
  def this(classes: String, children: Tag*) =
    this(classes = Some(classes), children = children: _*)
}
