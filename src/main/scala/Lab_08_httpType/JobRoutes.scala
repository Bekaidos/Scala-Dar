package Lab_08_httpType

import Lab_08_httpType.model.{Job, Jobs}
import Lab_08_httpType.repository.{FailedOperation, OperationResult, SuccessfulOperation}
import akka.actor.typed.{ActorRef, ActorSystem}
import akka.http.scaladsl.server.Route
import akka.util.Timeout
import akka.http.scaladsl.server.Directives._
import akka.actor.typed.scaladsl.AskPattern._
import akka.http.scaladsl.model.StatusCodes

import scala.concurrent.Future
import scala.concurrent.duration._

class JobRoutes(application: ActorRef[Command])(implicit system: ActorSystem[_]) extends JsonSupport {

  implicit val timeout: Timeout = 3.seconds

  lazy val theJobRoutes: Route =
    pathPrefix("jobs") {
      concat(
        pathEnd {
          concat(
            post {
              entity(as[Job]) { job =>
                val resp: Future[OperationResult] = application.ask(AddJob(job, _))

                onSuccess(resp) { // when future completes successfully
                  case res: SuccessfulOperation => complete(res)
                  case res: FailedOperation => complete(StatusCodes.InternalServerError -> res)
                }
              }
            },
            get {
              val maybeJob: Future[Jobs] = application.ask(GetAllJobById(_))
              rejectEmptyResponse {
                complete(maybeJob)
              }
            }
          )
        },
        path(LongNumber) { jobId =>
          concat(
            get {
              val maybeJob: Future[Option[Job]] = application.ask(GetJobById(jobId, _))
              rejectEmptyResponse {complete(maybeJob)
              }
            },

            delete {
              val response: Future[OperationResult] = application.ask(DeleteJobById(jobId, _))
              onSuccess(response) {
                case res: SuccessfulOperation => complete(res)
                case res: FailedOperation => complete(StatusCodes.InternalServerError -> res)
              }
            },

            put {
              entity(as[Job]) { job =>
                val response: Future[OperationResult] = application.ask(PutJobById(jobId, job, _))
                onSuccess(response){
                  case res: SuccessfulOperation => complete(res)
                  case res: FailedOperation => complete(StatusCodes.InternalServerError -> res)
                }
              }
            }
          )
        }

      )
}
}
