package com.deflatedpickle.eyellect.api

interface ObservableCollection<T> : MutableCollection<T> {
    val handler: (Action, Collection<T>) -> Unit
}