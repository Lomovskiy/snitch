package com.lomovskiy.snitch.presentation

import android.os.Bundle
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.*
import com.lomovskiy.snitch.R
import com.lomovskiy.snitch.presentation.theme.SnitchTheme

sealed class Screen(val route: String, @StringRes val name: Int) {
    object Screen1 : Screen("screen1", R.string.screen_1_name)
    object Screen2 : Screen("screen2", R.string.screen_2_name)
    object Screen3 : Screen("screen3", R.string.screen_3_name)
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

    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry = navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry.value?.arguments?.getString(KEY_ROUTE)
                listOf(
                    Screen.Screen1,
                    Screen.Screen2,
                    Screen.Screen3,
                ).forEach { screen ->
                    BottomNavigationItem(
                        icon = {
                            Icon(imageVector = Icons.Default.ExitToApp, contentDescription = "")
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
        NavHost(navController = navController, startDestination = Screen.Screen1.route) {
            composable(Screen.Screen1.route) {
                Screen1(
                    forward = {
                        navController.navigate(Screen.Screen2.route)
                    },
                    back = {
                        navController.popBackStack()
                    }
                )
            }
            composable(Screen.Screen2.route) {
                Screen2(
                    forward = {
                        navController.navigate(Screen.Screen3.route)
                    },
                    back = {
                        navController.popBackStack()
                    }
                )
            }
            composable(Screen.Screen3.route) {
                Screen3(
                    back = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }

}
