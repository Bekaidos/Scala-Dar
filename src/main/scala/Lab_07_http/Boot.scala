package Lab_07_http

import Lab_07_http.model.{FailedResponse, Human}
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._

import scala.concurrent.ExecutionContextExecutor

object Boot extends App with JsonSerializer {
  implicit val system: ActorSystem = ActorSystem("http-server")
  implicit val materializer: ActorMaterializer = ActorMaterializer() //materialise stream
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher

  val humanManager = HumanManager()

  val route: Route =
    concat(
      path("healthcheck") {
        get {
          complete {
            "Ok"
          }
        }
      },
      pathPrefix("humans") {
        concat(
          post {
            entity(as[Human]) { human =>
              complete{
                humanManager.createHuman(human)
              }
            }
          },
          path(Segment) { humanId =>
            get {
              complete{
                scala.Lab_05.Options.readInt(humanId) match {
                  case Some(id) => humanManager.getHuman(id)
                  case None => FailedResponse("Wrong id format")
                }
              }
            }
          },
          path(Segment) { humanId =>
            put {
              entity(as[Human]) { human =>
                complete{
                  scala.Lab_05.Options.readInt(humanId) match {
                    case Some(id) => humanManager.putHuman(id, human)
                    case None => FailedResponse("Wrong id format")
                  }
                }
              }
            }
          },
          path(Segment) { humanId =>
            delete {
              complete {
                scala.Lab_05.Options.readInt(humanId) match {
                  case Some(id) => humanManager.deleteHuman(id)
                  case None => FailedResponse("Wrong id format")
                }
              }
            }
          }
        )
      }
    )

  Http().bindAndHandle(route, "0.0.0.0", 8080)



}
