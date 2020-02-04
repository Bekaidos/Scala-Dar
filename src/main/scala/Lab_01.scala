object Lab_01 extends App {
  //
  println("foo" take 1)
  println(1.+(2).+(3))

  object Oswald {
    val colour: String = "Black"
    val food: String = "Milk"
  }
  object Henderson {
    val colour: String = "Ginger"
    val food: String = "Chips"
  }
  object Quentin {
    val colour: String = "Tabby and white"
    val food: String = "Curry"
  }

  object calc {
    def square(x: Double) = x * x
    def cube(x: Double) = x * square(x)
  }
  println(calc.cube(2))

  object calc2 {
    def square(value: Double) = value * value
    def cube(value: Double) = value * square(value)
    def square(value: Int) = value * value
    def cube(value: Int) = value * square(value)
  }
  println(calc2.square(2.1))

  object person {
    val firstName = "Dave"
    val lastName = "Gurnell"
  }
  object alien {
    def greet(p: person.type) =
      "Greetings, " + p.firstName + " " + p.lastName
  }
  println(alien.greet(person))

  for (i <- 1 to 3){
    println(i)
  }

  println(2.max(999))

  println("abcdef".take(1+1))

  println("hello".toUpperCase.toLowerCase)

  assert(3+1 == 4)

}

object HelloWorld {
  def main(args: Array[String]): Unit = {
    println("Hello World".toUpperCase)
  }
}

