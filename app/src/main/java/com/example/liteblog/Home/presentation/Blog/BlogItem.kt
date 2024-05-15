package com.example.liteblog.Home.presentation.Blog

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
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
            timePost = Instant.now().toEpochMilli(),
        ),
        modifier = Modifier.padding(10.dp)
    )
}

@Composable
fun BlogItem(
    blog: Blog,
    modifier: Modifier = Modifier,
) {
    val userInfor = blog.userinfor!!
    var showAllDescription by rememberSaveable {
        mutableStateOf(false)
    }
    var liked by rememberSaveable {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
    ) {
//        HEAD
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            UserIconDefault(userinfor = userInfor, size = 40, onClick = { /*TODO*/ })
            Text(
                text = userInfor.username,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(top = 5.dp)
            )
        }
//        BODY
        MSpacer(10)

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
            maxLines = if(showAllDescription) Int.MAX_VALUE else 4,
            modifier = Modifier.clickable {
                showAllDescription = !showAllDescription
            }
        )

        MSpacer(10)

        BI_BottomIcon(
            likes = blog.likes.size,
            comments = blog.comments.size,
            liked = liked,
            onClickLike = {
                liked = !liked
            }
        )
    }

//    Row (
//        modifier = modifier
//            .fillMaxWidth()
//            .background(MaterialTheme.colorScheme.surface)
//    ){
//        UserIconDefault(userinfor = userInfor, size = 40, onClick = { /*TODO*/ })
//        Column(
//            modifier = Modifier
//                .weight(1f)
//                .padding(vertical = 5.dp, horizontal = 10.dp),
////            verticalArrangement = Arrangement.spacedBy(2.dp)
//        ) {
//            Text(
//                text = userInfor.username,
//                fontWeight = FontWeight.SemiBold,
//                fontSize = 16.sp,
//                color = MaterialTheme.colorScheme.onSurface
//            )
//            Text(
//                text = blog.title!!,
//                fontWeight = FontWeight.Medium,
//                fontSize = 17.sp,
//                color = MaterialTheme.colorScheme.onSurface
//            )
//            Text(
//                text = blog.description!!,
//                fontWeight = FontWeight.W400,
//                fontSize = 16.sp,
//                lineHeight = 18.sp,
//                color = MaterialTheme.colorScheme.onSurface,
//                maxLines = if(viewModel.showAllDescription) Int.MAX_VALUE else 4,
//                modifier = Modifier.clickable {
//                    viewModel.showAllDescription = !viewModel.showAllDescription
//                }
//            )
//            MSpacer(10)
//            BI_BottomIcon(
//                likes = blog.likes.size,
//                comments = blog.comments.size
//            )
//        }
//    }
}

@Composable
fun BI_BottomIcon(
    modifier: Modifier = Modifier,
    likes: Int,
    comments: Int,
    liked: Boolean,

    onClickLike: () -> Unit
) {
    val numLike = likes + if(liked == true) 1 else 0
    Row (
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp),
        ){
            Icon(
                imageVector = if(liked) Icons.Outlined.Favorite else Icons.Outlined.FavoriteBorder,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .size(16.dp)
                    .clickable { onClickLike() },
            )
            Text(
                text =
                    if(numLike == 0) "0"
                    else if(liked == false) "${likes}"
                    else if(likes == 0) "1"
                    else "bạn và ${likes} người khác",
                fontSize = 14.sp
            )
        }
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ){
            Text(
                text = "${comments.toString()}  bình luận",
                fontSize = 14.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreViewBI_BottomIcon() {
    BI_BottomIcon(
        likes = 0,
        comments = 0,
        liked = false,
        onClickLike = {}
    )
}