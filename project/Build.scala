import sbt._
import Keys._

object HelloBuild extends Build {
    lazy val root = Project(id = "pickles-jvm", base = file(".")) aggregate(core,json)

    lazy val core = Project(id = "pickles-jvm-core", base = file("core"))
	
	lazy val json = Project(id = "pickles-jvm-json", base = file("json")) dependsOn(core)
}