package com.deflatedpickle.eyellect

import com.deflatedpickle.eyellect.api.AbstractObservableCollection
import com.deflatedpickle.eyellect.api.Action

class ObservableList<T>(
    handler: (Action, Collection<T>) -> Unit,
    thing: MutableList<T> = mutableListOf(),
) : AbstractObservableCollection<T>(thing, handler) {
    constructor(handler: (Action, Collection<T>) -> Unit) : this(handler, mutableListOf())
}