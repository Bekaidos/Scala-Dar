if (2 > 1) println("Yes") else println("No")

if(1 > 2) "alien" else 2001
if(false) "hello"

class Cat(val colour:String, val food:String)

val Oswald = new Cat( "Black", "Milk")
val Henderson = new Cat( "Ginger", "Chips")
val Quentin = new Cat( "Tabby and white", "Curry")


object ChipSHop {
  def willServe(cat: Cat): Boolean =
    if (cat.food == "Chips")
      true
    else
      false
}
println(ChipSHop.willServe(Oswald))
println(ChipSHop.willServe(Henderson))
println(ChipSHop.willServe(Quentin))







