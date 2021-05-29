package com.lomovskiy.snitch.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
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
import com.lomovskiy.snitch.presentation.screen.ScreenFolders
import com.lomovskiy.snitch.presentation.screen.ScreenFoldersViewModel
import com.lomovskiy.snitch.presentation.screen.ScreenSettings
import com.lomovskiy.snitch.presentation.screen.ScreenSettingsViewModel
import com.lomovskiy.snitch.presentation.screen.accounts.ScreenAccounts
import com.lomovskiy.snitch.presentation.screen.accounts.ScreenAccountsViewModel
import com.lomovskiy.snitch.presentation.theme.SnitchTheme
import dagger.hilt.android.AndroidEntryPoint

interface Screen {

    val destination: String

}

object NavigationDirections {

    val flowAccounts = object : Flow {

        override val destination = "Accounts"

        override val route = "root_accounts"

    }

    val folders = object : Screen {

        override val destination: String = "Folders"

    }

    val settings = object : Screen {

        override val destination: String = "Settings"

    }

}

interface Flow : Screen {

    val route: String

}

sealed class BottomNavRoot(val screen: Screen, @StringRes val name: Int, val icon: ImageVector) {
    object AccountsRoot : BottomNavRoot(NavigationDirections.flowAccounts, R.string.screen_accounts_name, Icons.Default.AccountCircle)
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
                    BottomNavRoot.AccountsRoot,
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
            startDestination = NavigationDirections.flowAccounts.route
        ) {
            navigation(
                startDestination = NavigationDirections.flowAccounts.destination,
                route = NavigationDirections.flowAccounts.route
            ) {
                composable(NavigationDirections.flowAccounts.destination) {
                    val vm: ScreenAccountsViewModel = hiltViewModel()
                    ScreenAccounts(vm = vm)
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
