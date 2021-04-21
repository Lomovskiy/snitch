package com.lomovskiy.snitch.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun Screen3(back: () -> Unit) {

    BackHandler(onBack = back)

    Box {
        Text(text = "Screen 3")
    }

}