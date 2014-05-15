import sbt._
import Keys._
import com.github.siasia.PluginKeys._
import com.github.siasia.WebPlugin._
import sbt.ExclusionRule
import sbt.ScalaVersion

object HelloBuild extends Build {
  val ProjectVersion = "1.0.1-SNAPSHOT"
  val Organization = "com.guru.mayoo"

  val ScalaVersion = "2.10.0"
  val SpringVersion = "3.2.3.RELEASE"
  val JettyVersion = "8.1.13.v20130916"

  val SpringCore = "org.springframework" % "spring-core" % SpringVersion
  val SpringWeb = "org.springframework" % "spring-web" % SpringVersion
  val SpringMvc = "org.springframework" % "spring-webmvc" % SpringVersion

  val ServletApi = "javax.servlet" % "javax.servlet-api" % "3.0.1" % "provided->default"

  val JettyWebAppContainer = "org.eclipse.jetty" % "jetty-webapp" % JettyVersion % "container"
  val JettyJspContainer = "org.eclipse.jetty" % "jetty-jsp" % JettyVersion % "container" excludeAll (ExclusionRule(organization = "org.slf4j"))

  val jettyConf = config("container")

  val jettyPluginSettings = Seq(
    libraryDependencies ++= Seq(
      JettyWebAppContainer,
      JettyJspContainer
    ),
    port in jettyConf := 8081
  )

  val webDependencies = Seq(
    SpringCore,
    SpringWeb,
    SpringMvc,
    ServletApi
  )

//  val excludedFilesInJar: NameFilter = (s: String) => """(.*?)\.(properties|props|conf|dsl|txt|xml)$""".r.pattern.matcher(s).matches

  lazy val baseSettings = {
    Defaults.defaultSettings ++ Seq(
      version := ProjectVersion,
      organization := Organization,
      scalaVersion := ScalaVersion,
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
    settings = baseSettings ++ webSettings ++ jettyPluginSettings ++ Seq(
      name := "scala-web",
      artifactName := {
        (config: ScalaVersion, module: ModuleID, artifact: Artifact) => "hello-scala" + "." + "war"
      },
      libraryDependencies ++= webDependencies
    )
  )
}
