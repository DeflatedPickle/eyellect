package com.deflatedpickle.eyellect

import com.deflatedpickle.eyellect.api.AbstractObservableCollection
import com.deflatedpickle.eyellect.api.Action

class ObservableSet<T>(
    handler: (Action, Collection<T>) -> Unit,
    thing: MutableSet<T> = mutableSetOf(),
) : AbstractObservableCollection<T>(thing, handler) {
    constructor(handler: (Action, Collection<T>) -> Unit) : this(handler, mutableSetOf())
}