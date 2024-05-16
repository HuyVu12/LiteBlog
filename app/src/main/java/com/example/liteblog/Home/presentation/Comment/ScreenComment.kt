package com.example.liteblog.Home.presentation.Comment

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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.liteblog.Home.presentation.Blog.BI_BottomIcon
import com.example.liteblog.Home.presentation.Blog.Sample_Blogs
import com.example.liteblog.Home.presentation.Blog.Sample_Comments
import com.example.liteblog.utils.Component.MSpacer
import com.example.liteblog.utils.Component.UserIconDefault
import com.example.liteblog.utils.Functions.MyFunction
import com.example.liteblog.utils.Model.Blog
import com.example.liteblog.utils.Model.Comment
import com.example.liteblog.utils.Model.UserInfor

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
    ScreenComment(blog = Sample_Blogs[0])
}
@Composable
fun ScreenComment(
    blog: Blog,
    modifier: Modifier = Modifier
) {
    Column (modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)){
        BI_BottomIcon(
            likes = blog.likes.size,
            comments = blog.comments.size,
            liked = true,
            modifier = Modifier,
            onClickLike = {},
            onShowComment = {}
        )
        MSpacer(10)
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(Sample_Comments) {comment ->
                CommentItem(
                    comment = comment,
                    modifier = Modifier.padding(vertical = 5.dp)
                )
            }
        }
        Row (
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            OutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(12.dp),
                placeholder = {
                    Text(text = "Viết bình luận...")
                }
            )
            TextButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Send, contentDescription = null)
            }
        }
    }
}