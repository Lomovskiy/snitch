package com.lomovskiy.snitch.presentation.screen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lomovskiy.snitch.domain.PasswordEntity
import com.lomovskiy.snitch.domain.repo.PasswordsRepo
import com.lomovskiy.snitch.presentation.DialogAddNewPassword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

data class ScreenPasswordsState(
    val passwords: List<PasswordEntity>,
    val isShowingAddNewPasswordDialog: Boolean
) {

    companion object {

        fun empty(): ScreenPasswordsState {
            return ScreenPasswordsState(
                passwords = emptyList(),
                isShowingAddNewPasswordDialog = false
            )
        }

    }

}

@HiltViewModel
class ScreenPasswordsViewModel @Inject constructor(
    private val passwordsRepo: PasswordsRepo
) : ViewModel() {

    private val state = MutableStateFlow(ScreenPasswordsState.empty())

    init {

        viewModelScope.launch {
            passwordsRepo.getAll().collect {
                state.value = state.value.copy(passwords = it)
            }
        }

    }

    fun getState(): Flow<ScreenPasswordsState> {
        return state
    }

    fun addNewPassword() {
        viewModelScope.launch {
            state.value = state.value.copy(isShowingAddNewPasswordDialog = true)
        }
    }

    fun newPasswordAdded(name: String, login: String, password: String) {
        viewModelScope.launch {
            state.value = state.value.copy(isShowingAddNewPasswordDialog = false)
            passwordsRepo.create(PasswordEntity(UUID.randomUUID().toString(), name, login, password))
        }
    }

    fun dialogAddNewPasswordClose() {
        viewModelScope.launch {
            state.value = state.value.copy(isShowingAddNewPasswordDialog = false)
        }
    }

}

@Preview
@Composable
fun ScreenPasswordsContentPreview() {
    ScreenPasswordsContent(
        padding = 16.dp,
        state = ScreenPasswordsState.empty(),
        onAddNewPassword = {},
        newPasswordAdded = { name: String, login: String, password: String -> },
        dialogAddNewPasswordClose = {}
    )
}

@Composable
fun ScreenPasswordsContent(
    padding: Dp,
    state: ScreenPasswordsState,
    onAddNewPassword: () -> Unit,
    newPasswordAdded: (name: String, login: String, password: String) -> Unit,
    dialogAddNewPasswordClose: () -> Unit
) {
    Scaffold(
        modifier = Modifier.padding(bottom = padding),
        floatingActionButton = {
            ButtonAddNewPassword(onAddNewPassword)
        }
    ) {
        LazyColumn {
            items(state.passwords.size) { idx: Int ->
                Text(text = state.passwords[idx].id)
            }
        }
    }

    if (state.passwords.isEmpty()) {
        Box {
            Text(text = "Passwords")
        }
    }

    if (state.isShowingAddNewPasswordDialog) {
        DialogAddNewPassword(
            onPositiveButtonClick = newPasswordAdded,
            onNegativeButtonClick = dialogAddNewPasswordClose
        )
    }

}

@Composable
fun ScreenPasswords(
    paddingValues: PaddingValues,
    vm: ScreenPasswordsViewModel
) {

    val state by vm.getState().collectAsState(initial = ScreenPasswordsState.empty())

    ScreenPasswordsContent(
        padding = paddingValues.calculateBottomPadding(),
        state = state,
        onAddNewPassword = vm::addNewPassword,
        newPasswordAdded = vm::newPasswordAdded,
        dialogAddNewPasswordClose = vm::dialogAddNewPasswordClose
    )

}

@Composable
fun ButtonAddNewPassword(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick
    ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = null)
    }
}
