val common = Seq(
  version := "0.0.1-SNAPSHOT",
  scalaVersion := "2.11.7"
)

lazy val root = (project in file("."))
  .settings(common: _*)
  .settings(
    name := "scala_fun"
  )

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.6" % "test",
  "org.scalamock" %% "scalamock-scalatest-support" % "3.2.2" % "test",
  "org.mockito" % "mockito-all" % "1.8.4" % "test"
)
