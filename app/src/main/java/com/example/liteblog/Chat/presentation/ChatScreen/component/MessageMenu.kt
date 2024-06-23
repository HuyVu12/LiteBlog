package com.example.liteblog.Chat.presentation.ChatScreen.component

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.liteblog.Chat.data.FB_Chat
import com.example.liteblog.Chat.model.Chat
import com.example.liteblog.Chat.model.Message
import com.example.liteblog.utils.Component.MSpacer

@Composable
fun MessageMenu(
    isVisible: Boolean,
    onDissmiss:() -> Unit,
    chat: Chat,
    message: Message
) {
    if(isVisible) {
        DropdownMenu(expanded = isVisible, onDismissRequest = { onDissmiss() }) {
            DropdownMenuItem(
                text = {
                    Row {
                        Text(text = "Xoá")
                        MSpacer(10)
                        Icon(imageVector = Icons.Default.Clear, contentDescription = null)
                    }
                },
                onClick = { FB_Chat.deleteMessage(chat = chat, message = message) })
        }
    }
}