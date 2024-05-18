package com.example.liteblog.utils.Model

data class Follow(
    var followers:List<UserInfor> = emptyList(),
    var myFollowers:List<UserInfor> = emptyList(),
)
