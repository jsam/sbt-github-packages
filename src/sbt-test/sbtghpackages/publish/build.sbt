import scala.sys.process._

val ArtifactId = "sbt-github-packages-tests-publish"

ThisBuild / organization := "com.codecommit"
ThisBuild / version := s"${sys.props("plugin.version")}"

ThisBuild / githubOwner := "djspiewak"
ThisBuild / githubRepository := "sbt-github-packages"
ThisBuild / githubTokenSource := Some(TokenSource.Environment("GITHUB_TOKEN"))

lazy val root = project.in(file("."))

lazy val publisher = project
  .in(file("publisher"))
  .settings(name := ArtifactId)

lazy val resolver = project
  .in(file("resolver"))
  .settings(
    resolvers += Resolver.githubPackagesRepo(githubOwner.value, githubRepository.value),

    libraryDependencies += "com.codecommit" %% ArtifactId % version.value)
