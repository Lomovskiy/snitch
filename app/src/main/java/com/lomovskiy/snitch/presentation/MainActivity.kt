package com.lomovskiy.snitch.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.lomovskiy.snitch.data.PasswordsRepoImpl
import com.lomovskiy.snitch.domain.PasswordsInteractorImpl
import com.lomovskiy.snitch.presentation.theme.SnitchTheme

@ExperimentalComposeUiApi
class MainActivity : AppCompatActivity() {

    private val screenPasswordsVM: ScreenPasswordsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SnitchTheme(darkTheme = false) {
                Surface {
                    ScreenPasswords(screenPasswordsVM)
                }
            }
        }
    }
}
