package variable

open class MyClassParent

class MyClass : MyClassParent()

interface Bag<in T> {
    fun get(t: T): Boolean
}

class BagImpl : Bag<MyClassParent> {
    override fun get(t: MyClassParent): Boolean {
        TODO("Not yet implemented")
    }
}

val a: Bag<MyClass> = BagImpl()