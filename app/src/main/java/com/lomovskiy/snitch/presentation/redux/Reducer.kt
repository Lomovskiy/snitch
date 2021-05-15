package com.lomovskiy.snitch.presentation.redux

interface Reducer<S> {

    fun reduce(action: Action, state: S): S

}
