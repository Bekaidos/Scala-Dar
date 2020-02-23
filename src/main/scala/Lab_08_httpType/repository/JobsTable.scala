package Lab_08_httpType.repository

import Lab_08_httpType.model.{Job, Jobs}
import com.outworkers.phantom.dsl._
import com.outworkers.phantom.Table

import scala.concurrent.Future

abstract class JobsTable extends Table[JobsTable, Job]{
  object name_list extends SetColumn[String]
  // Database representation
  object id extends LongColumn with PartitionKey
  object projectName extends StringColumn
  object status extends StringColumn
  object duration extends LongColumn

  def create(job: Job): Future[ResultSet] =
    insert
      .value(_.id, job.id)
      .value(_.projectName, job.projectName)
      .value(_.status, job.status)
      .value(_.duration, job.duration)
      .ifNotExists() // think about this
      .future()


  def read(id: Long): Future[Option[Job]] = {
    select.where(_.id.eqs(id)).one()
  } //select.where(_.id.eqs(id)).one()

  def getAllJob: Future[Jobs] = {

    for {
      result <- select.all.paginateRecord
    } yield Jobs(result.records)
  }

  def putJob(id: Long, job: Job): Future[OperationResult] =  Future {
    update.where(_.id eqs id)
      .modify(_.projectName setTo job.projectName)
      .and(_.status setTo job.status)
      .and(_.duration setTo job.duration)
      .future()
    SuccessfulOperation(s"${job} updated")

  }

  def deleteJob(id: Long): Future[ResultSet] = {

    delete.where(_.id eqs id).future()
  }

}
