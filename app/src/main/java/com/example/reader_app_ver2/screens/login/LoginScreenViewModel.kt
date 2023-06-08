package com.example.reader_app_ver2.screens.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class LoginScreenViewModel : ViewModel() {

//    val loadingState = MutableStateFlow(LoadingState.IDLE)
    private val auth: FirebaseAuth = Firebase.auth

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading



    fun signInWithEmailAndPassword(email: String, password: String, home:()->Unit) = viewModelScope.launch {
        try {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    //成功時の処理を記述
                    home()
                }
            }
        } catch (e: Exception) {
            //Log.d(TAG, "signInWithEmailAndPassword: ")
        }
    }



    fun createUserWithEmailAndPassword (
        email: String,
        password: String,
        home: () -> Unit) = viewModelScope.launch{
            if(_loading.value == false) {
                _loading.value = true
                auth.createUserWithEmailAndPassword(email, password)
                    //処理が完了したかどうかtask.isSuccessは成功かどうか
                    .addOnCompleteListener{ task->
                        if (task.isSuccessful){
                            home()
                        }else{
                            Log.d("FB", "currentUserEmailAndPassword${task.result}")
                        }
                        _loading.value == false
                    }
            }
    }
}
