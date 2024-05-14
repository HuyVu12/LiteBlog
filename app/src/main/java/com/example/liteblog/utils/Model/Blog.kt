package com.example.liteblog.utils.Model

data class Blog(
    val userinfor: UserInfor? = null,
    val title: String ?= null,
    val description: String ?= null,
    val timePost: Long ?= null,
    val comments: List<Comment> = emptyList(),
    val numLikes: Int = 0,
    val numReport: Int = 0,
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