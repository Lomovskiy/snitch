package com.lomovskiy.snitch.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.tooling.preview.Preview
import com.lomovskiy.snitch.data.PasswordsRepoImpl
import com.lomovskiy.snitch.domain.PasswordsInteractorImpl
import com.lomovskiy.snitch.presentation.theme.SnitchTheme

@ExperimentalComposeUiApi
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SnitchTheme(darkTheme = false) {
                Surface {
                    NavigationHolder()
                }
            }
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun NavigationHolder() {
    ScreenPasswords(ScreenPasswordsViewModel())
}
