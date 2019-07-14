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

    /* Traits */

    // minimal
    trait HairColor

    // generic
    trait Iterator[A] {
        def hasNext: Boolean
        def next(): A
    }

    class IntIterator(to: Int) extends Iterator[Int] {
        private var current = 0
        override def hasNext: Boolean = current < to
        override def next(): Int = {
            if (hasNext) {
                val t = current
                current += 1
                t
            } else 0
        }
    }

    val it = new IntIterator(10)
    println(it.next()) // 0
    println(it.next()) // 1

    // subtyping
    import scala.collection.mutable.ArrayBuffer
    trait Pet {
        val name: String
    }
    class Cat(val name: String) extends Pet
    class Dog(val name: String) extends Pet
    val dog = new Dog("Harry")
    val cat = new Cat("Sarry")
    val animals = ArrayBuffer.empty[Pet]
    animals.append(dog)
    animals.append(cat)
    animals.foreach(pet => println(pet.name))

    /* Tuples */
    var ingredient = ("Sugar", 25)
    println(ingredient) // (Sugar, 25)
    println(ingredient._1) // Sugar
    println(ingredient._2) // 25

    val (name, quantity) = ingredient
    println(name) // Sugar
    println(quantity) // 25

    val planets = List(
        ("Mercury", 57.9), ("Venus", 108.2), ("Earth", 149.6),
        ("Mars", 227.9), ("Jupiter", 778.3)
    )
    planets.foreach{
        case ("Earth", distance) =>
            println(s"Our planets is $distance million killometers from the sun")
        case _ =>
    }
    val numPairs = List((2, 5), (3, -7), (20, 56))
    for ((a, b) <- numPairs) {
        println(a * b)
    }

    /* Class Composition with Mixins */
    abstract class A {
        val message: String
    }
    class B extends A {
        val message = "I'm an instance of class B"
    }
    trait C extends A {
        def loudMessage = message.toUpperCase()
    }
    class D extends B with C
    val d = new D
    println(d.message) // I'm an instance of class B
    println(d.loudMessage) // I'M AN INSTANCE OF CLASS B

    abstract class AbsIterator {
        type T
        def hasNext: Boolean
        def next(): T
    }
    class StringIterator(s: String) extends AbsIterator {
        type T = Char
        private var i = 0
        def hasNext = i < s.length
        def next() = {
            var ch = s charAt i
            i += 1
            ch
        }
    }
    trait RichIterator extends AbsIterator {
        def foreach(f: T => Unit): Unit = while (hasNext) f(next())
    }
    class RichStringIterator(s: String) extends StringIterator(s) with RichIterator
    val rsi = new RichStringIterator("Hello")
