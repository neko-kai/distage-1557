package distage1557

import cats.Applicative
import cats.effect.Sync
import cats.effect.Resource
import cats.syntax.applicative._

class DummyService[F[_]: Applicative] {
  def doSomething: F[Unit] = ().pure
}

object DummyService {
  def apply[F[_]: Sync: Applicative]: Resource[F, DummyService[F]] =
    Resource.make(new DummyService[F].pure)(_ => ().pure)
}
