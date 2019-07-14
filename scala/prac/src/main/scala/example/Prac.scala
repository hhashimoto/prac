package example

object Prac extends App {
    println(1) // 1
    println(1 + 1) // 2
    println("Hello!") // Hello!
    println("Hello," + " world!") // Hello, world!

    /* val - Immutable Value */
    val x = 1 + 1 // `val` => immutable value definition
    println(x) // 2
    val x2: Int = 1 + 1 // notice type Int
    println(x2)  // 2
    // x = 3 // compile error!

    /* var - Mutable Value */
    var y = 1 + 1 // `var` => mutable value definition
    y = 3
    println(y * y) // 9

    /* {} - Blocks */
    println({
        val x = 10
        x * 2
    }) // 20

    /* Functions */
    val succ = (x: Int) => x + 1
    println(succ(1)) // 2
    val add = (x: Int, y: Int) => x + y
    println(add(2, 3)) // 5
    val sayHello = () => println("Hello")
    sayHello() // Hello

    /* Methods */
    def add2(x: Int, y: Int): Int = x + y
    println(add2(2, 3)) // 5
    def addThenMultiply(x: Int, y: Int)(multiplier: Int): Int = (x + y) * multiplier
    println(addThenMultiply(1, 2)(3)) // 9
    def home: String = System.getProperty("HOME")
    println("home = "+home)
    def getSquareString(input: Double): String = {
        val square = input * input
        square.toString
    }
    println(getSquareString(4.5)) // 20.25

    /* Classes */
    class Greeter(prefix: String, suffix: String) {
        def greet(name: String): Unit =
            println(prefix + name + suffix)
    }
    val usa = new Greeter("Hello, ", "!")
    usa.greet("Scala developer") // Hello, Scala developer!

    /* Case Class */
    case class Point(x: Int, y: Int) // => Immutable, Comparable
    val point = Point(1, 2)
    val point2 = Point(1, 2)
    val point3 = Point(3, 2)
    if (point == point2) {
        println(point + " and " + point2 + " are the same.")
    } else {
        println(point + " and " + point2 + " are different.")
    } // Point(1, 2) and Point(1, 2) are the same.
    if (point == point3) {
        println(point + " and " + point3 + " are the same.")
    } else {
        println(point + " and " + point3 + " are different.")
    } // Point(1, 2) and Point(3, 2) are different.

    /* Objects */
    object IdFactory { // similar singleton class instance
        private var counter = 0
        def create(): Int = {
            counter += 1
            counter
        }
    }
    var id1: Int = IdFactory.create()
    println(id1) // 1
    var id2: Int = IdFactory.create()
    println(id2) // 2

    /* Traits */
    trait TGreeter {
        def greet(name: String): Unit =
            println("Hello, " + name + "!")
    }
    class DefaultGreeter extends TGreeter

    class CustomizableGreeter(prefix: String, postfix: String) extends TGreeter {
        override def greet(name: String): Unit =
            println(prefix + name + postfix)
    }

    val greeter = new DefaultGreeter()
    greeter.greet("Scala developer") // Hello, Scala developer!
    val customGreeter = new CustomizableGreeter("How are you, ", "?")
    customGreeter.greet("Scala developer") // How are you, Scala developer?

    /* Unified Types */

    /*
    Any <- AnyRef(= java.lang.Object)
     ^
     |
    AnyVal <- Int, Double, Boolean, Char, String, Unit, ...
    */
    val list: List[Any] = List(
        "a string",
        732, // an integer
        'c', // a character
        true, // a boolean value
        () => "an anonymous function return a string"
    )
    list.foreach(e => println(e))

    // type casting
    val a: Long = 987654321
    val b: Float = a
    val face: Char = '☺'
    val number: Int = face
    println(a) // 987654321
    println(b) // 9.8765434E8
    println(face)
    println(number) // 9786

    /* Classes */

    // minimal class definition
    class User
    val user1 = new User

    // class definition
    class PointEx(var x: Int, var y: Int) {
        def move(dx: Int, dy: Int): Unit = {
            x += dx
            y += dy
        }

        override def toString: String =
            s"($x, $y)"
    }
    val point1 = new PointEx(2, 3)
    println(point1.x) // 2
    println(point1) // (2, 3)

    // constuctors
    class PointEx2(var x: Int = 0, var y: Int = 0)
    val origin = new PointEx2
    val pp1 = new PointEx2(1)
    val pp2 = new PointEx2(y=10)
    println(origin.x) // 0
    println(pp1.x) // 1
    println(pp2.y) // 10

    // Private Members and Getter/Setter Syntax
    class PointEx3 {
        private var _x = 0
        private var _y = 0
        private val bound = 100

        def x = _x
        def x_= (newX: Int): Unit = {
            if (newX < bound) _x = newX else printWarning
        }

        def y = _y
        def y_= (newY: Int): Unit = {
            if (newY < bound) _y = newY else printWarning
        }

        private def printWarning = println("WARNING: Out of bounds")
    }

    val pp5 = new PointEx3
    pp5.x = 99
    pp5.y = 101 // WARNING: Out of bounds
    println(pp5.x) // 99
    println(pp5.y) // 0

    // var/val is public, without var/val is private
    class Hoge(var a: Int = 1, val b: Int = 2, c: Int = 3)
    val h = new Hoge
    println(h.a) // 1
    println(h.b) // 2
    //println(h.c) // compile error
}