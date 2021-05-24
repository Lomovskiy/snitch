package com.lomovskiy.snitch.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import java.util.*

@Composable
fun DialogAddNewPassword(
    onPositiveButtonClick: (name: String, login: String, password: String) -> Unit,
    onNegativeButtonClick: () -> Unit
) {

    val nameFieldState = rememberSaveable { mutableStateOf("") }
    val loginFieldState = rememberSaveable { mutableStateOf("") }
    val passwordFieldState = rememberSaveable { mutableStateOf("") }

    Dialog(
        onDismissRequest = onNegativeButtonClick,
    ) {
        Column(
            modifier = Modifier
                .background(color = Color.White)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                value = nameFieldState.value,
                label = {
                    Text(text = "Name")
                },
                onValueChange = {
                    nameFieldState.value = it
                }
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                value = loginFieldState.value,
                label = {
                    Text(text = "Login")
                },
                onValueChange = {
                    loginFieldState.value = it
                }
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                value = passwordFieldState.value,
                label = {
                    Text(text = "Password")
                },
                trailingIcon = {
                    IconButton(
                        modifier = Modifier.wrapContentSize(),
                        onClick = {
                            passwordFieldState.value = UUID.randomUUID().toString()
                        }
                    ) {
                        Icon(imageVector = Icons.Default.Refresh, contentDescription = "")
                    }
                },
                maxLines = 1,
                onValueChange = {
                    passwordFieldState.value = it
                }
            )
            Row(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = {
                    onPositiveButtonClick(nameFieldState.value, loginFieldState.value, passwordFieldState.value)
                }) {
                    Text(text = "OK")
                }
                TextButton(
                    modifier = Modifier.padding(start = 8.dp),
                    onClick = onNegativeButtonClick,
                ) {
                    Text(text = "CANCEL")
                }
            }
        }
    }
}

@Preview
@Composable
fun DialogAddNewPasswordPreview() {
    DialogAddNewPassword(
        onPositiveButtonClick = { name, login, password ->  },
        onNegativeButtonClick = {}
    )
}