//    rsi.foreach(c => println(c))
    rsi foreach println // = rsi.foreach(c => println(c))

    /* Higer-order Functions */
    val sararies = Seq(20000, 70000, 40000)
    val doubleSarary = (x: Int) => x * 2
    val newSararies = sararies.map(doubleSarary)
    println(newSararies) // List(40000, 140000, 80000)
    val newSararies3 = sararies.map(x => x * 3) // List(60000, 210000, 120000)
    println(newSararies3)
    val newSararies4 = sararies.map(_ * 4) // List(80000, 280000, 160000)
    println(newSararies4)

    case class WeeklyWeatherForecast(temperatures: Seq[Double]) {
        private def convertCtoF(temp: Double) = temp * 1.8 + 32
        def forecastInFahrenheit: Seq[Double] = temperatures.map(convertCtoF)
    }
    val wwf = WeeklyWeatherForecast(Seq(24.5, 27, 30.5, 31.5))
    wwf.forecastInFahrenheit.map(println)

    object SalaryRaiser {
        private def promotion(salaries: List[Double], promotionFunction: Double => Double): List[Double] =
            salaries.map(promotionFunction)

        def smallPromotion(salaries: List[Double]): List[Double] =
            promotion(salaries, salary => salary * 1.1)

        def bigPromotion(salaries: List[Double]): List[Double] =
            promotion(salaries, salary => salary * math.log(salary))

        def hugePromotion(salaries: List[Double]): List[Double] =
            promotion(salaries, salary => salary * salary)
    }
    val salaries: List[Double] = List(10000, 30000, 50000)
    SalaryRaiser.smallPromotion(salaries).map(println)
    SalaryRaiser.bigPromotion(salaries).map(println)
    SalaryRaiser.hugePromotion(salaries).map(println)

    // Functions that return functions
    def urlBuilder(ssl: Boolean, domainName: String): (String, String) => String = {
        val schema = if (ssl) "https://" else "http://"
        (endpoint: String, query: String) => s"$schema$domainName/$endpoint?$query" 
    }
    val domainName = "www.example.com"
    def getURL = urlBuilder(ssl=true, domainName)
    val endpoint = "users"
    val query = "id=1"
    val url = getURL(endpoint, query)
    println(url) // https://www.example.com/users?id=1

    /* Nested Methods */
    def factorial(x: Int): Int = {
        def fact(x: Int, acc: Int): Int = {
            if (x <= 1) acc
            else fact(x - 1, x * acc)
        }
        fact(x, 1)
    }
    println("factorial of 5: " + factorial(5))

    /* Multiple Parameter Lists(Currying) */

    val numbers = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val res = numbers.foldLeft(0)((acc,n) => acc + n)
    println(res) // 55
    val res2 = numbers.foldLeft(0)((m: Int, n: Int) => m + n)
    val res3 = numbers.foldLeft(0)(_ + _)
    println(res2) // 55
    println(res3) // 55

    // Partial application
    val numberFunc = numbers.foldLeft(List[Int]()) _
    val squares = numberFunc((xs, x) => xs :+ x*x)
    print(squares)
    println()
    val cubes = numberFunc((xs, x) => xs :+ x*x*x)
    print(cubes)
    println()

    /* Case Classes */
    case class Message(sender: String, receipient: String, body: String)
    val message1 = Message("src@example.com", "dest@example.com", "message body")
    val message2 = message1.copy(sender = message1.receipient, receipient = message1.sender)
    println(message1.sender)
    println(message2.sender)
    println(message2.receipient)
    println(message2.body)

    /* Pattern Matching */
    import scala.util.Random

    val r: Int = Random.nextInt(10)
    val v1 = r match {
        case 0 => "zero"
        case 1 => "one"
        case 2 => "two"
        case _ => "other"
    }
    println(v1)

    def matchTest(x: Int): String = x match {
        case 1 => "one"
        case 2 => "two"
        case _ => "other"
    }
    println(matchTest(3)) // other
    println(matchTest(1)) // one

    // matching on case classes
    abstract class Notification
    case class Email(sender: String, title: String, body: String) extends Notification
    case class SMS(caller: String, message: String) extends Notification
    case class VoiceRecording(contactName: String, link: String) extends Notification

    def showNotification(notification: Notification): String = {
        notification match {
            case Email(sender, title, _) =>
                s"You got an email from $sender with title: $title"
            case SMS(number, message) =>
                s"You got an SMS from $number! Message: $message"
            case VoiceRecording(name, link) =>
                s"you received a Voice Recoridng from $name! Click the link hear it: $link"
        }
    }
    val someSms = SMS("12345", "Are you there?")
    val someVoiceRecording = VoiceRecording("Tom", "voicerecoridng.org")
    println(showNotification(someSms)) // You got an SMS from 12345! Message: Are you there?
    println(showNotification(someVoiceRecording)) // you received a Voice Recoridng from Tom! Click the link hear it: voicerecoridng.org

    def showImportantNotification(notification: Notification, importantPeopleInfo: Seq[String]): String = {
        notification match {
            case Email(sender, _, _) if importantPeopleInfo.contains(sender) =>
                "You got an email from special someone!"
            case SMS(number, _) if importantPeopleInfo.contains(number) =>
                "You got an SMS from special someone!"
            case other =>
                showNotification(other)
        }
    }
    val importantPeopleInfo = Seq("867-5309", "jenny@gmail.com")
    val importantEmail = Email("jenny@gmail.com", "Hello", "Hello,Hello")
    val importantSms = SMS("867-5309", "hogepiyo")
    println(showImportantNotification(someSms, importantPeopleInfo)) // You got an SMS from 12345! Message: Are you there?
    println(showImportantNotification(someVoiceRecording, importantPeopleInfo)) // you received a Voice Recoridng from Tom! Click the link hear it: voicerecoridng.org
    println(showImportantNotification(importantEmail, importantPeopleInfo)) // You got an email from special someone!
    println(showImportantNotification(importantSms, importantPeopleInfo)) // You got an SMS from special someone!

    // Matching on type only
    abstract class Device
    case class Phone(model: String) extends Device {
        def screenOff = "Turning screen off"
    }
    case class Computer(model: String) extends Device {
        def screenSaverOn = "Turning screen saver on..."
    }
    def goIdle(device: Device) = device match {
        case p: Phone => p.screenOff
        case c: Computer => c.screenSaverOn
    }

    /* Singleton Objects */
    import logging.Logger.info
    class Project(name: String, daysToComplete: Int)
    class Test {
        val project1 = new Project("TPS Reports", 1)
        val project2 = new Project("Website redesign", 5)
        info("Created projects")
    }
    val t = new Test // INFO: Created projects

    // Companion objects
    import scala.math._
    case class Circle(radius: Double) {
        import Circle._
        def area: Double = calculateArea(radius)
    }
    object Circle {
        private def calculateArea(radius: Double): Double = Pi * pow(radius, 2.0)
    }
    val circle1 = new Circle(5.0)
    println(circle1.area)

    class Email2(val username: String, val domainName: String)

    object Email2 {
        def fromString(emailString: String): Option[Email2] = {
            emailString.split('@') match {
                case Array(a, b) => Some(new Email2(a, b))
                case _ => None
            }
        }
    }
    val scalaCenterEmail = Email2.fromString("scala.center@epfl.ch")
    scalaCenterEmail match {
        case Some(email) => println(
            s"""Registered an email
                |Username: ${email.username}
                |Domain name: ${email.domainName}
            """)
        case None => println("Error: could not parse email")
    }

    /* Regular Expression Patterns */
    import scala.util.matching.Regex

    val numberPattern: Regex = "[0-9]".r
    numberPattern.findFirstMatchIn("awesomepassword") match {
        case Some(_) => println("Password OK")
        case None => println("Password must contain a number")
    } // Password must contain a number

    val keyValPattern: Regex = "([0-9a-zA-Z- ]+): ([0-9a-zA-Z-#()/. ]+)".r
    val input: String =
        """background-color: #A03300;
            |background-image: url(img/header100.png);
            |background-position: top center;
            |background-repeat: repeat-x;
            |background-size: 2160px 108px;
            |margin: 0;
            |height: 108px;
            |width: 100%;""".stripMargin
    for (patternMatch <- keyValPattern.findAllMatchIn(input))
        println(s"key: ${patternMatch.group(1)} value: ${patternMatch.group(2)}")
    
    /* Extractor Objects */
    object CustomerID {
        def apply(name: String) = s"$name--${Random.nextLong}"

        def unapply(customerID: String): Option[String] = {
            val stringArray: Array[String] = customerID.split("--")
            if (stringArray.tail.nonEmpty) Some(stringArray.head) else None
        }
    }
    val customer1ID = CustomerID("HogeFuga")
    println(customer1ID) // HogeFuga---976794760280160152
    customer1ID match {
        case CustomerID(name) => println(name)
        case _ => println("Could not extracta CustomerID")
    } // HogeFuga
    val customer2ID = CustomerID("Nico")
    val CustomerID(cname) = customer2ID
    println(cname) // Nico
    val CustomerID(cname2) = "--asbdyaruteyd"
//    val CustomerID(cname3) = "-asbdyeryu" // scala.MatchError
    println(cname2) //
}