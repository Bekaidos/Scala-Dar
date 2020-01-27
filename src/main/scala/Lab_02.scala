object DirectorAndFilm extends App {

  class Director(val firstName:String, val lastName:String, val yearOfBirth:Int){
    def name: String = lastName + " " + firstName
    def copy(firstName:String = this.firstName,
             lastName:String = this.lastName,
             yearOfBirth:Int = this.yearOfBirth) : Director = new Director(firstName, lastName, yearOfBirth)

    override def toString():String = {
      return "firstname " + firstName + ", lastname " + lastName + ", yearOfBirth " + yearOfBirth
    }
  }

  object Director {
    def apply(firstName:String, lastName:String, yearOfBirth: Int): Director =
      new Director(firstName, lastName, yearOfBirth)

    def older(director1: Director, director2: Director): Director = if (director1.yearOfBirth < director2.yearOfBirth)
      return director1 else director2
  }

  class Film(val name:String, val yearOfRelease:Int, val imdRating:Double, val director: Director){
    def directorsAge: Int = director.yearOfBirth - yearOfRelease
    def isDirectedBy(director: Director): Boolean = this.director.equals(director)
    def copy (name:String = this.name,
              yearOfRelease:Int = this.yearOfRelease,
              imdRating:Double = this.imdRating,
              director: Director = this.director): Film = new Film(name, yearOfRelease, imdRating, director)

    override def toString():String = {
      return "name: " + name + ", yearOfRelease " + yearOfRelease + ", imdRating " + imdRating + ", director " + director
    }
  }

  object Film {
    def apply(name:String, yearOfRelease:Int, imdRating: Double, director: Director): Film =
      new Film(name, yearOfRelease, imdRating, director)
    def highestRating(film1: Film, film2: Film): Double =
      if (film1.imdRating > film2.imdRating) film1.imdRating else film2.imdRating
    def oldDirectorAtTheTime(film1: Film, film2: Film): Director =
      if (film1.director.yearOfBirth < film2.director.yearOfBirth) film1.director else film2.director
  }

  val eastwood = new Director("Clint", "Eastwood", 1930)
  val mcTiernan = new Director("John", "McTiernan", 1951)
  val nolan = new Director("Christopher", "Nolan", 1970)
  val someBody = new Director("Just", "Some Body", 1990)

  val memento = new Film("Memento", 2000, 8.5, nolan)
  val darkKnight = new Film("Dark Knight", 2008, 9.0, nolan)
  val inception = new Film("Inception", 2010, 8.8, nolan)
  val highPlainsDrifter = new Film("High Plains Drifter", 1973, 7.7, eastwood)
  val outlawJoseyWales = new Film("The Outlaw Josey Wales", 1976, 7.9, eastwood)
  val unforgiven = new Film("Unforgiven", 1992, 8.3, eastwood)
  val granTorino = new Film("Gran Torino", 2008, 8.2, eastwood)
  val invictus = new Film("Invictus", 2009, 7.4, eastwood)
  val predator = new Film("Predator", 1987, 7.9, mcTiernan)
  val dieHard = new Film("Die Hard", 1988, 8.3, mcTiernan)
  val huntForRedOctober = new Film("The Hunt for Red October", 1990, 7.6, mcTiernan)
  val thomasCrownAffair = new Film("The Thomas Crown Affair", 1999, 6.8, mcTiernan)

  println(eastwood.yearOfBirth)
  println(dieHard.director.name)
  println(invictus.isDirectedBy(nolan))

  println(highPlainsDrifter.copy(name = "qwe"))
  println(thomasCrownAffair.copy(yearOfRelease =  1968,
    director = new Director("Norman", "Jewison", 1926)))

  println(inception.copy().copy().copy())

  println(Director("q", "w", 123).lastName)
  println(Director.older(nolan, eastwood))

  println(Film("qwe", 1, 1.1, someBody).imdRating)
  println(Film.highestRating(predator, dieHard))
  println(Film.oldDirectorAtTheTime(memento, invictus))
}

object WithCaseDirectorAndFilm extends App {

  case class Director(firstName:String, lastName:String, yearOfBirth:Int){
    def name: String = lastName + " " + firstName
  }

  object Director {
    def older(director1: Director, director2: Director): Director = if (director1.yearOfBirth < director2.yearOfBirth)
      return director1 else director2
  }

   case class Film(name:String, yearOfRelease:Int, imdRating:Double, director: Director){
    def directorsAge: Int = director.yearOfBirth - yearOfRelease
    def isDirectedBy(director: Director): Boolean = this.director.equals(director)
  }

