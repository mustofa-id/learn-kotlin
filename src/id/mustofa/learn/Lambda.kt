package id.mustofa.learn

import java.util.*

// Only initialized when used this prevent waste memory
// can null
val constVal: String by lazy {
    "MyLazyValue"
}

fun main() {
    printTag("BEGIN")

    lateinit var name: String

    name = "OKE"

    println("Late-init: $name")

    println("Lazy value: $constVal")

    printTag("END")
}

@Suppress("unused")
private fun nullSafe() {
    // Null-able variable
    @Suppress("CanBeVal")
    var name: String? = null

    // name = "Habib Mustofa"

    println("My name is $name and length of my name is ${name?.length}") // ? null safe
    // println("My name is $name and length of my name is ${name!!.length}") // !! not safe null aka non-null assertion operator

    name?.let {
        // if null, statement will not invoke
        println("With Let: My name is $name and length of my name is ${name.length}")
    }

    // Elvis operator
    val length = name?.length ?: -1
    println("Elvis: My name length is $length")
}

@Suppress("unused")
private fun listMapAndPredicate() {
    val animals = listOf<Mammalian>(
        Mammalian("Cat"),
        Mammalian("Tiger").apply { speed = 20 }
    ) // Immutable list
    // for (el in animals) println
    for (i in 0 until animals.size) {
        println("Immutable: $i = ${animals[i]}")
    }

    val mammalians = mutableListOf<Mammalian>()
    mammalians.add(Mammalian("Elephant"))
    mammalians.add(Mammalian("Zebra"))
    for (el in mammalians) println("Mutable: $el")

    // listOf, mapOf, setOf, arrayListOf, ArrayList, etc

    val numbs = mapOf<String, Int>(
        "Name" to 2,
        "Age" to 3,
        "Yob" to 4
    )
    println("Maps: $numbs")

    println("\nMap & Filter")
    val filteredAnimals = animals.filter { mammalian ->
        mammalian.speed > 0
    }
    for (el in filteredAnimals) println("Filtered: $el")

    val mapAnimals = animals.map {
        "${it.animalName} has speed ${it.speed * 2}"
    }
    println("Mapped: $mapAnimals")

    val checkIfAllLt10 = animals.all { it.speed < 10 }
    println("All: $checkIfAllLt10")

    val countSpeedEq10 = animals.count { it.speed == 0 }
    println("Count: $countSpeedEq10")
}

@Suppress("unused")
private fun array() {
    val numbers = Array(5) { 0 }
    numbers[2] = 12

    // for (el in numbers) println("Number: $el")

    for (index in 0 until numbers.size) {
        println("Index: $index | Element: ${numbers[index]}")
    }
}

@Suppress("unused")
private fun cat() {
    val cat = Mammalian("Cat").apply {
        food = "Gold Fish"
        speed = 21
    }
    // cat.food = "Fish" // use `with` or `apply` instead
    // cat.speed = 20
    // with(cat) {
    //     food = "Gold Fish"
    //     speed = 21
    // }

    cat.run(object : AnimalAction {
        override fun onRun(speed: Int) {
            println("${cat.animalName} running with speed: $speed")
        }
    })

    // lambda
    cat.walk {
        // cat = Mammalian("New Cat") can mutate inside lambda
        println("$it is now walking")
    }
    cat.eat()
    cat.printKind()
    cat.calculateAge(2016) { age: Int, thisYear: Int ->
        println("${cat.animalName} is $age years old in $thisYear")
    }
}

/**
 * Abstract class
 */
abstract class Animal(open var name: String) {

    var food: String = ""

    open fun eat() {
        println("$name eating a $food")
    }

    abstract fun printKind()
    abstract fun run(action: AnimalAction)
    abstract fun walk(action: (String) -> Unit)
    abstract fun calculateAge(yearOfBirth: Int, action: (Int, Int) -> Unit)
}

/**
 * Interface
 */
interface AnimalAction {
    fun onRun(speed: Int)
}

/**
 * Data class
 */
data class Mammalian(var animalName: String) : Animal(animalName) {

    var speed: Int = 0

    override fun printKind() {
        println("$animalName is a ${javaClass.simpleName}")
    }

    override fun run(action: AnimalAction) {
        action.onRun(speed)
    }

    override fun walk(action: (String) -> Unit /* High order fun */) {
        println("$animalName is preparing to walking...")
        Thread.sleep(1000)
        action(animalName)
    }

    override fun calculateAge(yearOfBirth: Int, action: (Int, Int) -> Unit) {
        val thisYear = Calendar.getInstance().get(Calendar.YEAR)
        val age = thisYear - yearOfBirth
        action(age, thisYear)
    }
}
