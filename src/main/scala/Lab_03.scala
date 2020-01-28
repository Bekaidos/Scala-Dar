import java.awt.geom.Area
import java.util.Date

import org.w3c.dom.css.RGBColor

object Lab_03 extends App{

  case class Person(firstName: String, lastName: String)

  object Stormtrooper{
    def inspect(person: Person): String = person match {
      case Person("Luke", "Skywalker") => "Stop, rebel scum!"
      case Person("Han", "Solo") => "Stop, rebel scum!"
      case Person(first, last) => s"Move along, ${first}"
    }
  }
}

object CatPatternMatching extends App{

  case class Cat(colour:String, food:String)

  val Oswald = Cat( "Black", "Milk")
  val Henderson = Cat( "Ginger", "Chips")
  val Quentin = Cat( "Tabby and white", "Curry")

  object chipShop {
    def willServe(cat: Cat): Boolean = cat match {
      case Cat(_ , "Chips") => true
      case Cat(_, _) => false
    }
  }

  println(chipShop.willServe(Oswald))
  println(chipShop.willServe(Henderson))
}

object PatternMatchingDadAndFilm extends App{
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
  object Dad{
    def rate(film: Film): Double = film match {
      case Film(_, _, _, Director("Clint", "Eastwood", _)) => 10.0
      case Film(_, _, _, Director("John", "McTiernan", _)) => 7.0
      case Film(_, _, _, _) => 3.0
    }
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

  println(Dad.rate(highPlainsDrifter))
  println(Dad.rate(memento))
  println(Dad.rate(predator))
  println(Dad.rate(Film("qwe", 2000, 10.0, someBody)))
}

object SealedTraitVisitorExpample extends App{

  sealed trait Visitor {def id:String
                def createdAt: Date
                def age: Long = new Date().getTime - createdAt.getTime}
  final case class Anonymous (id: String, createdAt: Date = new Date()) extends Visitor
  final case class User(id: String, email: String, createdAt: Date = new Date()) extends Visitor
}

object CatSimulatorV2_0 extends App {

  sealed trait Feline {
    def colour: String
    def sound: String
    def dinner: Food
  }
  sealed trait BigCat extends Feline {
    def sound:String = "roar"
  }
  case class Lions(colour:String, maneSize: Int) extends BigCat {
    def dinner = Antelope
  }
  case class Tiger(colour: String) extends BigCat {
    def dinner = TigerFodd
  }
  case class Panther(colour: String) extends BigCat {
    def dinner = Licorice
  }
  case class Cat(colour: String, favouriteFood: String ) extends Feline{
    val sound = "meow"
    def dinner = CatFood(favouriteFood)
  }
  sealed trait Food
  final case object Antelope extends Food
  final case object TigerFodd extends Food
  final case object Licorice extends Food
  final case class CatFood(food: String) extends Food

  println(Cat("Red", "whiskas").sound)
  println(Panther("grey").sound)
}

object Shape extends App {

  sealed trait Shape {
    def sides: Int
    def perimeter: Double
    def area: Double
    def color: Color
  }

  trait Rectangular extends Shape {
    def width: Int
    def height: Int
    val sides = 4
    val perimeter = width  * 2 + height * 2
    val area = width * height
  }

  case class Square(size: Int, color: Color) extends Rectangular {
    def width = size
    def height = size
  }
  case class Rectangle(width: Int, height: Int, color: Color) extends Rectangular { }
  case class Circle(radius: Double, color: Color) extends Shape{
    val sides = 1
    val perimeter = 2 * math.Pi * radius
    val area = math.Pi * radius * radius
  }

  object Draw {
    def apply(shape: Shape): String = shape match {
      case Rectangle(width: Int, height: Int, color) => s"A ${Draw(color)} rectangle of width ${width}cm and height ${height}cm"
      case Square(size: Int, color) => s"A ${Draw(color)} square of size ${size}cm"
      case Circle(radius: Double, color) => s"A ${Draw(color)} circle of radius ${radius}cm"
    }
    def apply(color: Color): String = color match {
      case Red => "red"
      case Yellow => "yellow"
      case Pink => "pink"
      case color => if(color.isLight) "light" else "dark"
    }
  }

  sealed trait Color {
    def red: Double
    def green: Double
    def blue: Double

    def isLight = (red + green + blue) / 3.0 > 0.5
    def isDark = !isLight
  }

  final case object Red extends Color {
    val red = 1.0
    val green = 0.0
    val blue = 0.0
  }
  final case object Yellow extends Color {
    val red = 1.0
    val green = 1.0
    val blue = 0.0
  }
  final case object Pink extends Color {
    val red = 1.0
    val green = 0.0
    val blue = 1.0
  }
  final case class CustomColor(
                                red: Double,
                                green: Double,
                                blue: Double) extends Color


  println(Draw(Circle(10, Pink)))
  println(Draw(Rectangle(3, 4, CustomColor(0.4, 0.4, 0.6))))
}

object DivisionExercise extends App {
  sealed trait DivisionResult
  final case class Finite(value: Int) extends DivisionResult
  final case object Infinite extends DivisionResult

  object divide {
    def apply (num: Int, den: Int): DivisionResult =
      if (den == 0) Infinite else Finite(num / den)
  }

  divide(1, 0) match {
    case Finite(value) => s"It's finite: ${value}"
    case Infinite => s"It's infinite"
  }


}

object TrafficLightAndCalculator extends App{
  sealed trait TrafficLight {
    def next: TrafficLight = this match {
      case Red => Green
      case Green => Yellow
      case Yellow => Red
    }
  }
  final case object Red extends TrafficLight
  final case object Yellow extends TrafficLight
  final case object Green extends TrafficLight

  sealed trait Calculator
  final case class Success(result: Int) extends Calculator
  final case class Failure(reason: String) extends Calculator
  object Calculator{
    def +(calc: Calculator, operand: Int): Calculator = calc match {
      case Success(result) => Success(result + operand)
      case Failure(reason) => Failure(reason)
    }
    def -(calc: Calculator, operand: Int): Calculator = calc match {
      case Success(result) => Success(result - operand)
      case Failure(reason) => Failure(reason)
    }
    def /(calc: Calculator, operand: Int): Calculator = calc match {
      case Success(result) => operand match {
        case 0 => Failure("Division by zero")
        case _ => Success(result/ operand)
      }
      case Failure(reason) => Failure(reason)
    }
  }
  println(Calculator./(Success(4), 2))
  println(Calculator./(Success(4), 0))

  final case class BottledWater(size: Int, sourse: Sourse, carbonated: Boolean)
  sealed trait Sourse
  final case object Well extends Sourse
  final case object Spring extends Sourse
  final case object Tap extends Sourse
}

object IntList extends App{

  sealed trait IntList {
    def length: Int = this match {
      case End => 0
      case Pair(hd, tl) => 1 + tl.length
    }
    def multiply: Int = this match {
      case End => 1
      case Pair(hd, tl) => hd * tl.multiply
    }
    def double: IntList = this match {
      case End => End
      case Pair(hd, tl) => Pair(hd*2, tl.double)
    }
  }
  final case object End extends IntList
  final case class Pair(head: Int, tail: IntList) extends IntList

  val example = Pair(1, Pair(2, Pair(3, End)))

  println(example.double)
  println(example.tail.double)
  println(End.double)
}

