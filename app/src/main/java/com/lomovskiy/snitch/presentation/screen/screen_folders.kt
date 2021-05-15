package com.lomovskiy.snitch.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel

@Composable
fun ScreenFolders() {

    Box {
        Text(text = "Folders")
    }

}

class ScreenFoldersViewModel : ViewModel()
