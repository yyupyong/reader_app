package com.example.reader_app_ver2.screens.home

import android.annotation.SuppressLint
import android.icu.text.CaseMap.Title
import android.net.wifi.hotspot2.pps.HomeSp
import android.provider.CalendarContract
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.reader_app_ver2.component.Center

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home() {
    //引数としてComposableを受け取ることで自分でカスタマイズが可能になる
    Scaffold(topBar = {}, floatingActionButton = { FABContent {} }) {
        Surface(modifier = Modifier.fillMaxSize()) {

        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun ReaderAppBar(
    title: String,
    showProfile: Boolean = true,
    navController: NavController
) {
    TopAppBar(title = {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (showProfile) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Logo Icon",
                    modifier = Modifier.clip(
                        RoundedCornerShape(12.dp))
                        .scale(0.6f)
                )
            }
        }
    }, actions = {}, colors = TopAppBarDefaults.smallTopAppBarColors())
}

@Composable
fun FABContent(onTap: (String) -> Unit) {
    FloatingActionButton(
        onClick = { onTap("") },
        shape = RoundedCornerShape(50.dp),
        containerColor = MaterialTheme.colorScheme.background,
    ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "")
    }
}


