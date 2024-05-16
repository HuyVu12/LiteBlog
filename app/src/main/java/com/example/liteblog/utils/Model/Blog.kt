package com.example.liteblog.utils.Model

data class Blog(
    var id: String? = null,
    var userinfor: UserInfor? = null,
    val title: String ?= null,
    val description: String ?= null,
    var timePost: Long ?= null,
    var imageList: List<String>? = null,
    val comments: List<Comment> = emptyList(),
    val likes: List<UserInfor> = emptyList(),
    val reports: List<UserInfor> = emptyList(),
)

data class Comment(
    val userinfor: UserInfor? = null,
    val description: String ?= null,
    val timePost: Long ?= null,
    val numLikes: Int = 0,
    val numReport: Int = 0,
)