package dsl

fun main() {
    val dslSandwich = sandwich {
        with type "toasted"
        with bread "baguette"

        fillings {
            +"cheese"
            +"ham"
            +"tomato"
        }

        if (this.fillings.count() >= 3) {
            filling("lettuce (free)")
        }

        dressings {
            +"Salt"
            +"Pepper"
        }

        sideOrders {
            +"French Fries"
            +"Chicken Nuggets"
            +"Chicken Soup"
        }
    }.construct()

    println(dslSandwich.receipt())
}

fun sandwich(launchOrder: SandwichDSL.() -> Unit): SandwichDSL = SandwichDSL().apply(launchOrder)

class SandwichDSL {
    var type = "simple"
    var bread: String = "white"
    internal val fillings = Fillings()
    private val dressings = Dressings()
    private val sides = SideOrders()
    public val with = this

    operator fun invoke(launchOrder: SandwichDSL.() -> Unit): SandwichDSL = SandwichDSL().apply(launchOrder)

    infix fun type(sandwichType: String): SandwichDSL {
        type = sandwichType
        return this
    }

    infix fun bread(breadType: String): SandwichDSL {
        bread = breadType
        return this
    }

    infix fun filling(fillingToAdd: String): SandwichDSL {
        fillings.apply { +fillingToAdd }
        return this
    }

    fun dressings(toAdd: Dressings.() -> Unit): SandwichDSL {
        dressings.toAdd()
        return this
    }

    fun fillings(fillingsToAdd: Fillings.() -> Unit): SandwichDSL {
        fillings.fillingsToAdd()
        return this
    }

    fun sideOrders(sideOrderToAdd: SideOrders.() -> Unit): SandwichDSL {
        sides.sideOrderToAdd()
        return this
    }

    fun construct(): SandwichDSL {
        println("Make Sandwich By Own Receipt!")
        return this
    }

    fun receipt(): String {
        var receipt = "$type sandwich on $bread bread"
        receipt += "\n\t" + fillings.receipt()
        receipt += "\n\t" + dressings.receipt()
        receipt += "\n\t" + sides.receipt()
        return "Sandwich receipt \n $receipt"
    }
}

class SideOrders {
    private val sideOrders = mutableListOf<String>()

    operator fun String.unaryPlus() = sideOrders.add(this)

    fun side(sideOrder: String) {
        sideOrders.add(sideOrder)
    }

    fun receipt(): String {
        return if (sideOrders.isEmpty()) "with no sides"
        else " with sides: " + sideOrders.joinToString(", ")
    }

    fun set(listOfSideOrders: List<String>) {
        sideOrders.clear()
        sideOrders.addAll(listOfSideOrders)
    }
}

class Dressings {
    private val dressings = mutableListOf<String>()

    operator fun String.unaryPlus() = dressings.add(this)

    fun receipt(): String {
        return if (dressings.isEmpty()) "with no dressings"
        else " with dressings: " + dressings.joinToString(", ")
    }

    fun set(listOfDressings: List<String>) {
        dressings.clear()
        dressings.addAll(listOfDressings)
    }
}

class Fillings {
    private val fillings = mutableListOf<String>()

    operator fun String.unaryPlus() = fillings.add(this)

    fun receipt(): String {
        return if (fillings.isEmpty()) "with no fillings"
        else " with fillings: " + fillings.joinToString(", ")
    }

    fun set(listOfFillings: List<String>) {
        fillings.clear()
        fillings.addAll(listOfFillings)
    }

    fun count(): Int {
        return fillings.size
    }
}

/**
 * Print
 *
 * Make Sandwich By Own Receipt!
 Sandwich receipt
 toasted sandwich on baguette bread
 with fillings: cheese, ham, tomato, lettuce (free)
 with dressings: Salt, Pepper
 with sides: French Fries, Chicken Nuggets, Chicken Soup
 */

/**
 * Reference
 *
 * @see https://www.youtube.com/watch?v=lnTjgz9Eor0
 */