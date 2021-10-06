import com.deflatedpickle.eyellect.ObservableList

fun main() {
    val list = ObservableList<Any> { a, i ->
        println("$a: { ${i.joinToString()} }")
    }
    list.add("hello")
    list.add(42)
    list.add("only numbers")
    list.retainAll { it is Number }
    list.add(7)
    list.clear()
    println(list)
}