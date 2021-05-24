package com.lomovskiy.snitch.presentation.screen.passwords

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CardPasswordEntry(
    name: String,
    onClick: () -> Unit = {}
) {

    Card(
        modifier = Modifier
            .clickable(onClick = onClick)
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Name:")
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = name
            )
        }
    }

}

@Preview
@Composable
fun CardPasswordEntryPreview() {
    CardPasswordEntry(name = "Name")
}