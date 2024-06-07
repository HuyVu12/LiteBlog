package com.example.liteblog.Follow.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.liteblog.utils.Component.MSpacer
import com.example.liteblog.utils.Component.UserIconDefault
import com.example.liteblog.utils.Model.UserInfor

@Composable
fun ItemFriendDefault(
    user: UserInfor,
    onClick: ()->Unit = {}
) {
    Row (modifier = Modifier.fillMaxWidth().clickable { onClick() }){
        UserIconDefault(userinfor = user, size = 50, onClick = { /*TODO*/ })
        MSpacer(0, 10)
        Column {
            Text(
                text = user.username,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = user.lastname + " " + user.firstname,
                fontSize = 15.sp,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewItemFriend() {
    ItemFriendDefault(UserInfor(firstname = "Vu", username = "huyvu_3107"))
}