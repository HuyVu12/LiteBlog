package com.example.liteblog.Follow.MyFollower

import com.example.liteblog.utils.Model.Follow
import com.example.liteblog.utils.Model.UserInfor

data class MyFollowerState(
    val isLoading: Boolean = false,
    val follow: Follow = Follow()
)
