package com.devjethava.composeboilerplate.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.devjethava.composeboilerplate.ui.screens.DetailScreen
import com.devjethava.composeboilerplate.ui.screens.HomeScreen
import com.devjethava.composeboilerplate.ui.screens.MainScreen
import com.devjethava.composeboilerplate.ui.screens.SplashScreen

@Composable
fun ComposeBoilerplateNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(route = Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController = navController)
        }
        composable(
            // For mandatory argument use /{name}
            // For optional Argument use ?name={name} in this if we not pass any args then consider defaultValue or null
            route = Screen.DetailScreen.route + "/{name}",
            arguments = listOf(navArgument("name") {
                type = NavType.StringType
                defaultValue = "Dev Jethava"
                nullable = true
            })
        ) {
            DetailScreen(name = it.arguments?.getString("name"))
        }
    }
}