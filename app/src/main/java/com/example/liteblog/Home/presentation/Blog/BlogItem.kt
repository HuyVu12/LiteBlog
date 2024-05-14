package com.example.liteblog.Home.presentation.Blog

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.liteblog.utils.Component.MSpacer
import com.example.liteblog.utils.Component.UserIconDefault
import com.example.liteblog.utils.Model.Blog
import com.example.liteblog.utils.Model.UserInfor
import java.time.Instant

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview(showBackground = true)
fun PreviewBlogItem() {
    BlogItem(
        blog = Blog(
            userinfor = UserInfor(firstname = "Vu", lastname = "Nguyen", username = "huyvu.3107"),
            title = "What is Lorem Ipsum?",
            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
            comments = emptyList(),
            likes = emptyList(),
            reports = emptyList(),
            timePost = Instant.now().toEpochMilli()
        )
    )
}

@Composable
fun BlogItem(blog: Blog, modifier: Modifier = Modifier) {
    val userInfor = blog.userinfor!!
    Row (
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
    ){
        UserIconDefault(userinfor = userInfor, size = 40, onClick = { /*TODO*/ })
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 5.dp, horizontal = 10.dp),
//            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = userInfor.username,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = blog.title!!,
                fontWeight = FontWeight.Medium,
                fontSize = 17.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = blog.description!!,
                fontWeight = FontWeight.W400,
                fontSize = 16.sp,
                lineHeight = 18.sp,
                color = MaterialTheme.colorScheme.onSurface,
//                textAlign = TextAlign.Justify
//                maxLines = 1,
            )
            MSpacer(10)
            BI_BottomIcon(
                likes = blog.likes.size,
                comments = blog.comments.size
            )
        }
    }
}

@Composable
fun BI_BottomIcon(
    modifier: Modifier = Modifier,
    likes: Int,
    comments: Int
) {
    Row (
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(30.dp)
    ){
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ){
            Icon(
                imageVector = Icons.Outlined.FavoriteBorder,
                contentDescription = null,
                modifier = Modifier.size(16.dp))
            Text(
                text = likes.toString(),
                fontSize = 16.sp
            )
        }
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ){
            Text(
                text = "${comments.toString()}  bình luận",
                fontSize = 16.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreViewBI_BottomIcon() {
    BI_BottomIcon(
        likes = 0,
        comments = 0
    )
}