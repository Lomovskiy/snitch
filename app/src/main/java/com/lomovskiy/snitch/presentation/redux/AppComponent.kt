package com.lomovskiy.snitch.presentation.redux

object AppComponent {

    val appStore = AppStore(
        AppState(emptyList()), emptyList(), listOf(AppReducer)
    )

}