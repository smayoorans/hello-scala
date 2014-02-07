import sbt._

package object repositories {

  val SprayReleases = "spray repo" at "http://repo.spray.io/"
  val SonatypeReleases = "sonatype releases" at "https://oss.sonatype.org/content/groups/public"
  val SonatypeSnapshots = "sonatype snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"

  sealed trait ModuleResolver extends {
    def moduleConfig: Seq[ModuleConfiguration] = Seq.empty
  }

  trait SprayResolver extends ModuleResolver {
    override def moduleConfig = super.moduleConfig ++
      Seq(ModuleConfiguration("io.spray", SprayReleases))
  }

  trait SonatypeResolver extends ModuleResolver {
    override def moduleConfig = super.moduleConfig ++ Seq(
      ModuleConfiguration("com.novus", SonatypeReleases),
      ModuleConfiguration("com.escalatesoft.subcut", SonatypeReleases)
    )
  }

  object DefaultResolver extends SprayResolver with SonatypeResolver

  object HmsWithMavenCentralOnly extends SprayResolver with SonatypeResolver

  object WorkFromHome extends ModuleResolver

}

