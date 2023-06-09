package com.example.reader_app_ver2.screens.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginScreenViewModel : ViewModel() {

    //    val loadingState = MutableStateFlow(LoadingState.IDLE)
    private val auth: FirebaseAuth = Firebase.auth

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading


    fun signInWithEmailAndPassword(email: String, password: String, home: () -> Unit) =
        viewModelScope.launch {
            try {
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val displayName = task.result.user?.email?.split("@")?.get(0)
                        creteUser(displayName)
                        home()
                    }
                }
            } catch (e: Exception) {
                //Log.d(TAG, "signInWithEmailAndPassword: ")
            }
        }

    private fun creteUser(displayName: String?) {
        val user_id = auth.currentUser?.uid
        val user = mutableMapOf<String, Any>()
        //FireStoreに保存する際にUIDとメールアドレスの＠の前を切り抜いたものを保存
        //要するにauthで登録したデータからFireStoreに初期値としてデータを保存
        user["user_id"] = user_id.toString()
        user["display_name"] = displayName.toString()

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
                        home()
                    } else {
                        Log.d("FB", "currentUserEmailAndPassword${task.result}")
                    }
                    !_loading.value
                }
        }
    }
}
