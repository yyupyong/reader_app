package com.example.reader_app_ver2.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.reader_app_ver2.component.Center

@Composable
fun ReaderLoginScreen(
) {
    Center(
        modifier = Modifier.fillMaxSize(),
    ) {
        Text(text = "LoginScreen")
    }
}

@Composable
fun UserForm(){
    val email  = rememberSaveable {
        mutableStateOf("")
    }
    val password  = rememberSaveable {
        mutableStateOf("")
    }
    val passwordVisibility = rememberSaveable {
        mutableStateOf(false)
    }
    val passwordFocusRequest = FocusRequester.Default
    val keyboardController = LocalSoftwareKeyboardController.current
    //上記で設定したpasswordとemailがisNotEmptyだった場合trueをもつ
    val valid = remember(email.value,password.value) {
        email.value.trim().isNotEmpty() || password.value.trim().isNotEmpty()
    }

}