package Lab_08_httpType

import Lab_08_httpType.model.{Job, Jobs}
import Lab_08_httpType.repository.OperationResult
import akka.actor.typed.ActorRef

sealed trait Command
final case class AddJob(job: Job, replyTo: ActorRef[OperationResult]) extends Command
final case class GetJobById(id: Long, replyTo: ActorRef[Option[Job]]) extends Command
final case class GetAllJob(replyTo: ActorRef[Jobs]) extends Command
final case class PutJobById(id: Long, job: Job, replyTo: ActorRef[OperationResult]) extends Command
final case class DeleteJobById(id:Long, replyTo: ActorRef[OperationResult]) extends Command