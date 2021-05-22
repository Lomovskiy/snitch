package com.lomovskiy.snitch.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

data class ScreenSettingsState(
    val name: String
) {

    companion object {

        fun empty(): ScreenSettingsState {
            return ScreenSettingsState("Settings")
        }

    }

}

@HiltViewModel
class ScreenSettingsViewModel @Inject constructor() : ViewModel() {

    private val state = MutableStateFlow(ScreenSettingsState.empty())

    fun getState(): StateFlow<ScreenSettingsState> {
        return state
    }

}

@Composable
fun ScreenSettings(
    vm: ScreenSettingsViewModel
) {

    val state by vm.getState().collectAsState()

    Box {
        Text(text = vm.toString())
    }

}
