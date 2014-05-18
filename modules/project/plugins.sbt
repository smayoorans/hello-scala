import sbt._
import Keys._

addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.3.0")

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies += "com.github.siasia" %% "xsbt-web-plugin" % "0.12.0-0.2.11.1"

