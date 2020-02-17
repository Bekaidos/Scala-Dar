package Lab_07_http

import model.{FailedResponse, Human, HumanCreated, SuccessfulResponse}

case class HumanManager() {

  var humans: Map[Int, Human] = Map()
  var counter = 0

  def createHuman(human: Human): HumanCreated = {
    humans = humans + (counter -> human)

    val response = HumanCreated(counter)
    counter += 1
    response
  }

  def getHuman(id: Int): Either[FailedResponse, Human] = {
    humans.get(id) match {
      case Some(human) => Right(human)
      case None => Left(FailedResponse("No such human exists, he-he"))
    }
  }

  def putHuman(id: Int, human: Human): Either[FailedResponse, SuccessfulResponse] = {
    humans.get(id) match {
      case Some(value) => {
        humans = humans + (id -> human)
        Right(SuccessfulResponse(s"${value} updated"))
      }
      case None => Left(FailedResponse("No such human exists, he-he"))
    }
  }

  def deleteHuman(id: Int): Either[FailedResponse, SuccessfulResponse] = {
    humans.get(id) match {
      case Some(human) => {
        humans = humans.removed(id)
        Right(SuccessfulResponse(s"${human} removed "))
      }
      case None => Left(FailedResponse("No such human exists, he-he"))
    }
  }







}
