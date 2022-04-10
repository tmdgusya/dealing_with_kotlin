package `pure-function`

data class Person(
    val name: String
)

fun main() {
    /**
     * 같은 정의역에 같은 공역이니까 순수함수?
     */
    val p1 = Person(name = "roach")
    val p2 = Person(name = "roach")

    println(p1 == p2)
}
