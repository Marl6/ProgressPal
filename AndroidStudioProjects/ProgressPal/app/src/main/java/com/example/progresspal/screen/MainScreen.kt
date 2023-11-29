package com.example.progresspal.screen

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.progresspal.Routes

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.StartScreen.route) {
        composable(Routes.WelcomeScreen.route) {
            WelcomePage(navController = navController)
        }
        composable(Routes.StartScreen.route) {
            LoginPage(navController = navController)
        }
        composable(Routes.TodoScreen.route) {
            TodoScreen(navController = navController)
        }
        composable(Routes.SignUpScreen.route) {
            SignupPage(navController = navController)
        }
    }
}