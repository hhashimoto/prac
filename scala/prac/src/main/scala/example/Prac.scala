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
}