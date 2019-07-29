package id.mustofa.learn

import java.util.*
import java.util.concurrent.ThreadLocalRandom

class Person(
    private var age: Int = 0,
    private var yearOfBirth: Int = 1500
) {

    /**
     * Data types: All is Object
     * String, Int, Double, Number, Boolean,
     * Char, Byte Short, Long, Float
     */

    var name: String? = null

    fun run(speed: Double, newName: String? = name) {
        println(
            "$newName is $age years old and" +
                    " running with $speed km/h"
        )
    }

    fun printUpdatedAge() {
        printTag("printUpdatedAge()")
        val calendar = Calendar.getInstance()
        val updatedAge = calendar.get(Calendar.YEAR) - yearOfBirth
        println("$name updated age is $updatedAge Y/O")
    }

    /**
     * https://www.youtube.com/watch?v=2V6et2FdS8I&list=PLlxmoA0rQ-LwgK1JsnMsakYNACYGa1cjR&index=17
     */
    fun printRange() {
        printTag("printRange()")

        val r0 = 1..5 // contains numbs 1, 2, 3, 4, 5
        val r0s = 1..5 step 2 // contains numbs 1, 3, 5

        /*
        val r1 = 5 downTo 1 // contains numb 5, 4, 3, 2, 1
        val r1s = 5 downTo 1 step 2 // contains numb 5, 3, 1

        val r1m = 5.downTo(1) step 2 // contains numb 5, 3, 1
        val r1r = 1.rangeTo(5) // contains numb 1, 2, 3, 4, 5
        */

        val r2 = 'a'..'e' // contains chars a, b, c, d, e

        val isPresent = 'c' in r2 // true

        println("$r0 $r0s $isPresent")
    }

    fun printIfAsExpression() {
        printTag("printIfAsExpression()")

        val a = ThreadLocalRandom
            .current()
            .nextLong(100, 111)

        val result = if (a.equals(110)) {
            print("WOA: ")
            "$a is equals to 110"
        } else {
            "$a is not equals to 110"
        }
        println(result)
    }

    fun printWhen() {
        printTag("printWhen()")
        val a = ThreadLocalRandom
            .current()
            .nextInt(0, 10)

        // as statement
        var x: String
        when (a) {
            1 -> {
                x = "Awesome!"
                x += " :)"
            }
            2 -> x = "Great!"
            3 -> x = "Look good"
            4 -> x = "Ok"
            in 5..7 -> x = "Ops!"
            !in 8..10 -> x = "LOL"
            else -> {
                x = "Not possible"
                println("Unknown")
            }
        }
        println(x)

        // as expression
        val y = when (a) {
            1 -> "Nice"
            2 -> "Not bad"
            else -> "What the heck!"
        }
        println(y)
    }

    fun printLoops() {
        printTag("printLoops()")
        println("For")
        for (x in 0..5) {
            println("x: $x")
        }

        println("\nWhile")
        var x = 0
        while (x < 5) {
            println("x: $x")
            x++
        }

        println("\nDo While")
        var y = 0
        do {
            println("y: $y")
            y++
        } while (y < 5)

        println("\nFor with control statement (break)")
        for (z in 0..5) {
            println("z: $z")
            if (z == 3) break
        }

        println("\nFor with control statement (break labeled for)")
        inc@ for (i in 1..3) {
            for (j in 1..3) {
                println("$i & $j")
                if (i == 2 && j == 2) {
                    // `break` only affected in current scope not their
                    // parent scope, instead use `labeled for loop`
                    break@inc
                }
            }
        }

        println("\nFor with control statement (continue)")
        for (z in 0..5) {
            if (z != 3) continue
            println("z: $z")
        }

        println("\nFor with control statement (break labeled for)")
        inc2@ for (i in 1..3) {
            for (j in 1..3) {
                if (i != 2 && j != 2) {
                    // `break` only affected in current scope not their
                    // parent scope, instead use `labeled for loop`
                    continue@inc2
                }
                println("$i & $j")
            }
        }
    }

    fun printFunAsExpression() {
        printTag("printFunAsExpression()")
        val a = Random().nextInt()
        val b = Random().nextInt()
        println(max(a, b))
    }

    private fun max(a: Int, b: Int): Int = if (a > b){
        print("return A=")
        a // last statement is return statement
    } else {
        print("return B=")
        b
    }
}