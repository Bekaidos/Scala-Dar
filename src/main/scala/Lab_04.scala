object GenericMap extends App{
  sealed trait GenericMap[T] {

    def map[T, B](fn: T => B): GenericMap[B] =
      this match {
        case GenericPair(head, tail) => GenericPair(fn(head), tail.map(fn))
        case GenericEnd() => GenericEnd[B]()
      }

    // TODO: implement map [T, B]  f: T => B
  }
  case class GenericEnd[T]() extends GenericMap[T]
  case class GenericPair[T](head: T, tail: GenericMap[T]) extends GenericMap[T]
}

object LinkedList extends App{

  sealed trait LinkedList[T]{
    def length: Int = this match {
      case Pair(head, tail) => 1 + tail.length
      case End()  => 0
    }
      def contains(item: T): Boolean = this match {
      case Pair(head, tail) =>
        if (head == item) true
        else tail.contains(item)
      case End() => false
    }
    def apply(index: Int): Result[T] = this match {
      case Pair(head, tail) =>
        if(index == 0) Success(head)
        else tail(index - 1)
      case End() => Failure("Index out of bounds")
    }
  }
  final case class Pair[T](head: T, tail: LinkedList[T]) extends LinkedList[T]
  final case class End[T]() extends LinkedList[T]

  sealed trait Result[T]
    case class Success[T](result: T) extends Result[T]
    case class Failure[T](reason: String) extends Result[T]

  val example = Pair(1, Pair(2, Pair(3, End())))

  println(example.length)
  println(example.contains(3))
  println(example(0))
  println(example(1))
  println(example(2))
  println(example(3))
}

object FunctionIntList extends App{
  sealed trait IntList {
    def fold[T](end: T, f: (Int, T) => T): T = this match {
      case End => end
      case Pair(head, tail) => f(head, tail.fold(end, f))
    }

    def length: Int = fold[Int](0, (_ , tail) => 1 + tail)
    def multiply: Int = fold[Int](1, (head ,tail) => head * tail)
    def sum: Int = fold[Int](0, (head, tail) => head + tail)
    def double: IntList = fold[IntList](End, (head, tail) => Pair(head * 2, tail))

  }
  final case object End extends IntList
  final case class Pair(head: Int, tail: IntList) extends IntList

  val example = Pair(1, Pair(2, Pair(3, End)))

  println(example.length)
  println(example.sum)
  println(example.multiply)
  println(example.double)

}

object Tree extends App {
  sealed trait Tree[A] {
    def fold[B](node: (B, B) => B, leaf: A => B): B
  }
  final case class Node[A](left: Tree[A], right: Tree[A]) extends Tree[A]{
    def fold[B](node: (B, B) => B, leaf: A => B): B = node(left.fold(node, leaf), right.fold(node, leaf))
  }
  final case class Leaf[A](value: A) extends Tree[A]{
    def fold[B](node: (B, B) => B, leaf: A => B): B = leaf(value)
  }

  val tree: Tree[String] =
    Node(Node(Leaf("To"), Leaf("iterate")),
      Node(Node(Leaf("is"), Leaf("human,")),
        Node(Leaf("to"), Node(Leaf("recurse"), Leaf("divine")))))

  println(tree.fold[String]((a, b) => a + " " + b, str => str))

}

object Pairs extends App{
  case class Pair[A, B](one: A, two: B)

  val pair = Pair("hi", 2)
  println(pair.one)
  println(pair.two)

  val x = (1, "b", true)
  println(x._1)
  println(x._2)
  println(x._3)


  sealed trait Maybe[A]{
    def fold[B](full: A => B, empty: B): B =
      this match {
        case Full(v) => full(v)
        case Empty() => empty
      }
  }
  final case class Full[A](value: A) extends Maybe[A]
  final case class Empty[A]() extends Maybe[A]

  val perhaps: Maybe[Int] = Empty[Int]
  val perhaps2: Maybe[Int] = Full(1)
  println(perhaps)
  println(perhaps2)

