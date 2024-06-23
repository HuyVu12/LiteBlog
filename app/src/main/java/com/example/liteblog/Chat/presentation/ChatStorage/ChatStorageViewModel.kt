package com.example.liteblog.Chat.presentation.ChatStorage

import UserData
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.liteblog.Chat.data.FB_Chat
import com.example.liteblog.Chat.model.Chat
import kotlinx.coroutines.launch

class ChatStorageViewModel: ViewModel() {
    var chats by mutableStateOf(
        listOf<Chat>()
    )
    init {
        viewModelScope.launch {
            chats = FB_Chat.get(UserData.username)
        }
    }
}