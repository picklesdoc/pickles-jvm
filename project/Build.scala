import sbt._
import Keys._

object HelloBuild extends Build {
    lazy val root = Project(id = "pickles-jvm", base = file(".")) aggregate(core)

    lazy val core = Project(id = "pickles-jvm-core", base = file("core"))
}