package com.example.reader_app_ver2.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.reader_app_ver2.screens.ReaderSplashScreen
import com.example.reader_app_ver2.screens.home.ReaderHomeScreen
import com.example.reader_app_ver2.screens.login.ReaderLoginScreen
import com.example.reader_app_ver2.screens.search.ReaderSearchScreen

@Composable
fun ReaderNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ReaderScreens.SearchScreen.name) {
        composable(ReaderScreens.SearchScreen.name) { ReaderSearchScreen(navController = navController) }
        composable(ReaderScreens.SplashScreen.name) { ReaderSplashScreen(navController = navController) }
    }

}