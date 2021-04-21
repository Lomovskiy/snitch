package com.lomovskiy.snitch.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
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
import androidx.navigation.compose.*
import com.lomovskiy.snitch.R
import com.lomovskiy.snitch.presentation.theme.SnitchTheme

sealed class BottomNavRoot(val route: String, @StringRes val name: Int, val icon: ImageVector) {
    object PasswordsRoot : BottomNavRoot("passwords", R.string.screen_passwords_name, Icons.Default.Build)
    object FoldersRoot : BottomNavRoot("folders", R.string.screen_folders_name, Icons.Default.Favorite)
    object SetttingsRoot : BottomNavRoot("settings", R.string.screen_settings_name, Icons.Default.Settings)
}

@ExperimentalComposeUiApi
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

    val navController = rememberNavController().apply {
        addOnDestinationChangedListener { controller, destination, arguments ->
            Log.d("App", destination.toString())
        }
    }

    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry = navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry.value?.arguments?.getString(KEY_ROUTE)
                listOf(
                    BottomNavRoot.PasswordsRoot,
                    BottomNavRoot.FoldersRoot,
                    BottomNavRoot.SetttingsRoot
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
    ) {
        NavHost(navController = navController, startDestination = BottomNavRoot.PasswordsRoot.route) {
            composable(BottomNavRoot.PasswordsRoot.route) {
                ScreenPasswords()
            }
            composable(BottomNavRoot.FoldersRoot.route) {
                ScreenFolders()
            }
            composable(BottomNavRoot.SetttingsRoot.route) {
                ScreenSettings()
            }
        }
    }

}
