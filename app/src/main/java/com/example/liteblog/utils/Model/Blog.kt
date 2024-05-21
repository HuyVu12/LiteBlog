package com.example.liteblog.utils.Model

import android.net.Uri

data class Blog(
    var id: String? = null,
    var userinfor: UserInfor? = null,
    val title: String ?= null,
    var description: String ?= null,
    var timePost: Long ?= null,
    var imageList: MutableList<String>? = null,
    var comments: List<Comment> = emptyList(),
    var likes: List<UserInfor> = emptyList(),
    val reports: List<UserInfor> = emptyList(),
)

data class Comment(
    var userinfor: UserInfor? = null,
    var description: String ?= null,
    var timePost: Long ?= null,
    val likes: List<UserInfor> = emptyList(),
    val reports: List<UserInfor> = emptyList(),
)