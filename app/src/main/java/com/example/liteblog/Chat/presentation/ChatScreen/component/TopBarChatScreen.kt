package com.example.liteblog.Chat.presentation.ChatScreen.component

import UserData
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.liteblog.Chat.model.Chat
import com.example.liteblog.utils.Component.MSpacer
import com.example.liteblog.utils.Component.UserIconDefault
import com.example.liteblog.utils.Model.UserInfor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarChatScreen(
    chat: Chat,
    navController: NavController
) {
    val userInfor:UserInfor
    if (chat.user1.username == UserData.username)userInfor = chat.user2
    else userInfor = chat.user1
    TopAppBar(
        title = {
            Row {
                MSpacer(0, 20)
                UserIconDefault(userinfor = userInfor, size = 40, onClick = { /*TODO*/ })
                MSpacer(0, 10)
                Column {
                    Text(text = userInfor.username,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    MSpacer(5)
                    Text(text = userInfor.lastname + " " + userInfor.firstname,
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        },
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable { navController.popBackStack() }
            )
        },
        modifier = Modifier.shadow(
            elevation = 5.dp
        )
    )
}