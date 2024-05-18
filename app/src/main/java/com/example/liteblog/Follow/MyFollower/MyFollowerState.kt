package com.example.liteblog.Follow.MyFollower

import com.example.liteblog.utils.Model.UserInfor

data class MyFollowerState(
    val isLoading: Boolean = false,
    val friends: List<UserInfor> = emptyList()
)
