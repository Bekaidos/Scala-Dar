package scala.Lab_05

object Lab_05 extends App {
  val sequence = Seq(1, 2, 3)

  println(  sequence.map(num => num * 2))

  println("dog".permutations.toList)

  List(1, 2, 3).foreach(num => println("qwe" + num + num + " rty"))

}

object NolanFilms extends App{
  case class Film(
                   name: String,
                   yearOfRelease: Int,
                   imdbRating: Double)
  case class Director(
                       firstName: String,
                       lastName: String,
                       yearOfBirth: Int,
                       films: Seq[Film])
  val memento = new Film("Memento", 2000, 8.5)
  val darkKnight = new Film("Dark Knight", 2008, 9.0)
  val inception = new Film("Inception", 2010, 8.8)
  val highPlainsDrifter = new Film("High Plains Drifter", 1973, 7.7)
  val outlawJoseyWales = new Film("The Outlaw Josey Wales", 1976, 7.9)
  val unforgiven = new Film("Unforgiven", 1992, 8.3)
  val granTorino = new Film("Gran Torino", 2008, 8.2)
  val invictus = new Film("Invictus", 2009, 7.4)
  val predator = new Film("Predator", 1987, 7.9)
  val dieHard = new Film("Die Hard", 1988, 8.3)
  val huntForRedOctober = new Film("The Hunt for Red October", 1990, 7.6)
  val thomasCrownAffair = new Film("The Thomas Crown Affair", 1999, 6.8)

  val eastwood = new Director("Clint", "Eastwood", 1930,
    Seq(highPlainsDrifter, outlawJoseyWales, unforgiven, granTorino, invictus))
  val mcTiernan = new Director("John", "McTiernan", 1951,
    Seq(predator, dieHard, huntForRedOctober, thomasCrownAffair))
  val nolan = new Director("Christopher", "Nolan", 1970,
    Seq(memento, darkKnight, inception))
  val someGuy = new Director("Just", "Some Guy", 1990,
    Seq())

  val directors = Seq(eastwood, mcTiernan, nolan, someGuy)

  println(nolan.films.map(film => film.name))

  println(directors.flatMap(dir => dir.films.map(film => film.name)))

  println(mcTiernan.films.map(film => film.yearOfRelease).min)
  val we = mcTiernan.films.sortWith{ (a, b) => a.yearOfRelease < b.yearOfRelease}.headOption
  println(we)
  val qwe = mcTiernan.films.foldLeft(Int.MaxValue) { (current, film) => math.min(current, film.yearOfRelease)}
  println(qwe)

  println(directors.flatMap(dir => dir.films.map(film => film.imdbRating)).sortWith(_ > _))

  val allImdb = directors.flatMap(dir => dir.films.map(film => film.imdbRating))
  val averageImbd = allImdb.sum / allImdb.size
  print(averageImbd)

  /*val films = directors.flatMap(dir => dir.films)
  films.foreach((film) => println("Tonight only! " + film.name + " by "))*/

  directors.foreach(dir =>
    dir.films.foreach(film =>
      println(s"Tonight only! " + film.name + " by " + dir.firstName)))

  val films = directors.flatMap(dir => dir.films)
  println(films.sortWith((a, b) => a.yearOfRelease < a.yearOfRelease).headOption)
}

object MinimumSeq extends App{
  val sequences = Seq(1, 2, 3, -4, 5)
  def smallest(seq: Seq[Int]) = seq.foldLeft(Int.MaxValue)(math.min)

  println(smallest(sequences))

  val sequences2 = Seq(1, 1, 2, 4, 3, 4)
  println(sequences2.distinct)

  println(sequences.reverse)
}

object NolanFilmsWithFor extends App{
  case class Film(
                   name: String,
                   yearOfRelease: Int,
                   imdbRating: Double)
  case class Director(
                       firstName: String,
                       lastName: String,
                       yearOfBirth: Int,
                       films: Seq[Film])
  val memento = new Film("Memento", 2000, 8.5)
  val darkKnight = new Film("Dark Knight", 2008, 9.0)
  val inception = new Film("Inception", 2010, 8.8)
  val highPlainsDrifter = new Film("High Plains Drifter", 1973, 7.7)
  val outlawJoseyWales = new Film("The Outlaw Josey Wales", 1976, 7.9)
  val unforgiven = new Film("Unforgiven", 1992, 8.3)
  val granTorino = new Film("Gran Torino", 2008, 8.2)
  val invictus = new Film("Invictus", 2009, 7.4)
  val predator = new Film("Predator", 1987, 7.9)
  val dieHard = new Film("Die Hard", 1988, 8.3)
  val huntForRedOctober = new Film("The Hunt for Red October", 1990, 7.6)
  val thomasCrownAffair = new Film("The Thomas Crown Affair", 1999, 6.8)

  val eastwood = new Director("Clint", "Eastwood", 1930,
    Seq(highPlainsDrifter, outlawJoseyWales, unforgiven, granTorino, invictus))
  val mcTiernan = new Director("John", "McTiernan", 1951,
    Seq(predator, dieHard, huntForRedOctober, thomasCrownAffair))
  val nolan = new Director("Christopher", "Nolan", 1970,
    Seq(memento, darkKnight, inception))
  val someGuy = new Director("Just", "Some Guy", 1990,
    Seq())

  val directors = Seq(eastwood, mcTiernan, nolan, someGuy)

  for {
    film <- nolan.films
  } println(film.name)

  println()

  for {
    dir <- directors
    film <- dir.films
  } println(film.name)

   val films  = for {
    dir <- directors
    film <- dir.films
  } yield film

  println()

  println(films.sortWith((a, b) => a.imdbRating > b.imdbRating))

  println()

  for {
    dir <- directors
    films <- dir.films
  } println(s"Tonight only! ${films.name} by ${dir.firstName}")
  
}

object Options extends App {
  def readInt(str: String): Option[Int] = {
    if (str matches "\\d+") Some(str.toInt) else None
  }

  def addOptions(opt1: Option[Int], opt2: Option[Int]) =
    for {
      a <- opt1
      b <- opt2
    } yield a + b

  def addOptions2(opt1: Option[Int], opt2: Option[Int]) =
    opt1 flatMap( a =>
      opt2 map( b =>
        a+ b
        ))

  def addOptions3(opt1: Option[Int], opt2: Option[Int], opt3: Option[Int]) =
    for {
      a <- opt1
      b <- opt2
      c <- opt3
    } yield a + b + c

  def addOptions4(opt1: Option[Int], opt2: Option[Int], opt3: Option[Int]) =
    opt1 flatMap( a =>
      opt2 flatMap ( b =>
        opt3 map( c =>
          a + b + c
        )))

  def divide(numerator: Int, denominator: Int): Option[Int] =
    if(denominator < 1) None else Some(numerator / denominator)

  def calculator(operand1: String, operator: String, operand2: String): Unit ={
    def calInternal(a:Int, b: Int) =
      operator match {
        case "+" => Some(a + b)
        case "-" => Some(a - b)
        case "*" => Some(a * b)
        case "/" => divide(a, b)
        case _ => None
      }
    val result = for {
      a <- readInt(operand1)
      b <- readInt(operand2)
      ans <- calInternal(a, b)
    } yield ans

    result match {
      case Some(number) => println(s"The answer is ${number}")
      case None => println(s"Error calculating $operand1 $operator $operand2")
    }
  }


  calculator("5", "+", "6")
  calculator("a", "+", "6")
  calculator("5", "/", "0")
}
