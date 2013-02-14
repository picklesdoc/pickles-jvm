name := "pickles-jvm-json"

version := "0.1"

scalaVersion := "2.10.0"

libraryDependencies ++= Seq(
	"org.scalatest" % "scalatest_2.10" % "1.9.1" % "test",
	"info.cukes" % "gherkin" % "2.11.2",
	"junit" % "junit" % "4.10",
	"commons-vfs" % "commons-vfs" % "1.0",
	"org.pegdown" % "pegdown" % "1.1.0"
)

resolvers ++= Seq(
  "snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
  "releases"  at "http://oss.sonatype.org/content/repositories/releases"
)
