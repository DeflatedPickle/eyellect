package com.deflatedpickle.eyellect

import com.deflatedpickle.eyellect.api.Action
import com.deflatedpickle.eyellect.api.ObservableCollection

class ObservableList<T>(
    override val handler: (Action, Collection<T>) -> Unit,
) : ObservableCollection<T> {
    private val list = mutableListOf<T>()

    override val size: Int
        get() = list.size

    override fun toString() = list.toString()

    override fun contains(element: T) = list.contains(element)
    override fun containsAll(elements: Collection<T>) = list.containsAll(elements)
    override fun isEmpty() = list.isEmpty()
    override fun iterator(): MutableIterator<T> = list.iterator()

    override fun add(element: T) = list.add(element).apply {
        if (this) {
            handler(Action.ADD, listOf(element))
        }
    }

    override fun addAll(elements: Collection<T>) = list.addAll(elements).apply {
        if (this) {
            handler(Action.ADD, elements)
        }
    }

    override fun clear() {
        val v = list.toList()
        list.clear()
        handler(Action.REMOVE, v)
    }

    override fun remove(element: T) = list.remove(element).apply {
        if (this) {
            handler(Action.REMOVE, listOf(element))
        }
    }

    override fun removeAll(elements: Collection<T>) = list.removeAll(elements).apply {
        if (this) {
            handler(Action.REMOVE, elements)
        }
    }

    override fun retainAll(elements: Collection<T>): Boolean {
        val v = list.filter { it !in elements }
        return list.retainAll(elements).apply {
            if (this) {
                handler(Action.REMOVE, v)
            }
        }
    }

    fun retainAll(predicate: (T) -> Boolean): Boolean {
        val v = list.filterNot(predicate)
        return list.retainAll(predicate).apply {
            if (this) {
                handler(Action.REMOVE, v)
            }
        }
    }
}