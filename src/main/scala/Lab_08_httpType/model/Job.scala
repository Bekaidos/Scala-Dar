package Lab_08_httpType.model

sealed trait Status
object Successful extends Status
object Failed extends Status

final case class Job(id: Long, projectName: String, status: Status, duration: Long)
final case class Jobs(jobs: List[Job])
