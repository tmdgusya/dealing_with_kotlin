package high_order_functions

fun main() {
    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    val combine = { acc: Int, nextElement: Int -> acc + nextElement }

    val result = list.accumulate(
        0,
        combine
    )

    println(result)
}

fun <T, R> Collection<T>.accumulate(
    initialValue: R,
    combine: (acc: R, nextElement: T) -> R
): R {
    var accumulator = initialValue
    for (ele: T in this) {
        accumulator = combine(accumulator, ele)
    }
    return accumulator
}
