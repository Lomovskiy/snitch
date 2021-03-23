package com.lomovskiy.snitch.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lomovskiy.snitch.AppLoader
import com.lomovskiy.snitch.domain.PasswordEntity
import com.lomovskiy.snitch.domain.PasswordsInteractor
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.*

class ScreenPasswordsViewModel(
    private val interactor: PasswordsInteractor = AppLoader.interactor
) : ViewModel() {

    fun getPasswords(): StateFlow<List<PasswordEntity>> {
        return interactor.subscribeOnPasswordsUpdates()
    }

    fun addNewPassword() {
        viewModelScope.launch {
            interactor.savePassword(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString()
            )
        }
    }

}

@Composable
fun AppBar(title: String) {
    TopAppBar(title = {
        Text(text = title)
    })
}

@ExperimentalComposeUiApi
@Composable
fun ScreenPasswords(viewModel: ScreenPasswordsViewModel) {

    val s = viewModel.getPasswords().collectAsState(initial = emptyList())

    Scaffold(
        topBar = { AppBar(title = "Passwords") }
    ) {
        Box(
            modifier = Modifier.padding(16.dp)
        ) {
            LazyColumn {
                items(s.value.size, itemContent = { idx ->
                    ListItem(s.value[idx])
                })
            }
            FloatingActionButton(
                onClick = viewModel::addNewPassword
            ) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        }
    }

}

@Composable
fun ListItem(passwordEntity: PasswordEntity) {
    Text(text = passwordEntity.login)
}