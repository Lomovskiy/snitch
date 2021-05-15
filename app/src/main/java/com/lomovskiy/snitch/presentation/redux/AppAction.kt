package com.lomovskiy.snitch.presentation.redux

sealed class AppAction : Action {

    object AddPassword : AppAction()

    object DeletePassword : AppAction()

}
