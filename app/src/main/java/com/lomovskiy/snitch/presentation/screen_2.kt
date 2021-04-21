package com.lomovskiy.snitch.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.navigate

@Composable
fun Screen2(forward: () -> Unit, back: () -> Unit) {

    BackHandler(onBack = back)

    Box(
        modifier = Modifier.clickable(onClick = forward)
    ) {
        Text(text = "Screen 2")
    }

}