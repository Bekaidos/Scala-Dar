for (i <- 1 to 3){
  println(i)
}

"Hello World".toUpperCase

2.min(1)

"abcdef" take 2

"Hello".toUpperCase().toLowerCase

"123".toShort
"123".toByte

"the quick brown fox" split(" ")

42
42.0
42.0f
42L

"the\nusual\tescape characters apply"

null

object Test3{
  def hello(name: String) = "Hello " + name
}
Test3.hello("Paul")

object Test7 {
  val simpleField = {
    println("Evaluating simpleField")
    42
  }
  def noParameterMethod = {
    println("Evaluating noParameterMethod")
    42
  }
}
Test7
Test7.simpleField
Test7.simpleField

object person{
  val firstname: String = "Aidos"
  val lastname: String = "Bek"
}
object alien {
  def greet(p: person.type ) =
    "Greatings, " + p.firstname + " " +p.lastname
}
alien.greet(person)

assert(alien.greet(person) == "Greatings, Aidos Bek")















