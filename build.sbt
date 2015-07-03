name := """PlayOne"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  javaWs,
  "org.webjars.bower" % "jquery" % "2.1.3",
  "org.webjars.bower" % "bootstrap" % "3.3.4" exclude("org.webjars.bower", "jquery"),
  "org.postgresql" % "postgresql" % "9.4-1201-jdbc41",
  javaJpa.exclude("org.hibernate.javax.persistence", "hibernate-jpa-2.0-api"),
  "org.hibernate" % "hibernate-entitymanager" % "4.3.8.Final"
)
libraryDependencies += "org.webjars" % "bootbox" % "4.2.0"

libraryDependencies += "com.google.inject" % "guice" % "4.0"


//play.Project.playJavaSettingsq
