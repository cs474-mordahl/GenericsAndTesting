val scala3Version = "3.6.4"

lazy val root = project
  .in(file("."))
  .settings(
    name                   := "GenericsAndTesting",
    version                := "0.1.0-SNAPSHOT",
    scalaVersion           := scala3Version,
    semanticdbEnabled      := true,
    semanticdbVersion      := scalafixSemanticdb.revision,
    Compile / doc / target := file("docs"),
    scalacOptions ++= Seq(
      "-Wunused:all"
    ),

    /* Uncomment the next two lines if you want scalafmt and scalafix to run
     * whenever your code is compiled. It is recommended to instead use format
     * on save for scalafmt and Metals' custom keybinding for scalafix. */
    // scalafmtOnCompile := true,
    // scalafixOnCompile := true,

    // Test Dependencies
    libraryDependencies += "org.scalameta" %% "munit" % "1.0.0" % Test,
    libraryDependencies += "org.scalameta" %% "munit-scalacheck" % "1.0.0" % Test
  )
