package com.lomovskiy.snitch.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
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

@Preview
@Composable
fun AddNewPasswordDialogPreview() {
    DialogAddNewPassword(DialogAddNewPasswordState.empty())
}

class DialogAddNewPasswordState(
    val nameValue: String,
    val loginValue: String,
    val passwordValue: String,
    val onPositiveButtonClick: (name: String, login: String, password: String) -> Unit,
    val onNegativeButtonClick: () -> Unit
) {

    companion object {

        fun empty(): DialogAddNewPasswordState {
            return DialogAddNewPasswordState(
                nameValue = "",
                loginValue = "",
                passwordValue = "",
                onPositiveButtonClick = { s1, s2, s3 -> },
                onNegativeButtonClick = {}
            )
        }

    }

}

@Composable
fun DialogAddNewPassword(state: DialogAddNewPasswordState) {

    val nameFieldState = rememberSaveable { mutableStateOf(state.nameValue) }
    val loginFieldState = rememberSaveable { mutableStateOf(state.loginValue) }
    val passwordFieldState = rememberSaveable { mutableStateOf(state.passwordValue) }

    Dialog(
        onDismissRequest = state.onNegativeButtonClick,
    ) {
        Column(
            modifier = Modifier
                .background(color = Color.White)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = nameFieldState.value,
                label = {
                    Text(text = "Name")
                },
                onValueChange = {
                    nameFieldState.value = it
                }
            )
            OutlinedTextField(
                value = loginFieldState.value,
                label = {
                    Text(text = "Login")
                },
                onValueChange = {
                    loginFieldState.value = it
                }
            )
            OutlinedTextField(
                value = passwordFieldState.value,
                label = {
                    Text(text = "Password")
                },
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
                    state.onPositiveButtonClick(nameFieldState.value, loginFieldState.value, passwordFieldState.value)
                }) {
                    Text(text = "OK")
                }
                TextButton(
                    modifier = Modifier.padding(start = 8.dp),
                    onClick = state.onNegativeButtonClick,
                ) {
                    Text(text = "CANCEL")
                }
            }
        }
    }
}