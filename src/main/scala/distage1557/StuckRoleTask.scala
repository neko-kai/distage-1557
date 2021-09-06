package distage1557

import cats.effect.IO
import distage.plugins.PluginDef
import fs2.kafka.KafkaProducer
import izumi.distage.model.definition.Id
import izumi.distage.roles.model.definition.RoleModuleDef
import izumi.distage.roles.model.{RoleDescriptor, RoleTask}
import izumi.fundamentals.platform.cli.model.raw.RawEntrypointParams
import org.typelevel.log4cats.Logger

class StuckRoleTask(
    producer: KafkaProducer[IO, String, String],
    @Id("stuck-role-logger") logger: Logger[IO]
) extends RoleTask[IO] {
  override def start(
      roleParameters: RawEntrypointParams,
      freeArgs: Vector[String]
  ): IO[Unit] =
    for {
      _ <- logger.info("Stuck role started")
      _ <- logger.info("Stuck role finished")
    } yield ()
}

object StuckRoleTask extends RoleDescriptor {
  override def id: String = "stuck-role"

  object Plugin extends PluginDef {
    include(
      new RoleModuleDef {
        makeRole[StuckRoleTask]
      }
    )
  }
}
