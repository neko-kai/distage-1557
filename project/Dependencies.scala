import sbt._

object Dependencies {

  private val izumiVersion = "1.0.8"
  private val catsEffectVersion = "2.5.1"
  private val fs2KafkaVersion = "1.8.0"
  private val log4CatsVersion = "1.3.1"
  private val logbackVersion = "1.2.3"

  lazy val izumi = Seq(
    "io.7mind.izumi" %% "distage-framework" % izumiVersion
  )

  lazy val catsEffect = Seq(
    "org.typelevel" %% "cats-effect" % catsEffectVersion
  )

  lazy val fs2Kafka = Seq(
    "com.github.fd4s" %% "fs2-kafka" % fs2KafkaVersion
  )

  lazy val log4Cats = Seq(
    "org.typelevel" %% "log4cats-slf4j" % log4CatsVersion
  )

  lazy val logback = Seq(
    "ch.qos.logback" % "logback-classic" % logbackVersion
  )
}
