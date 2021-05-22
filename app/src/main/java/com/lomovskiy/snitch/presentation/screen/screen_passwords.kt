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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lomovskiy.snitch.domain.PasswordEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ScreenPasswordsViewModel @Inject constructor() : ViewModel() {

    private val state = MutableStateFlow(ScreenPasswordsState.empty())

    fun getState(): StateFlow<ScreenPasswordsState> {
        return state
    }

}

data class ScreenPasswordsState(
    val passwords: List<PasswordEntity>
) {

    companion object {

        fun empty(): ScreenPasswordsState {
            return ScreenPasswordsState(
                passwords = emptyList<PasswordEntity>()
            )
        }

    }

}

@Composable
fun ScreenPasswords(
    paddingValues: PaddingValues,
    vm: ScreenPasswordsViewModel
) {

    val state = vm.getState().collectAsState()

    Scaffold(
        modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding()),
        floatingActionButton = {
            ButtonAddNewPassword {
//                vm.onAddPassword()
            }
        }
    ) {
        LazyColumn {
            items(state.value.passwords.size) { idx: Int ->
                Text(text = state.value.passwords[idx].password)
            }
        }
    }

    if (state.value.passwords.isEmpty()) {
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
