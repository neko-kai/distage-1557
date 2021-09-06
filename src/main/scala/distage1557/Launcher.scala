package distage1557

import cats.effect.ContextShift
import cats.effect.IO
import distage.plugins.PluginDef
import fs2.kafka.{KafkaProducer, ProducerSettings, Serializer}
import izumi.distage.model.definition.ModuleDef
import izumi.distage.plugins.PluginConfig
import izumi.distage.roles.RoleAppMain.LauncherCats
import org.typelevel.log4cats.Logger
import org.typelevel.log4cats.slf4j.Slf4jLogger

import java.util.concurrent.Executors
import scala.concurrent.{ExecutionContext, ExecutionContextExecutor}

object Launcher extends LauncherCats[IO] {

  private implicit val ec: ExecutionContextExecutor =
    ExecutionContext.fromExecutor(
      Executors.newFixedThreadPool(10)
    )

  private implicit val contextShiftIO: ContextShift[IO] = IO.contextShift(ec)

  override protected def pluginConfig: PluginConfig =
    PluginConfig.const(
      Seq(
        TerminatingRoleTask.Plugin,
        StuckRoleTask.Plugin,
        DI
      )
    )

  object DI extends PluginDef {
    include(
      new ModuleDef {
        make[KafkaProducer[IO, String, String]].fromResource {
          KafkaProducer.resource(
            ProducerSettings(
              keySerializer = Serializer.string[IO],
              valueSerializer = Serializer.string[IO]
            ).withBootstrapServers("localhost:9092")
          )
        }

        make[DummyService[IO]].fromResource(
          DummyService[IO]
        )

        make[Logger[IO]]
          .named("terminating-role-logger")
          .fromEffect(
            Slf4jLogger.fromName[IO]("terminating-role")
          )

        make[Logger[IO]]
          .named("stuck-role-logger")
          .fromEffect(
            Slf4jLogger.fromName[IO]("stuck-role")
          )

        make[ExecutionContext].from(ec)
      }
    )
  }
}
