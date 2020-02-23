package Lab_08_httpType.model

sealed trait Status {
  def strValue: String = this match {
    case Successful => "Successful"
    case Failed => "Failed"
  }
}
object Successful extends Status
object Failed extends Status

final case class Job(id: Long, projectName: String, status: String, duration: Long)
final case class Jobs(jobs: List[Job])
