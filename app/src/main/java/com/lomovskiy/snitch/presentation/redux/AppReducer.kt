package com.lomovskiy.snitch.presentation.redux

import java.util.*

object AppReducer : Reducer<AppState> {

    override fun reduce(action: Action, state: AppState): AppState {
        when (action) {
            AppAction.AddPassword -> {
                val passwords = state.passwords.toMutableList()
                passwords.add(UUID.randomUUID().toString())
                return state.copy(passwords = passwords)
            }
            AppAction.DeletePassword -> {
                val passwords = state.passwords.toMutableList()
                passwords.removeFirst()
                return state.copy(passwords = passwords)
            }
            else -> {
                return state
            }
        }
    }

}
