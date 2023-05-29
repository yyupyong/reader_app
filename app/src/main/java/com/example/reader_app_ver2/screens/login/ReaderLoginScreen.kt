package com.example.reader_app_ver2.screens.login

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun ReaderLoginScreen(
    navHostController: NavHostController
) {
    Button(onClick = { navHostController.navigate("home") }) {
        Text(text = "Click this")
    }


}