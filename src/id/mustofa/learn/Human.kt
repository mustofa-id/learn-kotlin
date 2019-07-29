package id.mustofa.learn

import java.util.*

fun main() {
    val santoni = Human("Xiaomi", "Kids")
    santoni.name = "Santoni"
    santoni.kind = "OKE"
    println(santoni)

    println()

    val kenzo = Human("Xiaomi", "Kenzo", 1993)
    println("$kenzo with age: ${kenzo.getAge()}")

    val superMan = SuperMan("Xiaomi", "Tisort", 24.0)
    println("$superMan with power: ${superMan.getAge()}")
    superMan.walk()

    superMan.onRun(object : HumanAction {
        override fun walk() {
            println("Running from object lambda")
        }
    })

    // Lambda
    superMan.onWalk(200) {
        println("Walking from object lambda and $it")
    }

    // object destruction
    val (initName, kind) = superMan.copy()
    println("$initName $kind")


    HumanData.name = "HumanData Name"
    HumanData.printName()
    HumanData.name = "Update HumanData"
    HumanData.printName()
    HumanData.printTitle("WOA")
    HumanData.status = "ACTIVE"
    HumanData.printStatus()

    HumanCompanion.name = "UnderCover"
    HumanCompanion.printName()
}

// can also: class Human constructor(kind: String) {}
// called `primary constructor`
// declaring properties (with val/var) only allowed in primary constructor
// Access modifier: Public (default), protected, private, internal (only in module)
open class Human(initName: String, open var kind: String) {

    var name: String = ""
    protected var yearOfBirth: Int = 0

    init {
        // from here we can access `primary constructor` args
        this.name = initName
//        println("Initial Human { initName: $initName, kind: $kind }")
    }

    constructor(initName: String, kind: String, yearOfBirth: Int) : this(initName, kind) {
        this.yearOfBirth = yearOfBirth
    }

    open fun getAge(): Int {
        return Calendar.getInstance().get(Calendar.YEAR) - yearOfBirth
    }

    override fun toString(): String {
        return "Human { initName: $name, kind: $kind }"
    }

    fun onRun(action: HumanAction) {
        action.walk()
    }

    fun onWalk(speed: Int, action: (Int) -> Unit) {
        action(getAge()) // Higher order fun
        print(" with speed $speed")
    }
}

interface HumanAction {

    fun walk()
}

// Data is data (comparator, toString, copyable/cloneable) dll
data class SuperMan(
    val initName: String,
    override var kind: String,
    private val punchPower: Double
) : Human(initName, kind), HumanAction {

    override fun walk() {
        println("$name is walking...")
    }

    fun getPunchPower(): Double {
        return this.punchPower * 100
    }

    override fun getAge(): Int {
        this.yearOfBirth = 1995
        return super.getAge()
    }
}


// https://www.youtube.com/watch?v=6Gtsp2M8rr8&list=PLlxmoA0rQ-LwgK1JsnMsakYNACYGa1cjR&index=45
object HumanData : HumanExt() {
    var name: String = ""

    init {
        // available
    }

    fun printName() {
        println(name)
    }

    fun printStatus() {
        println("HumanData status: $status")
    }

    override fun printTitle(title: String) {
        super.printTitle(title)
        println("HumanData title: $title")
    }
}

open class HumanExt {

    var status: String = ""

    open fun printTitle(title: String) {
        println("HumanExt title: $title")
    }
}

class HumanCompanion {

    var title: String = ""

    companion object Human {
        var name: String = ""

        @JvmStatic // available for Java as static method
        fun printName() {
            println(this.name)
        }
    }
}