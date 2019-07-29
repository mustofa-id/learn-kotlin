@file:JvmName("Main")

package id.mustofa.learn

import java.math.BigInteger


fun main() {
    printTag("Begin")
    val app = App()

    // calling extension
    var name = "Habib".myLastName()

    // calling extension (infix)
    val isGT = 10 gt 9 // == 10.gt(9)
    println("INFIX: $isGT")

    println("My name is $name")

    val person = app.person
    person.name = app.myName()
    person.run(20.5)

    // calling method with Named Parameters can do random order
    person.run(newName = "https://mustofa.id", speed = 22.7)

    person.printUpdatedAge()
    person.printRange()
    person.printIfAsExpression()
    person.printWhen()
    person.printLoops()
    person.printFunAsExpression()

    val index = 10000
    val fibonacci = fibonacciNumber(index, BigInteger("1"), BigInteger("0"))
    println("Fibonacci in index $index is:\n$fibonacci")

    name = "Done!"
    printTag(name)
}

@JvmOverloads // Make java support default value
fun printTag(title: String = "NO_TITLE") = println("\n======= $title")

// add new function in String class (extension function)
fun String.myLastName(): String = "$this Mustofa"

// add new function in Int class (infix function)
// can only receive one argument
infix fun Int.gt(value: Int): Boolean = this > value

// if n == 10000 we will get java.lang.StackOverflowError because method
// called recursively and method stack memory will overflow
// to solve that we use tailrec keyword (tail-recursive)
// the compiler optimises out the recursion
tailrec fun fibonacciNumber(n: Int, a: BigInteger, b: BigInteger): BigInteger {
    return if (n == 0) b
    else fibonacciNumber(n - 1, a + b, a)
}