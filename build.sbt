ThisBuild / scalaVersion := "2.12.8"
ThisBuild / organization := "com.lastsys"
ThisBuild / organizationName := "lastsys"
ThisBuild / organizationHomepage := Some(url("https://blog.lastsys.com"))

lazy val aoc2018 = (project in file("."))
  .settings(
    name := "AOC2018",
    version := "0.1",
    scalacOptions ++= Seq(
      // Cats relies on improved type inference for Scala 2.11.9 or later (https://typelevel.org/cats/)
      "-Ypartial-unification",    // Enable partial unification in type constructor inference.
      "-Yrangepos",               // Use range positions for syntax trees.
      "-target:jvm-1.8",          // Target platform for object files.
      "-encoding", "utf8",        // Specify character encoding used by source files.
      "-unchecked",               // Enable additional warnings where generated code depends on assumptions.
      "-deprecation",             // Emit warning and location for usages of deprecated APIs.
      "-explaintypes",            // Explain type errors in more detail.
      "-Xfuture",                 // Turn on future language features.
      "-Xlint",                   // Enable or disable specific warnings: _ for all.
      //        "-Yno-adapted-args",      // Do not adapt an argument list (either by inserting () or creating a tuple) to
      // match the receiver.
      "-Ywarn-dead-code",         // Warn when dead code is identified.
      "-Ywarn-numeric-widen",     // Warn when numerics are widened.
      "-Ywarn-value-discard",     // Warn when non-Unit expression results are unused.
      "-Ywarn-unused"             // Enable or disable specific unused warnings: _ for all.
    ),
    libraryDependencies ++= Seq(
      "com.lihaoyi" %% "fastparse" % "2.1.0",
      "org.typelevel" %% "cats-core" % "1.6.0",
      "com.github.bigwheel" %% "util-backports" % "1.1",
      "org.scalatest" %% "scalatest" % "3.0.5" % Test,
      "org.scalacheck" %% "scalacheck" % "1.14.0" % Test
    ),
    // https://stackoverflow.com/a/35387923/4435991
    fork in Test := false
  )
