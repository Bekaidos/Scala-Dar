package Lab_08_httpType

import akka.actor.typed.ActorSystem

object Boot extends App {
  val system: ActorSystem[Server.Message] =
    ActorSystem(Server("0.0.0.0", 8080), "job-server")
}

