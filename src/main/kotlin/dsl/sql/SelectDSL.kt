package dsl.sql

import java.util.function.Predicate

class QueryDSL(
    select: () -> SelectDSL
)

class SelectDSL {

    private var column: Columns = Columns()
    private var from: From? = null

    fun columns(column: Columns): SelectDSL {
        this.column = column
        return this
    }

    fun columns(addColum: Columns.() -> Unit): SelectDSL {
        this.column.addColum()
        return this
    }

    infix fun from(table: String): From {
        this.from = From(table)
        return this.from!!
    }

    fun makeQuery(): String {
        var query = "SELECT "
        query += column.toString()
        query += from?.toString()
        return query
    }
}

fun select(selectDSL: SelectDSL.() -> Unit): SelectDSL = SelectDSL().apply(selectDSL)

class Columns {

    private val columns = mutableListOf<String>()

    operator fun String.unaryPlus() = columns.add(this)

    override fun toString(): String {
        return columns.joinToString(", ")
    }
}

class From(
    val tableName: String
) {

    override fun toString(): String {
        return " From $tableName"
    }
}

class Where(
    val predicate: String
)

fun main() {
    val select = select {
        columns {
            +"name"
            +"age"
        }
        from("user")
    }

    println(select.makeQuery())
}
