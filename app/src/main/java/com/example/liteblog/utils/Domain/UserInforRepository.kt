package com.example.liteblog.utils.Domain

interface UserInforRepository {
    suspend fun getUserInfor(username: String)
}