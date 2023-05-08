package dev.papacarlo

import org.scalajs.dom._
import java.nio.file.Files
import java.nio.file.Path
import java.io.File

object Carousel {
  def createCarousel(parent: Node) = {
    val resources = "/dist/carousel/"
    console.dir(resources)

    val element = document.createElement("div")
    element.id = "carousel"
    parent.appendChild(element)
    console.log("element attached")
    element
  }
}
