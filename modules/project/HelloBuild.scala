import sbt._
import Keys._
import com.github.siasia.WebPlugin._
import sbt.ExclusionRule
import sbt.ScalaVersion

object HelloBuild extends Build {
  val projectVersion        = "1.0.2-SNAPSHOT"
  val projectOrganization   = "com.guru.mayoo"

  val scalaVersions         = "2.10.0"
  val springVersion         = "4.0.3.RELEASE"
  val jettyVersion          = "8.1.13.v20130916"

  val springCore            = "org.springframework"         % "spring-core"           % springVersion
  val springWeb             = "org.springframework"         % "spring-web"            % springVersion
  val springMvc             = "org.springframework"         % "spring-webmvc"         % springVersion

  val jettyWebAppContainer  = "org.eclipse.jetty"           % "jetty-webapp"          % jettyVersion  % "container"
  val jettyJspContainer     = "org.eclipse.jetty"           % "jetty-jsp"             % jettyVersion  % "container" excludeAll ExclusionRule(organization = "org.slf4j")

  val webDependencies = Seq(
    springCore,
    springWeb,
    springMvc,
    jettyWebAppContainer,
    jettyJspContainer
  )

  lazy val baseSettings = {
    Defaults.defaultSettings ++ Seq(
      version := projectVersion,
      organization := projectOrganization,
      scalaVersion := scalaVersions,
      scalacOptions += "-deprecation",
      scalacOptions += "-unchecked",
      logBuffered := false,
      parallelExecution in Test := false,
      offline := true
    )
  }

  lazy val modules = Project(id = "hello-scala", base = file("."),
    settings = baseSettings ++ Seq(
      name := "hello-scala"
    )
  ) aggregate web


  lazy val web = Project(id = "web-module", base = file("scala-web"),
    settings = baseSettings ++ webSettings ++ Seq(
      name := "scala-web",
      artifactName := {
        (config: ScalaVersion, module: ModuleID, artifact: Artifact) => "hello-scala" + "." + "war"
      },
      libraryDependencies ++= webDependencies
    )
  )
}
