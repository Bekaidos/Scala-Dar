import scala.collection.mutable.ListBuffer

object Test3  extends  App{
  var humans: Map[Int, Human] = Map()
  val q = Human("1", 1, "1")
  val w = Human("2", 2, "2")
  val e = Human("3", 3, "3")
  humans = Map(1 -> q, 2 -> w, 3 -> e)

  humans = humans.removed(1)

  var jobs: List[Job] = List.empty

  val a = Job(1, "1", 1)
  val s = Job(2, "2", 2)
  val d = Job(3, "3", 3)
  val f = Job(4, "4", 4)

  jobs = List(a, s, d, f)

  jobs = jobs.filter(_.id != 2)
  println(jobs)
  val id = jobs.indexOf(Job(4, "4", 4))
  println(id)
  println(jobs(2))

}

case class Human(name: String, age: Int, sex: String)
case class Job(id: Long, projectName: String, duration: Long)
