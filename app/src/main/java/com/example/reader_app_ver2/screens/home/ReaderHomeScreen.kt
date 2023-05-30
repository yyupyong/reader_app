package com.example.reader_app_ver2.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.reader_app_ver2.component.Center

@Composable
fun ReaderHomeScreen() {
    Center(
        modifier = Modifier.fillMaxSize(),
    ) {
        Text(text = "Home")
    }
}