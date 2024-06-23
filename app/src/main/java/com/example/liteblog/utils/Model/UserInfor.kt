package com.example.liteblog.utils.Model

data class UserInfor(
    var firstname: String = "",
    var lastname: String = "",
    var username: String = "",
    var avatar: String? = null,
    var admin: Boolean? = null,
    var banned: Boolean? = null
)