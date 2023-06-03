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

class Tag(
    classes: Seq[String] = Seq(),
    content: Option[String] = None,
    file: Option[String] = None,
    tag: String = ("div"),
    children: Seq[Tag] = Seq()
) {

  /**
    * Override this to work on the rendered node
    *
    * @param node this element after dom creation
    * @return this element after finalisation
    */
  def finalise(node: HTMLElement): HTMLElement = identity(node)
  def classList = classes map (_.split(" ").toIndexedSeq)
  def fileContents: Option[Future[String]] = {
    for (fileString <- file) yield {
      for {
        fetched <- fetch(fileString)
        text    <- fetched.text()
      } yield text
    }
  }

  def toElement: HTMLElement = {
    (mkElement(tag))
  }

  def compile : Future[HTMLElement] = {
    val element = toElement
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
      Future.sequence {
        for (ifChild <- children) yield {
          for(compiled <- ifChild.compile) yield { 
            el.appendChild(compiled)
          }
        }
      }

    for {
      _ <- Future(applyClass(element))
      _ <- Future(applyContent(element))
      _ <- applyChildren(element)
      _ <- applyFileText(element) match
        case None        => Future.successful(())
        case Some(value) => value

    } yield finalise(element)
  }
}
