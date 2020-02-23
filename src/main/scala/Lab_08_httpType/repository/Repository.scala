package Lab_08_httpType.repository

import Lab_08_httpType.model.{Job, Jobs}

import scala.concurrent.Future

trait Repository {
  def addJob(job: Job): Future[OperationResult]

  def getJob(id: Long): Future[Option[Job]]

  def getAllJob(): Future[Jobs]

  def putJob(id: Long, job: Job): Future[OperationResult]

  def deleteJob(id: Long): Future[OperationResult]
}
