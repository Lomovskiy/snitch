package com.lomovskiy.snitch.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.*
import com.lomovskiy.snitch.R
import com.lomovskiy.snitch.presentation.redux.AppAction
import com.lomovskiy.snitch.presentation.redux.AppReducer
import com.lomovskiy.snitch.presentation.redux.AppState
import com.lomovskiy.snitch.presentation.redux.AppStore
import com.lomovskiy.snitch.presentation.screen.ScreenFolders
import com.lomovskiy.snitch.presentation.screen.ScreenPasswords
import com.lomovskiy.snitch.presentation.screen.ScreenPasswordsViewModel
import com.lomovskiy.snitch.presentation.screen.ScreenSettings
import com.lomovskiy.snitch.presentation.theme.SnitchTheme
import kotlinx.coroutines.flow.StateFlow

interface NavigationCommand {

    val destination: String

}

object NavigationDirections {

    val passwords = object : NavigationCommand {

        override val destination: String = "passwords"

    }

    val folders = object : NavigationCommand {

        override val destination: String = "folders"

    }

    val settings = object : NavigationCommand {

        override val destination: String = "settings"

    }

}

sealed class BottomNavRoot(val route: String, @StringRes val name: Int, val icon: ImageVector) {
    object PasswordsRoot : BottomNavRoot("passwords", R.string.screen_passwords_name, Icons.Default.Build)
    object FoldersRoot : BottomNavRoot("folders", R.string.screen_folders_name, Icons.Default.Favorite)
    object SettingsRoot : BottomNavRoot("settings", R.string.screen_settings_name, Icons.Default.Settings)
}

@ExperimentalComposeUiApi
class MainActivity : AppCompatActivity() {

    private val vm: AppViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SnitchTheme(darkTheme = false) {
                App(vm)
            }
        }
    }
}

class AppViewModel : ViewModel() {

    private val appStore: AppStore = AppStore(
        initialState = AppState(emptyList()),
//            middlewares = emptyList(),
        reducers = listOf(AppReducer)
    )

    fun getAppState(): StateFlow<AppState> {
        return appStore.getState()
    }

    fun onAddPassword() {
        appStore.dispatch(AppAction.AddPassword)
    }

    fun onDeletePassword() {
        appStore.dispatch(AppAction.DeletePassword)
    }

}

@Preview
@Composable
fun AppPreview() {
    App(AppViewModel())
}

@Composable
fun App(viewModel: AppViewModel) {

    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            val navBackStackEntry = navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry.value?.arguments?.getString(KEY_ROUTE)
            BottomNavigation {
                listOf(
                    BottomNavRoot.PasswordsRoot,
                    BottomNavRoot.FoldersRoot,
                    BottomNavRoot.SettingsRoot
                ).forEach { screen ->
                    BottomNavigationItem(
                        icon = {
                            Icon(imageVector = screen.icon, contentDescription = "")
                        },
                        label = {
                            Text(text = stringResource(id = screen.name))
                        },
                        selected = currentRoute == screen.route,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo = navController.graph.startDestination
                                launchSingleTop = true
                            }
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(navController = navController, startDestination = NavigationDirections.passwords.destination) {
            composable(NavigationDirections.passwords.destination) {
                ScreenPasswords(paddingValues = paddingValues, navController.hiltNavGraphViewModel(
                    route = NavigationDirections.passwords.destination
                ))
            }
            composable(NavigationDirections.folders.destination) {
                ScreenFolders()
            }
            composable(NavigationDirections.settings.destination) {
                ScreenSettings()
            }
        }
    }

}
