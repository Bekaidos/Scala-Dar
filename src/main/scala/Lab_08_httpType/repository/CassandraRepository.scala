package Lab_08_httpType.repository

import Lab_08_httpType.model.{Job, Jobs}
import com.outworkers.phantom.database.DatabaseProvider
import com.outworkers.phantom.dsl._

import scala.concurrent.{ExecutionContext, Future}

case class CassandraRepository()(implicit executionContext: ExecutionContext) extends DatabaseProvider[AppDatabase]
  with Repository {
  override val database: AppDatabase = new AppDatabase(CassandraConnector.cassandraConnection)

  override def addJob(job: Job): Future[OperationResult] = Future {

    database.jobsTable.create(job)
    SuccessfulOperation("Job successfully added")

  }

  override def getJob(id: Long): Future[Option[Job]] = {
    database.jobsTable.read(id)
  }

  override def getAllJob(): Future[Jobs] = {
    database.jobsTable.getAllJob
  }

  override def putJob(id: Long, job: Job): Future[OperationResult] = {

    database.jobsTable.putJob(id, job).map { resultSet =>
      SuccessfulOperation("Job successfully updated")
    }
  }

  override def deleteJob(id: Long): Future[OperationResult] = {
    database.jobsTable.deleteJob(id).map { resultSet =>
      SuccessfulOperation("Job successfully deleted")
    }
  }

}
