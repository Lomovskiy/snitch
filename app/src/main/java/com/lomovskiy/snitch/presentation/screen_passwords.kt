package com.lomovskiy.snitch.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lomovskiy.snitch.domain.PasswordEntity
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ScreenPasswordsViewModel : ViewModel() {

//    private val states = MutableStateFlow(ScreenPasswordsState.empty())
//
//    fun getStates(): StateFlow<ScreenPasswordsState> {
//        return states
//    }
//
//    fun handleAction(action: Action) {
//        viewModelScope.launch {
//            when (action) {
//                Action.OnDialogAddNewPasswordCancelButtonClicked -> {
//                    states.emit(states.value.copy(dialogAddNewPasswordState = null))
//                }
//                is Action.OnDialogAddNewPasswordOkButtonClicked -> {
//                    states.emit(states.value.copy(dialogAddNewPasswordState = null))
//                }
//                Action.OnAddNewPasswordButtonClicked -> {
//                    states.emit(states.value.copy(
//                        dialogAddNewPasswordState = DialogAddNewPasswordState(
//                            nameValue = "",
//                            loginValue = "",
//                            passwordValue = "",
//                            onPositiveButtonClick = { name: String, login: String, password: String ->
//                                handleAction(Action.OnDialogAddNewPasswordOkButtonClicked(name, login, password))
//                            },
//                            onNegativeButtonClick = {
//                                handleAction(Action.OnDialogAddNewPasswordCancelButtonClicked)
//                            }
//                        )
//                    ))
//                }
//            }
//        }
//    }
//
//    sealed class Action {
//        class OnDialogAddNewPasswordOkButtonClicked(val name: String, val login: String, val password: String) : Action()
//        object OnDialogAddNewPasswordCancelButtonClicked : Action()
//        object OnAddNewPasswordButtonClicked : Action()
//    }

}

data class ScreenPasswordsState(
    val dialogAddNewPasswordState: DialogAddNewPasswordState?,
    val passwords: List<PasswordEntity>
) {

    companion object {
        fun empty(): ScreenPasswordsState {
            return ScreenPasswordsState(
                dialogAddNewPasswordState = null,
                passwords = emptyList()
            )
        }
    }

}

@ExperimentalComposeUiApi
@Composable
fun ScreenPasswords(viewModel: ScreenPasswordsViewModel) {

//    var dialogStates: ScreenPasswordsState by viewModel.getStates().collectAsState()

//    Scaffold(
//        topBar = { AppBar(title = "Passwords") },
//        content = {
//            Box {
//                LazyColumn(
//                    contentPadding = PaddingValues(16.dp),
//                    verticalArrangement = Arrangement.spacedBy(8.dp)
//                ) {
//                    items(state.value.passwords.size, itemContent = { idx ->
//                        ListItem(state.value.passwords[idx], {})
//                    })
//                }
//                state.value.dialogAddNewPasswordState?.let { state: DialogAddNewPasswordState ->
//                    DialogAddNewPassword(state)
//                }
//            }
//        },
//        floatingActionButton = {
//            FloatingActionButton(
//                onClick = {
//                    viewModel.handleAction(ScreenPasswordsViewModel.Action.OnAddNewPasswordButtonClicked)
//                }
//            ) {
//                Icon(Icons.Default.Add, contentDescription = null)
//            }
//        }
//    )

}

@Preview
@Composable
fun ListItemPreview(
    passwordEntity: PasswordEntity = PasswordEntity.stub(),
    onClick: () -> Unit = {}
) {
    ListItem(passwordEntity, onClick)
}

@Composable
fun ListItem(passwordEntity: PasswordEntity, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = 4.dp
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    style = TextStyle(color = Color.Gray),
                    text = "Name:"
                )
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = passwordEntity.name
                )
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)) {
                Text(
                    style = TextStyle(color = Color.Gray),
                    text = "Login:"
                )
                Text(
                    modifier = Modifier.padding(PaddingValues(start = 8.dp)),
                    text = passwordEntity.login
                )
            }
        }
    }
}