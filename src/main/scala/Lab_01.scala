object Lab_01 extends App {

  println(6*2) //12

  for (i <- 1 to 3){
    println(i)          //1 2 3
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




