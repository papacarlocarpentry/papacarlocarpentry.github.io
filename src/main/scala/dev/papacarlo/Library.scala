// package dev.papacarlo
// import org.scalajs.dom
// import org.scalajs.dom._
// import org.scalajs.dom.document
// import org.scalajs.dom.HTMLElement
// import scala.collection.immutable.Range

// object Library {
//   @main def startup(): Unit = {
//     registerScrollListener() { scroll =>
//       if (pageScroll() > 0) collapseHeader()
//       else expandHeader()
//     }
//     val files = carouselFiles("carousel", 1 to 6, "JPG")
//     Carousel.initialiseImages(files)
//     Carousel.createCarousel()
//   }

//   def carouselFiles(prefix: String, range: Range, ext: String): List[String] = {
//     range map (i => f"$prefix ($i).$ext")
//   }.toList

//   def styleCarousel(elem: Element) = {}

//   def startCarousel(elem: Element) = {}

//   def registerScrollListener()(func: Event => Unit): Unit =
//     document.addEventListener("scroll", func)

//   def collapseHeader() = getHeader().classList.add("collapsed")
//   def expandHeader() = getHeader().classList.remove("collapsed")
//   def getHeader() = document.querySelector(".banner")
//   def pageScroll() = document.documentElement.scrollTop
// }
