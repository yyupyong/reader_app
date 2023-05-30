package com.example.reader_app_ver2.screens.search

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.reader_app_ver2.navigation.ReaderScreens

@Composable
fun ReaderSearchScreen(
    onNavigateSplash: () -> Unit,
) {
    Button(onClick = onNavigateSplash) {
        Text(text = "SearchScreen")
    }

}