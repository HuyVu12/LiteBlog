package com.example.liteblog.Home.Comment

import UserData
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.liteblog.Home.Blog.BI_BottomIcon
import com.example.liteblog.Home.Blog.Sample_Blogs
import com.example.liteblog.utils.Component.MSpacer
import com.example.liteblog.utils.Component.UserIconDefault
import com.example.liteblog.utils.Functions.MyFunction
import com.example.liteblog.utils.Model.Blog
import com.example.liteblog.utils.Model.Comment
import com.example.liteblog.utils.Model.UserInfor
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.liteblog.utils.Data.Database.FBfetchAutoUpdateBlog
import com.example.liteblog.utils.Data.Database.FBupdateBlog

@Preview(showBackground = true)
@Composable
fun PreviewCommentItem(
    comment: Comment = Comment(
        userinfor = UserInfor(
            username = "huyvu_3107",
            firstname = "Vũ"
        ),
        description = "Hello World!",
        timePost = MyFunction.getCurrentTime(),
        likes = emptyList(),
        reports = emptyList()
    )
) {
    CommentItem(comment = comment)
}
@Composable
fun CommentItem(
    comment: Comment,
    modifier: Modifier = Modifier
) {
    Row (
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ){
        UserIconDefault(
            userinfor = comment.userinfor!!,
            size = 40,
            onClick = { /*TODO*/ }
        )
        Column {
            Column(
                modifier = Modifier
//                .weight(1f)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(18.dp))
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .padding(10.dp)
            ) {
                Text(
                    text = comment.userinfor.username,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
                MSpacer(5)
                Text(
                    text = "${comment.description}",
                    fontSize = 15.sp,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
            Text(
                text = MyFunction.parseTimePastToString(timePost = comment.timePost!!),
                modifier = Modifier.padding(start = 10.dp, top = 5.dp),
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewScreenComment() {
    ScreenComment(blogData = Sample_Blogs[0])
}

private fun onSubmit(
    descrtiption: String,
    comment: Comment,
    blogData: Blog
){
    if(descrtiption.isNotBlank()) {
        comment.description = descrtiption
        comment.timePost = MyFunction.getCurrentTime()
        val comments = blogData.comments.toMutableList()
        Log.i("huyVu", "${blogData.comments}")
        comments.add(0, comment)
        blogData.comments = comments
        Log.i("huyVu", "${blogData.comments}")
        FBupdateBlog(blogData)
    }
}

@Composable
fun ScreenComment(
    blogData: Blog,
) {
    var descrtiption by rememberSaveable {
        mutableStateOf("")
    }
    var comment = Comment(
        userinfor = UserData.userinfor
    )
    Column (modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)){
        BI_BottomIcon(
            likes = blogData.likes.size,
            comments = blogData.comments.size,
            liked = true,
            modifier = Modifier,
            onClickLike = {},
            onShowComment = {}
        )
        MSpacer(10)
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(blogData.comments) { comment ->
                CommentItem(
                    comment = comment,
                    modifier = Modifier.padding(vertical = 5.dp)
                )
            }
        }
        Divider()
        Row (
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 10.dp)
        ){
            OutlinedTextField(
                value = descrtiption,
                onValueChange = {
                    descrtiption = it
                },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(12.dp),
                placeholder = {
                    Text(text = "Viết bình luận...")
                }
            )
            TextButton(onClick = {
                onSubmit(
                    descrtiption = descrtiption,
                    comment = comment,
                    blogData = blogData
                )
                descrtiption = ""
            }) {
                Icon(imageVector = Icons.Default.Send, contentDescription = null)
            }
        }
    }
}