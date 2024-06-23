package com.example.liteblog.Chat.presentation.ChatStorage

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.liteblog.utils.Component.MyBasicTopBar
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.liteblog.Chat.presentation.ChatStorage.component.UserChatItem
import com.example.liteblog.Screen

@Composable
fun ChatStorageScreen(
    navController: NavController
) {
    Scaffold (
        topBar = {
            MyBasicTopBar(
                navController = navController,
                title = {
                    Text(text = "Đoạn chat")
                }
            )
        }
    ){
        ChatMainStorageScreen(
            navController = navController,
            modifier = Modifier.padding(it)
        )
    }
}

@Composable
fun ChatMainStorageScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: ChatStorageViewModel = viewModel()
) {
    LazyColumn(
        modifier = modifier.padding(10.dp)
    ) {
        items(viewModel.chats) {
            UserChatItem(
                chat = it,
                onClick = {
                    navController.navigate(Screen.ChatScreen.withArgs(it.id))
                }
            )
        }
    }
}