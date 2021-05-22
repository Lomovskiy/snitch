package com.lomovskiy.snitch.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lomovskiy.snitch.domain.PasswordEntity
import com.lomovskiy.snitch.domain.repo.PasswordsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ScreenPasswordsState(
    val passwords: List<PasswordEntity>
) {

    companion object {

        fun empty(): ScreenPasswordsState {
            return ScreenPasswordsState(
                passwords = emptyList()
            )
        }

    }

}

@HiltViewModel
class ScreenPasswordsViewModel @Inject constructor(
    private val passwordsRepo: PasswordsRepo
) : ViewModel() {

    private var state = MutableStateFlow(ScreenPasswordsState.empty())

    init {
        viewModelScope.launch {
            fetch()
        }
    }

    fun getState(): StateFlow<ScreenPasswordsState> {
        return state
    }

    fun addNewPassword() {
        viewModelScope.launch {
            passwordsRepo.create(PasswordEntity.stub())
            fetch()
        }
    }

    private suspend fun fetch() {
        viewModelScope.launch {
            val passwords = passwordsRepo.getAll()
            state.value = state.value.copy(passwords = passwords)
        }
    }

}

@Composable
fun ScreenPasswords(
    paddingValues: PaddingValues,
    vm: ScreenPasswordsViewModel
) {

    val state by vm.getState().collectAsState()

    Scaffold(
        modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding()),
        floatingActionButton = {
            ButtonAddNewPassword {
                vm.addNewPassword()
            }
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

}

@Preview
@Composable
fun ButtonAddNewPasswordPreview() {
    ButtonAddNewPassword {}
}

@Composable
fun ButtonAddNewPassword(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick
    ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = null)
    }
}
