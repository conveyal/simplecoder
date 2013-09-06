import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "simplecoder"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    javaCore,
    javaJdbc,
    javaEbean,
    "oauth.signpost" % "oauth-signpost" % "1.2.1.2"
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here      
  )

}
