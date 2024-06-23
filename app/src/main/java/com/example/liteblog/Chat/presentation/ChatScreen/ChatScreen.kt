package com.example.liteblog.Chat.presentation.ChatScreen

import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.liteblog.Chat.data.FB_Chat
import com.example.liteblog.Chat.model.Message
import com.example.liteblog.Chat.presentation.ChatScreen.component.MessageItem
import com.example.liteblog.Chat.presentation.ChatScreen.component.TopBarChatScreen
import com.example.liteblog.utils.Component.MSpacer
import com.example.liteblog.utils.Model.UserInfor

@Composable
fun ChatScreen(
    viewModel: ChatViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    chat_id: String = "",
    navController: NavController
) {
    if(!viewModel.isLoadData) {
        Scaffold (
            topBar = {
                TopBarChatScreen(chat = viewModel.chat!!, navController = navController)
            }
        ){
            ChatMainScreen(
                modifier = Modifier.padding(it)
            )
        }
    }
    else {
        viewModel.initChat(chat_id)
    }
}

@Composable
fun ChatMainScreen(
    modifier: Modifier = Modifier,
    viewModel:ChatViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val pickLaucher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            viewModel.pickImage = it
        }
    )
    val lazyState = rememberLazyListState()
    LaunchedEffect(key1 = viewModel.chat) {
        lazyState.animateScrollToItem(viewModel.chat!!.messages.size)
    }
    viewModel.chat?.let {
        FB_Chat.autoFetchMessage(
            it,
            onUpdate = {
                viewModel.chat = it
            }
        )
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp, 0.dp, 10.dp, 10.dp)
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f),
            state = lazyState
        ) {
            items(viewModel.chat!!.messages) {message ->
                MessageItem(
                    message = message,
                    chat = viewModel.chat!!
                )
            }
        }
        if(viewModel.pickImage != null) {
            Divider()
            MSpacer(5)
            AsyncImage(
                model = viewModel.pickImage,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                filterQuality = FilterQuality.None,
                modifier = Modifier.height(100.dp)
            )
            MSpacer(5)
            Divider()
        }
        Column {
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                IconButton(
                    onClick = {
                        pickLaucher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = MaterialTheme.colorScheme.tertiary,
                        contentColor = MaterialTheme.colorScheme.onTertiary
                    ),
                )
                {
                    Icon(imageVector = Icons.Default.Image, contentDescription = null)
                }
                MSpacer(0, 10)
                OutlinedTextField(
                    value = viewModel.text,
                    onValueChange = {
                        viewModel.text = it
                    },
                    placeholder = {
                        Text(text = "Nhắn tin")
                    },
                    label = {
                        Text(text = "Nhắn tin")
                    },
                    modifier = Modifier.weight(1f),
                    keyboardActions = KeyboardActions(),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                )
                MSpacer(0, 10)
                TextButton(
                    onClick = {
                        viewModel.sendMessage()
                    },
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = MaterialTheme.colorScheme.tertiary,
                        contentColor = MaterialTheme.colorScheme.onTertiary
                    ),
                )
                {
                    Icon(imageVector = Icons.Default.Send, contentDescription = null)
                }
            }
        }
    }
}