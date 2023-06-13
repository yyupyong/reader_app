package com.example.reader_app_ver2.screens.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reader_app_ver2.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginScreenViewModel : ViewModel() {

    //    val loadingState = MutableStateFlow(LoadingState.IDLE)
    private val auth: FirebaseAuth = Firebase.auth

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()


    fun signInWithEmailAndPassword(email: String, password: String, home: () -> Unit) =
        viewModelScope.launch {
            try {
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        home()
                    }
                }
            } catch (e: Exception) {
                //Log.d(TAG, "signInWithEmailAndPassword: ")
            }
        }

    private fun creteUser(displayName: String?) {
        //ここでauthインスタンスからuser_idを取得
        val user_id = auth.currentUser?.uid
        //user dataclassを作成しtoMapメソッドを作成しそれをfirestoreに保存する
        //ここでauthに登録されているデータを使用しfireStoreに今後アプリで使用するデータを保存するg
        val user = User(
            userId = user_id.toString(),
            displayName = displayName.toString(),
            profession = "Flutter developer",
            quote = "YOLO",
            avatarUrl = "", id = null
        ).toMap()

        FirebaseFirestore.getInstance().collection("users")
            .add(user)
    }


    fun createUserWithEmailAndPassword(
        email: String,
        password: String,
        home: () -> Unit
    ) = viewModelScope.launch {
        if (!_loading.value) {
            _loading.value = true
            auth.createUserWithEmailAndPassword(email, password)
                //処理が完了したかどうかtask.isSuccessは成功かどうか
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val displayName = task.result.user?.email?.split("@")?.get(0)
                        creteUser(displayName)
                        home()
                    } else {
                        Log.d("FB", "currentUserEmailAndPassword${task.result}")
                    }
                    !_loading.value
                }
        }
    }
}
