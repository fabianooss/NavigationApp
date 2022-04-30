package com.example.navigationapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.*
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.navigationapp.ui.theme.NavigationAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                   MyApp()
                }
            }
        }
    }
}

@Composable
fun MyAppSimple() {
    val navController = rememberNavController()
    Column() {
        Row() {
            Button(onClick = { navController.navigate("profile") }) {
                Text(text = "Profile")
            }
            Button(onClick = { navController.navigate("about") }) {
                Text(text = "About")
            }

        }
        NavHost(navController = navController, startDestination = "home") {
            composable("home") {
                HomeCompose()
            }
            composable("about") {
                AboutCompose()
            }
            profileGraph(navController)
        }

    }
}

fun NavGraphBuilder.profileGraph(navController: NavHostController) {
    navigation(startDestination = "principal", route = "profile") {
        composable("principal") { ProfileCompose(navController) }
        composable("form/{profileId}",
            arguments = listOf(
                    navArgument("profileId") {
                        type = NavType.IntType
                    } // ,
/*                    navArgument("other") {
                        type = NavType.FloatType
                    }, */

                )
        ) {
            val id = it.arguments?.getInt ("profileId")
            // val other = it.arguments?.getFloat("other")
            ProfileFormCompose(navController, id)
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    val items = listOf(
        ScreenManager.Home,
        ScreenManager.Profile,
        ScreenManager.About
    )
    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(screen.icon, contentDescription = null) },
                        label = { Text(stringResource(screen.resourceId)) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {

                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(navController, startDestination = ScreenManager.Home.route, Modifier.padding(innerPadding)) {
            composable(ScreenManager.Home.route) { HomeCompose() }
            composable(ScreenManager.About.route) { AboutCompose() }
            profileGraph(navController)
        }
    }

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NavigationAppTheme {
        MyApp()
    }
}