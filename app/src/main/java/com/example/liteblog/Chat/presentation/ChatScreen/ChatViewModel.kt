package com.example.liteblog.Chat.presentation.ChatScreen

import UserData
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.liteblog.Chat.data.FB_Chat
import com.example.liteblog.Chat.model.Chat
import com.example.liteblog.Chat.model.Message
import com.example.liteblog.utils.Storage.FireStorage
import kotlinx.coroutines.launch
import java.util.UUID

class ChatViewModel: ViewModel() {
    var isLoadData by mutableStateOf(true)
    var chat:Chat? by mutableStateOf(null)
    var pickImage: Uri? by mutableStateOf(null)
    var text by mutableStateOf("")
    fun initChat(chat_id: String) {
        viewModelScope.launch {
            chat = FB_Chat.getChatById(chat_id)
            Log.i("HuyVu5", "${chat}")
            isLoadData = false
        }
    }
    fun sendMessage() {
        if(text.length > 0 || pickImage != null) {
            viewModelScope.launch {
                var uri = ""
                if(pickImage != null) {
                    uri = FireStorage.getUrimage(FireStorage.uploadImage(pickImage!!)).toString()
                }
                val message = Message(
                    description = text,
                    user = UserData.userinfor,
                    id = UUID.randomUUID().toString(),
                    image = uri
                )
                FB_Chat.sendMessage(
                    chat = chat!!,
                    message = message
                )
                text = ""
                pickImage = null
            }
        }
    }
}