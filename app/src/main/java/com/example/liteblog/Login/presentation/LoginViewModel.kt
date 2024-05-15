package com.example.liteblog.Login.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.liteblog.utils.Data.FBgetUserByUsernameAndPassword
import com.example.liteblog.utils.Model.User
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class LoginViewModel:ViewModel() {
    private val _state = MutableStateFlow(LoginScreenState());
    val state = _state.asStateFlow()

    var usernameInput by mutableStateOf("")
    var passwordInput by mutableStateOf("")
    var isShowPassword by mutableStateOf(false)

    var userData by mutableStateOf<String?>(null)

    fun signIn() {
        if( usernameInput.length > 0 &&
            passwordInput.length > 0 &&
            _state.value.isLoading == false
            ) {
            viewModelScope.launch {
                _state.update {
                    it.copy(isLoading = true)
                }
                userData = FBgetUserByUsernameAndPassword(username = usernameInput, password = passwordInput)?.username
                _state.update {
                    it.copy(isLoading = false)
                }
                Log.i("HuyVu", "${userData}")
            }
        }
    }
}