package com.lomovskiy.snitch.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.lomovskiy.snitch.R
import com.lomovskiy.snitch.presentation.screen.*
import com.lomovskiy.snitch.presentation.screen.passwords.ScreenPasswords
import com.lomovskiy.snitch.presentation.screen.passwords.ScreenPasswordsViewModel
import com.lomovskiy.snitch.presentation.theme.SnitchTheme
import dagger.hilt.android.AndroidEntryPoint

interface Screen {

    val destination: String

}

object NavigationDirections {

    val flowPasswords = object : Flow {

        override val destination = "Passwords"

        override val root = "root_passwords"

    }

    val folders = object : Screen {

        override val destination: String = "Folders"

    }

    val settings = object : Screen {

        override val destination: String = "Settings"

    }

}

interface Flow : Screen {

    val root: String

}

sealed class BottomNavRoot(val screen: Screen, @StringRes val name: Int, val icon: ImageVector) {
    object PasswordsRoot : BottomNavRoot(NavigationDirections.flowPasswords, R.string.screen_passwords_name, Icons.Default.Build)
    object FoldersRoot : BottomNavRoot(NavigationDirections.folders, R.string.screen_folders_name, Icons.Default.Favorite)
    object SettingsRoot : BottomNavRoot(NavigationDirections.settings, R.string.screen_settings_name, Icons.Default.Settings)
}

@ExperimentalComposeUiApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SnitchTheme(darkTheme = false) {
                App()
            }
        }
    }
}

@Preview
@Composable
fun AppPreview() {
    App()
}

@Composable
fun App() {

    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            AppBar(title = currentRoute ?: "null")
        },
        bottomBar = {
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
                        selected = currentRoute == screen.screen.destination,
                        onClick = {
                            navController.navigate(screen.screen.destination) {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = NavigationDirections.flowPasswords.root
        ) {
            navigation(
                startDestination = NavigationDirections.flowPasswords.destination,
                route = NavigationDirections.flowPasswords.root
            ) {
                composable(NavigationDirections.flowPasswords.destination) {
                    val vm: ScreenPasswordsViewModel = hiltViewModel()
                    ScreenPasswords(paddingValues = paddingValues, vm = vm)
                }
            }
            composable(NavigationDirections.folders.destination) {
                val vm: ScreenFoldersViewModel = hiltViewModel()
                ScreenFolders(vm)
            }
            composable(NavigationDirections.settings.destination) {
                val vm: ScreenSettingsViewModel = hiltViewModel()
                ScreenSettings(vm)
            }
        }
    }

}
