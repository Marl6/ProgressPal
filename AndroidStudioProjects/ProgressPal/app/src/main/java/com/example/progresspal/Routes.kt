package com.example.progresspal

sealed class Routes(val route: String) {
    object WelcomeScreen : Routes("WelcomePage")
    object StartScreen  : Routes("LoginPage")
}
