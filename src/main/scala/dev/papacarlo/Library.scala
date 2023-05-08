package dev.papacarlo
import org.scalajs.dom
import org.scalajs.dom._
import org.scalajs.dom.document
import org.scalajs.dom.HTMLElement

object Library {

  def onLoad(func: Event => Unit): Unit = {
    document.addEventListener("load", func)
  }

  def createCarousel() = {
    val carouselParent = document.getElementById("carousel-source")
    console.dir(carouselParent)
    val carousel = Carousel.createCarousel(carouselParent)
    console.log("created child")
    carousel
  }

  def header() = document.querySelector(".banner")

  @main def startup(): Unit = {
    console.dir("starting up!")
    onLoad { e =>
      console.log("Hello world!")
      createCarousel()
      console.dir("created carousel")
    }
    console.log("test")
    registerScrollListener() { scroll =>
      if (pageScroll() > 0) collapseHeader()
      else expandHeader()
    }
    console.dir("registered scroll")

  }

  def registerScrollListener()(func: Event => Unit): Unit =
    document.addEventListener("scroll", func)

  def pageScroll() = document.documentElement.scrollTop

  def collapseHeader() = header().classList.add("collapsed")
  def expandHeader() = header().classList.remove("collapsed")
}
