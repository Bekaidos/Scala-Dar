package Lab_07_http

import model.{FailedResponse, Human, HumanCreated, SuccessfulResponse}
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

trait JsonSerializer extends DefaultJsonProtocol{
  implicit val humanCreatedFormat: RootJsonFormat[HumanCreated] = jsonFormat1(HumanCreated)
  implicit val humanFormat: RootJsonFormat[Human] = jsonFormat3(Human)
  implicit val failedResponse: RootJsonFormat[FailedResponse] = jsonFormat1(FailedResponse)
  implicit val successfulResponse: RootJsonFormat[SuccessfulResponse] =  jsonFormat1(SuccessfulResponse)
}
