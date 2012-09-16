import sbt._
object MyBuild extends Build {
  lazy val root = Project("root", file(".")) dependsOn(knockoff)
  lazy val knockoff = RootProject(uri("git://github.com/tristanjuricek/knockoff.git"))
}
