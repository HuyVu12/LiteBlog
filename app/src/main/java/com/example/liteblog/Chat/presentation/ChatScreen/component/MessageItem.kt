package com.example.liteblog.Chat.presentation.ChatScreen.component

import UserData
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.liteblog.Chat.model.Chat
import com.example.liteblog.Chat.model.Message
import com.example.liteblog.utils.Component.MSpacer
import com.example.liteblog.utils.Component.UserIconDefault

@Composable
fun MessageItem(
    message: Message,
    chat: Chat
) {
    val userInfor = if(UserData.username == message.user.username) null else message.user
    val textAlign = if(userInfor == null) TextAlign.End else TextAlign.Start
    var isShowMenu by remember {
        mutableStateOf(false)
    }
    Box (
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        contentAlignment = if(
            userInfor == null
        ) Alignment.TopEnd
        else Alignment.TopStart
    ){
        Row (
            modifier = Modifier.fillMaxWidth(.8f),
            horizontalArrangement = if(userInfor == null)Arrangement.End else Arrangement.Start
        ){
            if(userInfor != null)
                UserIconDefault(userinfor = userInfor, size = 40, onClick = { /*TODO*/ })
            MSpacer(0, 10)
            Column (
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .padding(10.dp),
            ){
                if(message.description.length > 0) {
                    Text(
                        text = message.description,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        textAlign = TextAlign.Start
                    )
                    MSpacer(10)
                }
                if(message.image.isNotEmpty()) {
                    AsyncImage(
                        model = message.image,
                        contentDescription = null,
                        modifier = Modifier.height(200.dp)
                    )
                }
                if(userInfor == null)
                Box {
                    Icon(imageVector = Icons.Default.MoreHoriz,
                        contentDescription = null,
                        Modifier.size(15.dp).clickable { isShowMenu = true }
                    )
                    MessageMenu(isVisible = isShowMenu, onDissmiss = { isShowMenu = false }, chat = chat, message = message)
                }
            }
        }
    }
}