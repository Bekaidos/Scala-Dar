package Lab_08_httpType

import Lab_08_httpType.model.{Failed, Job, Jobs, Status, Successful}
import Lab_08_httpType.repository.{FailedOperation, SuccessfulOperation}
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.{DeserializationException, JsString, JsValue, RootJsonFormat}
import spray.json.DefaultJsonProtocol._




trait JsonSupport extends SprayJsonSupport{


  implicit object StatusFormat extends RootJsonFormat[Status] {
    def read(json: JsValue): Status = json match {
      case JsString("Failed")     => Failed
      case JsString("Successful") => Successful
      case _                      => throw new DeserializationException("Status unexpected")
    }

    def write(obj: Status): JsValue = obj match {
      case Failed     => JsString("Failed")
      case Successful => JsString("Successful")
    }
  }
  implicit val jobFormat = jsonFormat4(Job)

  implicit val jobsFormat = jsonFormat1(Jobs)

  implicit val operationSuccessFormat = jsonFormat1(SuccessfulOperation)
  implicit val failedOperationFormat = jsonFormat1(FailedOperation)

}
