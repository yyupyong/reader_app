package com.example.reader_app_ver2.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.reader_app_ver2.screens.home.ReaderHomeScreen
import com.example.reader_app_ver2.screens.login.ReaderLoginScreen

@Composable
fun ReaderNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { ReaderLoginScreen(navController) }
        composable("home") { ReaderHomeScreen() }
    }

}