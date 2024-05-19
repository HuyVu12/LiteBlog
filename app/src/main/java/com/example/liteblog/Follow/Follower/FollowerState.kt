package com.example.liteblog.Follow.Follower

import com.example.liteblog.utils.Model.Follow

data class FollowerState (
    val isLoading: Boolean = false,
    val follow: Follow = Follow()
)
