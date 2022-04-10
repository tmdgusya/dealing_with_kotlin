package variable

open class D

open class A : D()

class B : A()

class C<in T>

class Z<out T>

fun createContravariant(objects: C<A>): C<A> {
    return objects
}

fun createCovariant(objects: Z<B>): Z<A> {
    return objects
}

fun main() {
    val a1: A = B()
    val a2: A = B()

    val b1: C<B> = createContravariant(C<A>())
    val a3: Z<A> = createCovariant(Z<B>())
}
