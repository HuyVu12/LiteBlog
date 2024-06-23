package com.example.liteblog.Chat.presentation.ChatScreen.component

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.liteblog.Chat.model.Chat


@Composable
fun ChatScreenList(
    chat: Chat,
    modifier: Modifier = Modifier
) {
    LazyColumn(content = {})
}