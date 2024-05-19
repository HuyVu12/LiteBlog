package com.example.liteblog.Follow.FindUser

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.liteblog.utils.Data.Database.FBgetUserInforByUsername
import com.example.liteblog.utils.Data.Database.FBfollowUser
import com.example.liteblog.utils.Model.UserInfor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FindUserViewModel: ViewModel() {
    private val _state = MutableStateFlow(FindFollowerState())
    val state = _state.asStateFlow()

    var friendInput by mutableStateOf("")
    var friendFind:UserInfor? by mutableStateOf(null)

    fun onFindUser() {
        if(friendInput.isNotEmpty()) {
            viewModelScope.launch {
                _state.update {
                    it.copy(isLoading = true)
                }
                friendFind = FBgetUserInforByUsername(friendInput)
                _state.update {
                    it.copy(isLoading = false)
                }
            }
        }
    }
    fun onClickFollow() {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            FBfollowUser(friendFind!!)
            _state.update {
                it.copy(isLoading = false)
            }
        }
    }
}