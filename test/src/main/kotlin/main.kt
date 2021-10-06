import com.deflatedpickle.eyellect.ObservableList
import com.deflatedpickle.eyellect.ObservableMap
import com.deflatedpickle.eyellect.ObservableSet

fun main() {
    val list = ObservableList<Any> { a, i ->
        println("$a: { ${i.joinToString()} }")
    }
    println("LIST\n-----")
    list.add("hello")
    list.add(42)
    list.add("only numbers")
    list.retainAll { it is Number }
    list.add(7)
    list.clear()
    println(list)

    val set = ObservableSet<Int> { a, i ->
        println("$a: { ${i.joinToString()} }")
    }
    println("SET\n-----")
    set.add(42)
    set.add(42)
    set.add(42)
    set.add(7)
    println(set)

    val map = ObservableMap<String, Any> { a, i ->
        println("$a: { ${i.joinToString()} }")
    }
    println("MAP\n-----")
    map["i"] = "replaceable"
    map["i"] = "see?"
    map["you"] = "everything"
    map["me"] = "you"
    map.clear()
}