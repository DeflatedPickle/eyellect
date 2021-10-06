package com.deflatedpickle.eyellect.api

abstract class AbstractObservableCollection<T>(
    private val thing: MutableCollection<T>,
    override val handler: (Action, Collection<T>) -> Unit,
) : ObservableCollection<T> {
    override val size: Int
        get() = thing.size

    override fun toString() = thing.toString()

    override fun contains(element: T) = thing.contains(element)
    override fun containsAll(elements: Collection<T>) = thing.containsAll(elements)
    override fun isEmpty() = thing.isEmpty()
    override fun iterator(): MutableIterator<T> = thing.iterator()

    override fun add(element: T) = thing.add(element).apply {
        if (this) {
            handler(Action.ADD, listOf(element))
        }
    }

    override fun addAll(elements: Collection<T>) = thing.addAll(elements).apply {
        if (this) {
            handler(Action.ADD, elements)
        }
    }

    override fun clear() {
        val v = thing.toList()
        thing.clear()
        handler(Action.REMOVE, v)
    }

    override fun remove(element: T) = thing.remove(element).apply {
        if (this) {
            handler(Action.REMOVE, listOf(element))
        }
    }

    override fun removeAll(elements: Collection<T>) = thing.removeAll(elements.toSet()).apply {
        if (this) {
            handler(Action.REMOVE, elements)
        }
    }

    override fun retainAll(elements: Collection<T>): Boolean {
        val v = thing.filter { it !in elements }
        return thing.retainAll(elements.toSet()).apply {
            if (this) {
                handler(Action.REMOVE, v)
            }
        }
    }

    fun retainAll(predicate: (T) -> Boolean): Boolean {
        val v = thing.filterNot(predicate)
        return thing.retainAll(predicate).apply {
            if (this) {
                handler(Action.REMOVE, v)
            }
        }
    }
}