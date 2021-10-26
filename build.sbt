import sbt.Keys.{crossScalaVersions, publishArtifact}

lazy val scala2_13              = "2.13.6"
lazy val scala2_12              = "2.12.12"
lazy val supportedScalaVersions = List(scala2_12, scala2_13)

lazy val notification = (project in file("."))
  .settings(
    publish / skip := true,
    releaseIgnoreUntrackedFiles := true,
    crossScalaVersions := Nil
  ).aggregate(core, examples)

ThisBuild / version := "2.0.2"
ThisBuild / organization := "uz.scala"
ThisBuild / publishMavenStyle := true
ThisBuild / publishArtifact in Test := false
ThisBuild / pomIncludeRepository := { _ => false }
ThisBuild / publishTo in ThisBuild := {
  val nexus = "https://s01.oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases" at nexus + "service/local/staging/deploy/maven2")
}
ThisBuild / scmInfo := Some(
  ScmInfo(
    url("https://github.com/Prince951-17/notification"),
    "scm:git:https://github.com/Prince951-17/notification.git"
  )
)
ThisBuild / licenses ++= Seq("Apache 2.0" -> url("https://www.apache.org/licenses/LICENSE-2.0"))
ThisBuild / homepage := Some(url("https://github.com/Prince951-17/notification"))
ThisBuild / developers := List(
  Developer("Prince", "Maftunbek Raxmatov", "prince777_98@mail.ru", url("https://github.com/Prince951-17"))
)
ThisBuild / scalaVersion := scala2_13

lazy val core = project
  .in(file("core"))
  .settings(
    name := "notification",
    compilerOptions,
    crossScalaVersions := supportedScalaVersions,
    resolvers += Resolver.sonatypeRepo("releases"),
    libraryDependencies ++= Seq(
      "com.github.japgolly.scalajs-react" %%% "core" % "2.0.0-RC3",
      "com.github.japgolly.scalacss" %%% "ext-react" % "0.7.0"
    ),
    npmDependencies in Compile ++= Seq(
        "react" -> "17.0.2",
        "react-dom" -> "17.0.2"
    )
  )
  .enablePlugins(ScalaJSBundlerPlugin)

lazy val examples = project
  .in(file("examples"))
  .settings(
    name := "notification-examples",
    scalaJSUseMainModuleInitializer := true,
    resolvers += Resolver.sonatypeRepo("releases"),
    publish / skip := true,
    crossScalaVersions := supportedScalaVersions
  ).dependsOn(core)
  .enablePlugins(ScalaJSBundlerPlugin)


lazy val compilerOptions =
  scalacOptions ++= Seq(
    "-Xfatal-warnings", // Fail the compilation if there are any warnings.
    "-deprecation", // Emit warning and location for usages of deprecated APIs.
    "-explaintypes", // Explain type errors in more detail.
    "-feature", // Emit warning and location for usages of features that should be imported explicitly.
    "-language:higherKinds", // Allow higher-kinded types
    "-language:postfixOps", // Allow higher-kinded types
    "-language:implicitConversions", // Allow definition of implicit functions called views
    "-Ywarn-unused:implicits", // Warn if an implicit parameter is unused.
    "-Ywarn-unused:imports", // Warn if an import selector is not referenced.
    "-Ywarn-unused:locals", // Warn if a local definition is unused.
    // "-Ywarn-unused:params", // Warn if a value parameter is unused.
    "-Ywarn-unused:patvars", // Warn if a variable bound in a pattern is unused.
    "-Ywarn-unused:privates", // Warn if a private member is unused.
    "-Ywarn-value-discard" // Warn when non-Unit expression results are unused.
  ) ++ (if (scalaBinaryVersion.value.startsWith("2.12")) List("-Ypartial-unification") else Nil)

ThisBuild / description := "Scalajs & React notification (Toastr)"
ThisBuild / credentials += Credentials(Path.userHome / ".sbt" / "sonatype_credentials")