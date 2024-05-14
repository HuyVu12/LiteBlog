package com.example.liteblog.utils.Model

data class User(
    var firstname: String? = null,
    var lastname: String? = null,
    var username: String? = null,
    var password: String? = null,
    var birthdate: Birthday? = null,
)
