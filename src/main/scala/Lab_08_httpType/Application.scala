package Lab_08_httpType

import Lab_08_httpType.repository.{FailedOperation, Repository}
import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors

import scala.util.{Failure, Success}

object Application {
  def apply(repository: Repository): Behavior[Command] = Behaviors.setup{ ctx =>
    import ctx.executionContext

    Behaviors.receiveMessage{
      case AddJob(job, replyTo) =>
        repository.addJob(job).onComplete{
          case Success(value)     => replyTo ! value
          case Failure(exception) => replyTo ! FailedOperation(exception.getMessage)
        }
        Behaviors.same

      case GetJobById(id, replyTo) =>
        repository.getJob(id).foreach { job =>
          replyTo ! job
        }
        Behaviors.same

      case GetAllJobById(replyTo) =>
        repository.getAllJobById().foreach { jobs =>
          replyTo ! jobs
        }
        Behaviors.same

      case PutJobById(id, job, replyTo) =>
        repository.putJob(id, job).onComplete{
          case Success(value) => replyTo ! value
          case Failure(exception) => replyTo ! FailedOperation(exception.getMessage)
        }
        Behaviors.same

      case DeleteJobById(id, replyTo) =>
        repository.deleteJob(id).onComplete {
          case Success(value) => replyTo ! value
          case Failure(exception) => replyTo ! FailedOperation(exception.getMessage)
        }
        Behaviors.same
    }
  }
}
