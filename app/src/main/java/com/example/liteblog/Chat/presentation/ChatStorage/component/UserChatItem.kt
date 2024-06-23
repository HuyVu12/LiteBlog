package com.example.liteblog.Chat.presentation.ChatStorage.component

import UserData
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.liteblog.Chat.model.Chat
import com.example.liteblog.utils.Component.MSpacer
import com.example.liteblog.utils.Component.UserIconDefault
import com.example.liteblog.utils.Model.User
import com.example.liteblog.utils.Model.UserInfor

@Composable
fun UserChatItem(
    chat: Chat,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val user: UserInfor
    user = if(UserData.username == chat.user1.username) chat.user2
    else chat.user1

    Row (
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ){
        UserIconDefault(userinfor = user, size = 50, onClick = { /*TODO*/ })
        MSpacer(0, 20)
        Column(
            modifier = Modifier
//                .padding(top = 10.dp)
                .weight(1f)
        ) {
            Text(
                text = user.username,
                style = TextStyle(
                    fontSize = 17.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.SemiBold
                )
            )
            Text(
                text = user.lastname + " " + user.firstname,
                style = TextStyle(
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
            )
        }
    }
    MSpacer(20)
}