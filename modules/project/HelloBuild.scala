import sbt._
import Keys._
import repositories._
import com.github.siasia.PluginKeys._
import com.github.siasia.WebPlugin._
import sbt.ExclusionRule
import sbt.ScalaVersion

object HelloBuild extends Build {
  val ProjectVersion  = "1.0.1"
  val Organization    = "org.guru.mayoo"

  val ScalaVersion    = "2.10.0"
  val SpringVersion   = "3.2.3.RELEASE"
  val JettyVersion    = "8.1.13.v20130916"

  val BouncyCastle                      = "bouncycastle"                    %   "bcprov-jdk14"              % "124"
  val CommonsCodec                      = "commons-codec"                   %   "commons-codec"             % "1.5"

  val LiftJson                          = "net.liftweb"                     %%  "lift-json"                 % "2.5+"

  val SpringCore                        = "org.springframework"             %   "spring-core"               % SpringVersion
  val SpringExpression                  = "org.springframework"             %   "spring-expression"         % SpringVersion
  val SpringBeans                       = "org.springframework"             %   "spring-beans"              % SpringVersion
  val SpringAop                         = "org.springframework"             %   "spring-aop"                % SpringVersion
  val SpringContext                     = "org.springframework"             %   "spring-context"            % SpringVersion
  val SpringContextSupport              = "org.springframework"             %   "spring-context-support"    % SpringVersion
  val SpringOxm                         = "org.springframework"             %   "spring-oxm"                % SpringVersion
  val SpringWeb                         = "org.springframework"             %   "spring-web"                % SpringVersion
  val SpringMvc                         = "org.springframework"             %   "spring-webmvc"             % SpringVersion
  val SpringTest                        = "org.springframework"             %   "spring-test"               % SpringVersion

  val ServletApi                        = "javax.servlet"                   %   "javax.servlet-api"         % "3.0.1"         % "provided->default"
  val Jstl                              = "jstl"                            %   "jstl"                      % "1.2"
  val SiteMesh                          = "org.sitemesh"                    %   "sitemesh"                  % "3.0-alpha-2"

  val JodaTime                          = "joda-time"                       % "joda-time"                   % "2.1"
  val JodaTimeTags                      = "joda-time"                       % "joda-time-jsptags"           % "1.1.1"
  val JodaConvert                       = "org.joda"                        % "joda-convert"                % "1.2"

  val JettyWebAppContainer              = "org.eclipse.jetty"               %   "jetty-webapp"              % JettyVersion    % "container"
  val JettyJspContainer                 = "org.eclipse.jetty"               %   "jetty-jsp"                 % JettyVersion    % "container" excludeAll(ExclusionRule(organization = "org.slf4j"))

  val TypesafeConfig                    = "com.typesafe"                    %   "config"                    % "1.0.0"
  val TypesafeLogging                   = "com.typesafe"                    %%  "scalalogging-slf4j"        % "1.0.1"

  val jettyConf = config("container")

  val jettyPluginSettings = Seq(
    libraryDependencies ++= Seq(
      JettyWebAppContainer,
      JettyJspContainer
    ),
    port in jettyConf := 8081
  )

  val webDependencies = Seq(
    BouncyCastle,
    SpringCore,
    SpringExpression,
    SpringBeans,
    SpringAop,
    SpringContext,
    SpringContextSupport,
    SpringOxm,
    SpringWeb,
    SpringMvc,
    SpringTest,
    Jstl,
    ServletApi,
    TypesafeLogging,
    TypesafeConfig,
    SiteMesh,
    CommonsCodec,
    JodaTime,
    JodaConvert,
    JodaTimeTags
  )

  val coreDependencies = Seq(
    Jstl,
    TypesafeLogging,
    TypesafeConfig,
    CommonsCodec,
    JodaTime,
    JodaConvert,
    JodaTimeTags,
    LiftJson
  )

  val testDependencies = Seq(
    "org.scalamock"         %% "scalamock-specs2-support"     % "3.0.1"         % "test",
    "hms.specs"             %% "specs-matchers"               % "0.1.0"         % "test"
  )

  val moduleLookupConfigurations = DefaultResolver.moduleConfig

  val excludedFilesInJar: NameFilter = (s: String) => """(.*?)\.(properties|props|conf|dsl|txt|xml)$""".r.pattern.matcher(s).matches

  lazy val baseSettings = {
    Defaults.defaultSettings ++ Seq(
      version := ProjectVersion,
      organization := Organization,
      scalaVersion := ScalaVersion,
      scalacOptions += "-deprecation",
      scalacOptions += "-unchecked",
      moduleConfigurations ++= moduleLookupConfigurations,
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


  lazy val web = Project(id = "web", base = file("web"),
    settings = baseSettings ++ webSettings ++ jettyPluginSettings ++ Seq(
      name := "web",
      artifactName := {
        (config: ScalaVersion, module: ModuleID, artifact: Artifact) => "hello-scala" + "." + "war"
      },
      libraryDependencies ++= webDependencies,
      libraryDependencies ++= testDependencies
    )
  )

  lazy val core = Project(id = "core", base = file("core"),
    settings = baseSettings ++ Seq(
      name := "core",
      libraryDependencies ++= coreDependencies,
      libraryDependencies ++= testDependencies
    )
  )
}
