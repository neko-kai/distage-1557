package distage1557

import cats.effect.IO
import distage.plugins.PluginDef
import izumi.distage.model.definition.Id
import izumi.distage.roles.model.definition.RoleModuleDef
import izumi.distage.roles.model.{RoleDescriptor, RoleTask}
import izumi.fundamentals.platform.cli.model.raw.RawEntrypointParams
import org.typelevel.log4cats.Logger

class TerminatingRoleTask(
    svc: DummyService[IO],
    @Id("terminating-role-logger") logger: Logger[IO]
) extends RoleTask[IO] {
  override def start(
      roleParameters: RawEntrypointParams,
      freeArgs: Vector[String]
  ): IO[Unit] =
    for {
      _ <- logger.info("Terminating role started")
      _ <- logger.info("Terminating role finished")
    } yield ()
}

object TerminatingRoleTask extends RoleDescriptor {
  override def id: String = "terminating-role"

  object Plugin extends PluginDef {
    include(
      new RoleModuleDef {
        makeRole[TerminatingRoleTask]
      }
    )
  }
}
