package Lab_08_httpType.repository

import Lab_08_httpType.model.{Job, Jobs}

import scala.concurrent.{ExecutionContext, Future}

 case class InMemoryRepository()(implicit ex: ExecutionContext) extends Repository {

  var jobs: List[Job] = List.empty

  override def addJob(job: Job): Future[OperationResult] = Future {
    if (jobs.exists(_.id == job.id)) {
      FailedOperation("Such job already exists")
    } else {
      jobs = jobs :+ job
      SuccessfulOperation(s"added ${job}")
    }
  }

   override def getJob(id: Long): Future[Option[Job]] = Future {
     jobs.find(_.id == id)
   }

   override def getAllJob(): Future[Jobs] = Future {
     var jobs2 = Jobs(jobs)
     jobs2
   }

   override def putJob(id: Long, job: Job): Future[OperationResult] =  Future {
     if (jobs.exists(_.id == id)) {
       if (job.id == id) {
         jobs = jobs.filter(_.id != id)
         jobs = jobs :+ job
         SuccessfulOperation(s"${job} updated")
       } else FailedOperation(" \"id\" must be the same")
     } else FailedOperation("No such id, he-he")
   }

   override def deleteJob(id: Long): Future[OperationResult] = Future {
     if(jobs.exists(_.id == id)) {
       jobs = jobs.filter(_.id != id)
       SuccessfulOperation("deleted")
     }
     else FailedOperation("No such id, he-he")
   }

 }
