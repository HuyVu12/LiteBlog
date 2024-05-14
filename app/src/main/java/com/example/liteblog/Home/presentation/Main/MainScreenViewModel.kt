package com.example.liteblog.Home.presentation.Main

import androidx.lifecycle.ViewModel
import com.example.liteblog.Home.presentation.Home.HomeScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainScreenViewModel: ViewModel() {
    private val _state = MutableStateFlow(HomeScreenState())
    val state = _state.asStateFlow()

    fun checkinLogin() {
        _state.update {
            it.copy(isLogin = true)
        }
    }
}