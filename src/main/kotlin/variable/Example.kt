package variable

open class A : Z()

class B : A()

open class Z

class C<T>

fun createC(arg: C<out A>): C<out A> {
    return arg
}

fun createC2(arg: C<in A>): C<in A> {
    return arg
}

fun main() {
    val a1: A = B()
    val a2: A = B()

    val c1 = createC(C<B>())
    val c2 = createC2(C<Z>())
}
