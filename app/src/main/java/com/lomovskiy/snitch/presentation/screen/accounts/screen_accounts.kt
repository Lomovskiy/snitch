package com.lomovskiy.snitch.presentation.screen.accounts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import com.lomovskiy.snitch.domain.AccountEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

data class ScreenAccountsState(
    val isLoading: Boolean,
    val accounts: List<AccountEntity>
) {

    companion object {

        fun loading(): ScreenAccountsState {
            return ScreenAccountsState(
                isLoading = true,
                accounts = emptyList()
            )
        }

    }

}

@HiltViewModel
class ScreenAccountsViewModel @Inject constructor() : ViewModel() {

    private val state = MutableStateFlow(ScreenAccountsState.loading())

    fun getState(): StateFlow<ScreenAccountsState> {
        return state
    }

}

@Composable
fun ScreenAccounts(
    vm: ScreenAccountsViewModel
) {

    val state by vm.getState().collectAsState()

    if (state.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
        return
    }

    Scaffold {

    }

}