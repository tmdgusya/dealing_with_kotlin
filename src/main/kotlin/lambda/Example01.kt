package lambda

fun main() {
    val alice = Person("Alice", 20, "Amsterdam").apply {
        println(toString())
        moveTo("Seoul")
        incrementAge()
        println(toString())
    }

    val Alice = Person("Alice", 20, "Amsterdam").apply {
        moveTo("London")
        incrementAge()
    }

    val roach = Person("Alice", 20, "Amsterdam").fakeApply {
        moveTo("London")
        incrementAge()
    }

    println(alice)
}

class Person(
    val name: String,
    var age: Int,
    var address: String
) {

    fun moveTo(moveAddress: String) {
        this.address = moveAddress
    }

    fun incrementAge() {
        this.age += 1
    }

    inline fun <T, R> fakeWith(receiver: T, block: T.() -> R): R {
        return receiver.block()
    }

    override fun toString(): String {
        return "Person(name='$name', age=$age, address='$address')"
    }
}

inline fun Person.fakeApply(block: Person.() -> Unit): Person {
    block()
    return this
}
