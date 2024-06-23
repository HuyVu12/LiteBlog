package com.example.liteblog.Home.Blog.component

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.liteblog.Home.Blog.BI_BottomIcon
import com.example.liteblog.utils.Component.MSpacer
import com.example.liteblog.utils.Model.Blog

@Composable
fun MyBodyBlogItem(
    blog: Blog,
    showAllDescription: Boolean,
    liked: Boolean,
    onClickDescription:() -> Unit,
    onSelectImage:(String) -> Unit,
    onShowComment: () -> Unit,
    onClickLike: () -> Unit
) {
    SelectionContainer {
        Column {
            if(blog.title!!.isNotEmpty()) {
                Text(
                    text = blog.title!!,
                    fontWeight = FontWeight.Medium,
                    fontSize = 17.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            if(blog.description!!.isNotEmpty()) {
                Text(
                    text = blog.description!!,
                    fontWeight = FontWeight.W400,
                    fontSize = 16.sp,
                    lineHeight = 18.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = if(showAllDescription) Int.MAX_VALUE else 7,
                    modifier = Modifier
                        .clickable {
                            onClickDescription()
                        }
                        .animateContentSize()
                )
            }
        }
    }
    if(blog.imageList != null && blog.imageList!!.size > 0) {
        MSpacer(10)
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
            ,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(blog.imageList!!) {uri ->
                AsyncImage(
                    model = uri,
                    contentDescription = null,
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(12.dp))
                        .clickable {
                            onSelectImage(uri)
                        }
                        .height(300.dp),
                    filterQuality = FilterQuality.None,
                    contentScale = ContentScale.Crop
                )
            }
        }
    }

    MSpacer(10)
    BI_BottomIcon(
        comments = blog.comments.size,
        liked = liked,
        onClickLike = {
            onClickLike()
        },
        onShowComment = {
            onShowComment()
        }
    )
}