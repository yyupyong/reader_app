package com.example.reader_app_ver2.navigation

import java.lang.IllegalArgumentException

enum class ReaderScreens {
    SplashScreen,
    LoginScreen,
    CreateAccountScreen,
    ReaderHomeScreen,
    SearchScreen,
    DetailScreen,
    UpdateScree,
    ReaderStatsScreen;

    companion object{
        fun fromRoute(route:String):ReaderScreens
        = when(route?.substringBefore("/")){
            SplashScreen.name-> SplashScreen
            LoginScreen.name-> LoginScreen
            CreateAccountScreen.name-> CreateAccountScreen
            ReaderHomeScreen.name-> ReaderHomeScreen
            DetailScreen.name-> DetailScreen
            SearchScreen.name-> SearchScreen
            UpdateScree.name-> UpdateScree
            ReaderStatsScreen.name-> ReaderStatsScreen
            null-> ReaderHomeScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }
    }

}