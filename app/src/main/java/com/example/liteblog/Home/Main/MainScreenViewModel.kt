package com.example.liteblog.Home.Main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.liteblog.utils.Data.Database.FBgetUserInforByUsername
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import UserData
import com.example.liteblog.utils.Data.Database.FBgetFollow

class MainScreenViewModel: ViewModel() {
    private val _state = MutableStateFlow(MainScreenState())
    val state = _state.asStateFlow()

    fun checkinLogin() {
        viewModelScope.launch {
            UserData.userinfor = FBgetUserInforByUsername(UserData.username)!!
            UserData.follow = FBgetFollow(UserData.userinfor)
            _state.update {
                it.copy(isLogin = true)
            }
        }
    }
}