object Test3  extends  App{
  var humans: Map[Int, Human] = Map()
  val q = Human("1", 1, "1")
  val w = Human("2", 2, "2")
  val e = Human("3", 3, "3")
  humans = Map(1 -> q, 2 -> w, 3 -> e)

  humans = humans.removed(1)

  println(humans)


}

case class Human(name: String, age: Int, sex: String)