  sealed trait Sum[A, B]{
    def fold[C](left: A => C, right: B => C): C =
      this match {
        case Left(a) => left(a)
        case Right(b) => right(b)
      }
  }
  final case class Left[A, B](value: A) extends Sum[A, B]
  final case class Right[A, B](value: B) extends Sum[A, B]


}

object MappingLists extends App{

  sealed trait LinkedList[T]{
    def map[B](fn: T => B): LinkedList[B] =
      this match {
        case Pair(hd, tl) => Pair(fn(hd), tl.map(fn))
        case Empty() => Empty[B]()
      }
  }
  final case class Pair[T](head: T, tail: LinkedList[T]) extends LinkedList[T]
  final case class Empty[T]() extends LinkedList[T]

  val list: LinkedList[Int] = Pair(1, Pair(2, Pair(3, Empty())))

  println(list.map(_ * 2))
  println(list.map(_ + 1))
  println(list.map(_ / 3))

}

object MappingMaybe extends App{
  sealed trait Maybe[A] {
    def flatMap[B](fn: A => Maybe[B]): Maybe[B] =
      this match {
        case Full(v) => fn(v)
        case Empty() => Empty[B]()
      }
    def map[B](fn: A => B): Maybe[B] =
      this match {
        case Full(v) => Full(fn(v))
        case Empty() => Empty[B]()
      }
  }
  final case class Full[A](value: A) extends Maybe[A]
  final case class Empty[A]() extends Maybe[A]

  val list = List(1, 2, 3)
  println(list.flatMap(x => List(x, -x)))

  val list2 = List(Full(3), Full(2), Full(1))

 // println(list2.map(maybe => maybe flatMap { x => if(x % 2 == 0) Full(x) else Empty() }))
}

object Sum extends App{
  sealed trait Sum[A, B] {
    def fold[C](error: A => C, success: B => C): C =
      this match {
        case Failure(v) => error(v)
        case Success(v) => success(v)
      }
    def map[C](f: B => C): Sum[A, C] =
      this match {
        case Failure(v) => Failure(v)
        case Success(v) => Success(f(v))
      }
    def flatMap[C](f: B => Sum[A, C]) =
      this match {
        case Failure(v) => Failure(v)
        case Success(v) => f(v)
      }
  }
  final case class Failure[A, B](value: A) extends Sum[A, B]
  final case class Success[A, B](value: B) extends Sum[A, B]
}

object Sequences extends App{
  /*val   sequence = Seq(1, 2, 3)
  println(sequence.contains(4))
  println(sequence.filter(_ > 1))
  println(sequence.sortWith(_ > _))
  println(sequence :+ 4)
  println(0 +: sequence)
  println(sequence ++ Seq(4, 5, 6))
  println(sequence.mkString(","))

  val list = 1 :: 2 :: 3 :: Nil
  4 :: 5 :: list
  println(list)
  println(4 :: 5 :: list)
  println(List(1, 2, 3) ::: List(4, 5, 6))*/

  val animals = Seq("cat" , "dog", "penguin")
  println("mouse" +: animals :+ "tyrannosaurus")
  println(2 +: animals)

}

object IntranetMovieDatabase extends App{
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

  def numberOfFilms(numOfFilms: Int): Seq[Director] = directors.filter(_.films.length > numOfFilms)
  def year(numYear: Int): Option[Director] = directors.find(_.yearOfBirth < numYear)

  def numberOfFilmsAndYear(numOfFilms:Int, numYear: Int): Seq[Director] = {
    val num = directors.filter(_.films.length > numOfFilms)
    num.filter(directors.filter(_.yearOfBirth < numYear).contains)
  }

  def directorsSortedByAge(ascending: Boolean = true) =
    if(ascending) {
      directors.sortWith((a, b) => a.yearOfBirth < b.yearOfBirth)
    } else {
      directors.sortWith((a, b) => a.yearOfBirth > b.yearOfBirth)
    }

  println(year(1970))
  println(numberOfFilmsAndYear(3, 1951))

  println(directorsSortedByAge())


}