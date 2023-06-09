package com.example.reader_app_ver2.navigation

import android.telecom.Call.Details
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.reader_app_ver2.screens.ReaderSplashScreen
import com.example.reader_app_ver2.screens.detail.ReaderDetailScreen
import com.example.reader_app_ver2.screens.home.ReaderHomeScreen
import com.example.reader_app_ver2.screens.login.ReaderLoginScreen
import com.example.reader_app_ver2.screens.search.ReaderSearchScreen
import com.example.reader_app_ver2.screens.ststs.ReaderStatsScreen
import com.example.reader_app_ver2.screens.update.ReaderUpdateScreen
import javax.security.auth.login.LoginException

@Composable
fun ReaderNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ReaderScreens.SplashScreen.name) {

        //navControllerをなるべくこのCompose内で持ちたい、なのでcallbackで渡す
        composable(ReaderScreens.SplashScreen.name) {
            ReaderSplashScreen(navController)
        }

        //ここでは普通に渡している
        composable(ReaderScreens.LoginScreen.name) {
            ReaderLoginScreen(navController)
        }

        composable(ReaderScreens.ReaderStatsScreen.name) { ReaderStatsScreen() }
        composable(ReaderScreens.ReaderHomeScreen.name) { ReaderHomeScreen() }
        composable(ReaderScreens.UpdateScree.name) { ReaderUpdateScreen() }
        composable(ReaderScreens.DetailScreen.name) { ReaderDetailScreen() }
    }

}

