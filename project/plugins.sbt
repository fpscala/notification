logLevel := Level.Warn

addSbtPlugin("com.github.sbt" % "sbt-release"             % "1.0.15")
addSbtPlugin("org.xerial.sbt" % "sbt-sonatype"            % "3.9.5")
addSbtPlugin("com.github.sbt" % "sbt-pgp"                 % "2.1.2")
addSbtPlugin("com.vmunier"    % "sbt-web-scalajs"         % "1.2.0")
addSbtPlugin("org.scala-js"   % "sbt-scalajs"             % "1.7.0")
addSbtPlugin("ch.epfl.scala"  % "sbt-web-scalajs-bundler" % "0.20.0")
addSbtPlugin("org.scalameta"  % "sbt-scalafmt"            % "2.4.3")
