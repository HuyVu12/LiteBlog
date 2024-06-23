package com.example.liteblog.Home.Comment.component

import UserData
import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Block
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.liteblog.utils.Component.MSpacer
import com.example.liteblog.utils.Data.Database.FB_Blog.Companion.blockUser
import com.example.liteblog.utils.Data.Database.FB_Blog.Companion.removeComment
import com.example.liteblog.utils.Model.Blog
import org.w3c.dom.Comment


@Composable
fun MenuCommentSelect(
    isVisible: Boolean,
    onDissmiss: () -> Unit,
    comment: com.example.liteblog.utils.Model.Comment,
    blog: Blog
) {
    DropdownMenu(expanded = isVisible, onDismissRequest = { onDissmiss() }) {
        if(
            (UserData.username == (blog.userinfor?.username ?: "") && comment.userinfor!!.username != UserData.username)
            || UserData.userinfor.admin == true
            ) {
            DropdownMenuItem(
                text = {
                    Row {
                        Text(text = "Chặn")
                        MSpacer(0, 10)
                        Icon(imageVector = Icons.Default.Block, contentDescription = null)
                    }
                }, onClick = {
                    blockUser(blog = blog, comment = comment)
                }
            )
        }
        if(UserData.username == comment.userinfor!!.username) {
            DropdownMenuItem(
                text = {
                    Row {
                        Text(text = "Chỉnh sửa")
                        MSpacer(0, 10)
                        Icon(imageVector = Icons.Default.Edit, contentDescription = null)
                    }
                }, onClick = {
                }
            )
        }
        DropdownMenuItem(
            text = {
                Row {
                    Text(text = "Xóa")
                    MSpacer(0, 10)
                    Icon(imageVector = Icons.Default.Clear, contentDescription = null)
                }
            }, onClick = {
                removeComment(blog = blog, comment = comment)
            }
        )
    }
}