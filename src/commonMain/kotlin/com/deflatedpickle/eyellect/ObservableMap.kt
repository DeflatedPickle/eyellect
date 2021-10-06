package com.deflatedpickle.eyellect

import com.deflatedpickle.eyellect.api.Action
import com.deflatedpickle.eyellect.api.Observable

class ObservableMap<K, V>(
    override val handler: (Action, Collection<Map.Entry<K, V>>) -> Unit
) : Observable<Map.Entry<K, V>>, MutableMap<K, V> {
    private val thing = mutableMapOf<K, V>()

    override val size: Int
        get() = thing.size

    override val entries: MutableSet<MutableMap.MutableEntry<K, V>>
        get() = thing.entries
    override val keys: MutableSet<K>
        get() = thing.keys
    override val values: MutableCollection<V>
        get() = thing.values

    override fun containsKey(key: K): Boolean = thing.containsKey(key)
    override fun containsValue(value: V): Boolean = thing.containsValue(value)

    override operator fun get(key: K): V? = thing[key]

    override fun isEmpty(): Boolean = thing.isEmpty()

    override fun clear() {
        val v = entries.toSet()
        thing.clear()
        handler(Action.REMOVE, v)
    }

    override fun put(key: K, value: V): V? {
        val old = if (!thing.contains(key)) null else entries.first { e -> e.key == key }
        val r = thing.put(key, value)

        if (old != null) {
            handler(Action.REPLACE, listOf(old))
        } else {
            handler(Action.ADD, listOf(entries.first { e -> e.key == key }))
        }

        return r
    }

    override fun putAll(from: Map<out K, V>) = thing.putAll(from).apply {
        handler(Action.ADD, entries)
    }

    override fun remove(key: K): V? {
        val v = entries.first { e -> e.key == key }
        val r = thing.remove(key)
        handler(Action.REMOVE, listOf(v))
        return r
    }
}