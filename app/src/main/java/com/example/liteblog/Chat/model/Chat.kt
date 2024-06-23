package com.example.liteblog.Chat.model

import com.example.liteblog.utils.Model.UserInfor

data class ChatStorage(
    var chats_id: List<String> = emptyList()
)
data class Chat(
    val id: String = "",
    val user1: UserInfor = UserInfor(),
    val user2: UserInfor = UserInfor(),
    var messages: List<Message> = emptyList()
)

data class Message(
    val id: String = "",
    val user: UserInfor = UserInfor(),
    val description: String = "",
    val image: String = "",

)

