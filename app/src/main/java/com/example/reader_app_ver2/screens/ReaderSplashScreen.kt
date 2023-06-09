package com.example.reader_app_ver2.screens

import android.content.Context
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.reader_app_ver2.R
import com.example.reader_app_ver2.component.Center
import com.example.reader_app_ver2.navigation.ReaderScreens
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay
import org.checkerframework.checker.units.qual.Current
import org.w3c.dom.Text

@Composable
fun ReaderSplashScreen(navController: NavController) {
    //ここではanimateの値を定義しただけ
    val scale = remember {
        androidx.compose.animation.core.Animatable(0f)
    }

    // LaunchedEffectで実際の動きを与える
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.9f, animationSpec = tween(durationMillis = 900, easing = {
                OvershootInterpolator(4f).getInterpolation(it)
            })
        )
        delay(100L)
        //この部分はシングルトンでの実装->FirebaseAuth.getInstance()
        if (FirebaseAuth.getInstance().currentUser?.email.isNullOrEmpty()) {
            navController.navigate(ReaderScreens.LoginScreen.name)
        } else {
            navController.navigate(ReaderScreens.ReaderHomeScreen.name)
        }
    }

    // 実際にBoxにScaleを渡して引数に先ほど定義したAnimatescale値を入れる
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Text(text = "SplashScreen", modifier = Modifier.scale(scale.value))
    }


}


