package com.lomovskiy.snitch.presentation

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun AppBarPreview() {
    AppBar(title = "AppBarPreview")
}

@Composable
fun AppBar(title: String) {
    TopAppBar(
        title = {
            Text(
                text = title
            )
        }
    )
}