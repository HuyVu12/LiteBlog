package com.example.liteblog.Follow.MyFollower

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MyFollowerViewModel: ViewModel() {
    private val _state = MutableStateFlow(MyFollowerState())
    val state = _state.asStateFlow()

    var friendInput by mutableStateOf("")

    fun onFindFriend() {

    }
}