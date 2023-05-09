import java.nio.file.Files
import java.net.URL
import sbt._

name := "Papa Carlo Scala-JS"
ThisBuild / scalaVersion := "3.2.2"
ThisBuild / organization := "dev.papacarlo"
ThisBuild / organizationHomepage := Some(
  new URL("https://papacarlocarpentry.co.uk")
)

val copyFiles = taskKey[Unit]("Copy js files into resources")
val dist = taskKey[Unit]("Distribute the compiled JS sources")

lazy val build = (project in file("."))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    scalaJSUseMainModuleInitializer := true,
    libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "2.1.0",
    Compile / dist := Def
      .sequential(
        Compile / fullLinkJS,
        Compile / copyFiles
      )
      .value,
  )


copyFiles := {
  import scala.sys.process._ 
  val baseDir = baseDirectory.value.toPath
  val version = scalaVersion.value
  val from = baseDir.resolve(
    s"target/scala-${version}/papa-carlo-scala-js-opt"
  )
  val dest = baseDir.resolve("dist")

  Files
    .walk(from)
    .filter(Files.isRegularFile(_))
    .forEach(file => {
      val rel = from.relativize(file)
      val to = dest.resolve(rel)
      IO.copyFile(file.toFile, to.toFile)
      println(s"Copying ${file}")
    })
}



