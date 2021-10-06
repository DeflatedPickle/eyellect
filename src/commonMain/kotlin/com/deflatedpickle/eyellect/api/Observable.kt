package com.deflatedpickle.eyellect.api

interface Observable<T> {
    val handler: (Action, Collection<T>) -> Unit
}