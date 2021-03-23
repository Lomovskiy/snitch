package com.lomovskiy.snitch.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
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
        topBar = { AppBar(title = "Passwords") },
        content = {
            Box {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(s.value.size, itemContent = { idx ->
                        ListItem(s.value[idx], {})
                    })
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = viewModel::addNewPassword
            ) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        }
    )

}

@Preview()
@Composable
fun ListItemPreview(
    passwordEntity: PasswordEntity = PasswordEntity(
        "a@lomovskiy.com",
        "qwerty"
    ),
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
        Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    style = TextStyle(color = Color.Gray),
                    text = "Login:"
                )
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = passwordEntity.login
                )
            }
            Row(modifier = Modifier.fillMaxWidth().padding(top = 8.dp)) {
                Text(
                    style = TextStyle(color = Color.Gray),
                    text = "Password:"
                )
                Text(
                    modifier = Modifier.padding(PaddingValues(start = 8.dp)),
                    text = passwordEntity.password
                )
            }
        }
    }
}