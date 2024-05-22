package com.example.liteblog.Home.Blog

import UserData
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.liteblog.Home.Blog.component.MyBodyBlogItem
import com.example.liteblog.Home.Blog.component.MyRatingButton
import com.example.liteblog.Home.Comment.ScreenComment
import com.example.liteblog.utils.Component.MSpacer
import com.example.liteblog.utils.Component.UserIconDefault
import com.example.liteblog.utils.Data.Database.FB_Blog
import com.example.liteblog.utils.Functions.MyFunction.Companion.parseTimePastToString
import com.example.liteblog.utils.Model.Blog
import com.example.liteblog.utils.Model.UserInfor
import kotlinx.coroutines.launch
import java.time.Instant

val DEBUG = true

@Composable
@Preview(showBackground = true)
fun PreviewBlogItem() {
    BlogItem(
        blogDefault = Blog(
            userinfor = UserInfor(firstname = "Vu", lastname = "Nguyen", username = "huyvu.3107"),
            title = "",
            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
            comments = emptyList(),
            likes = emptyList(),
            reports = emptyList(),
            timePost = Instant.now().toEpochMilli(),
        ),
        modifier = Modifier.padding(10.dp),
        selectImage = {}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BlogItem(
    blogDefault: Blog,
    modifier: Modifier = Modifier,
    selectImage: (String) -> Unit
) {
    var blog by remember {
        mutableStateOf(blogDefault)
    }
    var liked by remember {
        mutableStateOf(blog.likes.contains((UserData.userinfor.username)))
    }
    val userInfor = blog.userinfor!!
    var showAllDescription by rememberSaveable {
        mutableStateOf(false)
    }
    var isShowComment by rememberSaveable {
        mutableStateOf(false)
    }
    val coroutineScope = rememberCoroutineScope()

    FB_Blog.fetch(
        blog = blog,
        onUpdate = {
            blog = it
            liked = it.likes.contains((UserData.userinfor.username))
        }
    )

    if(isShowComment) {
        ModalBottomSheet(
            onDismissRequest = {isShowComment = false},
            sheetState = rememberModalBottomSheetState(
                skipPartiallyExpanded = true
            ),
            dragHandle = {
                Column {
                    MSpacer(10)
                    Text(text = "Bình luận", fontSize = 24.sp, color = MaterialTheme.colorScheme.primary)
                    MSpacer(10)
                }
            }
        ) {
            ScreenComment(
                blogData = blog
            )
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
    ) {
//        HEAD
        Box(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopStart),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                UserIconDefault(userinfor = userInfor, size = 40, onClick = { /*TODO*/ })
                Column {
                    Text(
                        text = userInfor.username,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(top = 0.dp)
                    )
                    Text(
                        text = parseTimePastToString(blog.timePost!!) + (if (DEBUG) " - " + blog.id else ""),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 13.sp
                    )
                }
            }
            MyRatingButton(
                modifier = Modifier.align(Alignment.CenterEnd),
                rating = blog.rating,
                onClickUpRate = {
                           coroutineScope.launch {
                               FB_Blog.userRate(blog = blog, isUpRate = true)
                           }
                },
                onClickDownRate = {
                    coroutineScope.launch {
                        FB_Blog.userRate(blog = blog, isUpRate = false)
                    }
                }
            )
        }
        MSpacer(10)
        MyBodyBlogItem(
            blog = blog,
            showAllDescription = showAllDescription,
            liked = liked,
            onClickDescription = { showAllDescription = !showAllDescription },
            onSelectImage = {
                selectImage(it)
            },
            onShowComment = { isShowComment = !isShowComment },
            onClickLike = {
                coroutineScope.launch {
                    FB_Blog.userLike(blog = blog)
                }
            }
        )
        MSpacer(10)
    }
}

@Composable
fun BI_BottomIcon(
    modifier: Modifier = Modifier,
    comments: Int,
    liked: Boolean,

    onClickLike: () -> Unit,
    onShowComment: () -> Unit
) {
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
                    .size(18.dp)
                    .clickable { onClickLike() },
            )
//            Text(
//                text =
//                    if(numLike == 0) "0"
//                    else if(liked == false) "${likes}"
//                    else if(likes == 1) "1"
//                    else "bạn và ${likes-1} người khác",
//                fontSize = 15.sp
//            )
        }
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ){
            Text(
                text = "${comments.toString()}  bình luận",
                fontSize = 15.sp,
                modifier = Modifier.clickable {
                    onShowComment()
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreViewBI_BottomIcon() {
    BI_BottomIcon(
        comments = 0,
        liked = false,
        onClickLike = {},
        onShowComment = {}
    )
}