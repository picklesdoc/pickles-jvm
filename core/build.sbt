name := "pickles-jvm"

version := "0.1"

scalaVersion := "2.9.2"

libraryDependencies ++= Seq(
	"org.scalatest" %% "scalatest" % "1.8" % "test",
	"info.cukes" % "gherkin" % "2.11.2",
	"junit" % "junit" % "4.10",
	"commons-vfs" % "commons-vfs" % "1.0",
	"org.pegdown" % "pegdown" % "1.1.0"
)
