package com.lomovskiy.snitch.presentation.redux

typealias Next<S> = (Action, S) -> Action

interface Middleware<S> {

    fun handleAction(state: S, action: Action, next: Next<S>): Action

}
