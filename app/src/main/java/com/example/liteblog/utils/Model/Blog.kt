package com.example.liteblog.utils.Model

import android.net.Uri

data class Blog(
    var id: String? = null,
    var userinfor: UserInfor? = null,
    val title: String ?= null,
    val description: String ?= null,
    var timePost: Long ?= null,
    var imageList: MutableList<String>? = null,
    val comments: List<Comment> = emptyList(),
    val likes: List<UserInfor> = emptyList(),
    val reports: List<UserInfor> = emptyList(),
)

data class Comment(
    val userinfor: UserInfor? = null,
    val description: String ?= null,
    val timePost: Long ?= null,
    val likes: List<UserInfor> = emptyList(),
    val reports: List<UserInfor> = emptyList(),
)