  object Film {
    def highestRating(film1: Film, film2: Film): Double =
      if (film1.imdRating > film2.imdRating) film1.imdRating else film2.imdRating
    def oldDirectorAtTheTime(film1: Film, film2: Film): Director =
      if (film1.director.yearOfBirth < film2.director.yearOfBirth) film1.director else film2.director
  }

  val eastwood = new Director("Clint", "Eastwood", 1930)
  val mcTiernan = new Director("John", "McTiernan", 1951)
  val nolan = new Director("Christopher", "Nolan", 1970)
  val someBody = new Director("Just", "Some Body", 1990)

  val memento = new Film("Memento", 2000, 8.5, nolan)
  val darkKnight = new Film("Dark Knight", 2008, 9.0, nolan)
  val inception = new Film("Inception", 2010, 8.8, nolan)
  val highPlainsDrifter = new Film("High Plains Drifter", 1973, 7.7, eastwood)
  val outlawJoseyWales = new Film("The Outlaw Josey Wales", 1976, 7.9, eastwood)
  val unforgiven = new Film("Unforgiven", 1992, 8.3, eastwood)
  val granTorino = new Film("Gran Torino", 2008, 8.2, eastwood)
  val invictus = new Film("Invictus", 2009, 7.4, eastwood)
  val predator = new Film("Predator", 1987, 7.9, mcTiernan)
  val dieHard = new Film("Die Hard", 1988, 8.3, mcTiernan)
  val huntForRedOctober = new Film("The Hunt for Red October", 1990, 7.6, mcTiernan)
  val thomasCrownAffair = new Film("The Thomas Crown Affair", 1999, 6.8, mcTiernan)

  println(eastwood.yearOfBirth)
  println(dieHard.director.name)
  println(invictus.isDirectedBy(nolan))

  println(highPlainsDrifter.copy(name = "qwe"))
  println(thomasCrownAffair.copy(yearOfRelease =  1968,
    director = new Director("Norman", "Jewison", 1926)))

  println(inception.copy().copy().copy())

  println(Director("q", "w", 123).lastName)
  println(Director.older(nolan, eastwood))

  println(Film("qwe", 1, 1.1, someBody).imdRating)
  println(Film.highestRating(predator, dieHard))
  println(Film.oldDirectorAtTheTime(memento, invictus))
}

object Counter extends App{

  class Counter(val count: Int) {
    def inc: Counter = inc()
    def dec: Counter = dec()
    def inc(increment: Int = 1) = new Counter(count + increment)
    def dec(decrement: Int = 1) = new Counter(count - decrement)
    def adjust(adder: Adder) = new Counter(adder.add(count))
  }
  println(new Counter(10).inc.dec.inc.inc.count)
  println(new Counter(10).inc.inc(10).count)
  println(new Counter(10).adjust(new Adder(10)).count)

  class Adder(amount: Int){
    def add(in: Int) = in + amount
  }
}

object Adder extends App{

  class Adder(amount: Int){
    def apply(in: Int) = in + amount
  }
  val add3 = new Adder(3)
  println(add3(2))

}

object Time extends App{

  class TimeStamp(val seconds: Long)

  object TimeStamp {
    def apply(hours: Int, minutes: Int, seconds: Int): TimeStamp =
      new TimeStamp(hours*60*60 + minutes * 60 + seconds)
  }

  println(TimeStamp(1, 1, 1).seconds)
}

object Person extends App{

  class Person(val lastName: String, val firstName: String)

  object Person {
    def apply(name:String): Person = {
      val parts = name.split(" ")
      new Person(parts(0), parts(1))
    }
  }
  println(Person("Bek Aidos").firstName)
}

object Case extends App{

  case class Person(firstName:String, lastName:String){
    def name = firstName + lastName
  }
  val dave = new Person("dave" , "dave")

  println(dave.firstName)
  println(dave)

  println(new Person("qwe", "qwe") == new Person("qwe", "qwe"))
  println(new Person("qwe", "qwe").equals(new Person("qwe", "qwe")))
}

object CaseCat extends App{

  case class Cat(colour: String, food: String)
  val cat1 = Cat("red", "q")
  val cat2 = Cat("blue", "w")
  val cat3 = Cat("grey", "e")

  if (cat1.food == "w") println(cat1)
  else if (cat2.food == "w") println(cat2)
  else if (cat3.food == "e") println(cat3)
}

