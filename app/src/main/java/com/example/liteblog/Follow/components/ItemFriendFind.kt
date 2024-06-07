package com.example.liteblog.Follow.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.liteblog.utils.Component.MSpacer
import com.example.liteblog.utils.Component.UserIconDefault
import com.example.liteblog.utils.Model.UserInfor

@Composable
fun ItemFriendFind(
    user: UserInfor,
    onClick: () -> Unit = {}
) {
    Row (modifier = Modifier.fillMaxWidth()){
        UserIconDefault(userinfor = user, size = 80, onClick = { /*TODO*/ })
        MSpacer(0, 10)
        Column {
            Text(
                text = user.username,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = user.lastname + " " + user.firstname,
                fontSize = 18.sp,
            )
            MSpacer(10)
            Button(
                onClick = { onClick() },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colorScheme.secondary,
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                )
            ) {
                Text(text = "Xem trang cá nhân")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItemFriendFind() {
    ItemFriendFind(UserInfor(firstname = "Vu", username = "huyvu_3107"))
}