package com.example.liteblog.Follow.MyFollower

import UserData
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.liteblog.utils.Data.Database.FBgetFollow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MyFollowerViewModel: ViewModel() {
    private val _state = MutableStateFlow(MyFollowerState())
    val state = _state.asStateFlow()

    var friendInput by mutableStateOf("")
    init {
        viewModelScope.launch {
            _state.update {
                it.copy(true)
            }
            _state.update {
                it.copy(false, FBgetFollow(UserData.userinfor))
            }
        }
    }
    fun onFindFriend() {

    }
}