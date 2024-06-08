package com.example.liteblog.Home.Blog.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import com.example.liteblog.Screen
import com.example.liteblog.utils.Component.MSpacer
import com.example.liteblog.utils.Data.Database.FB_Blog.Companion.moveToGarbage
import com.example.liteblog.utils.Model.Blog
import kotlinx.coroutines.launch

@Composable
fun MenuSettingBlogItem(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    DropdownMenuItem(
        text = {
            Row {
                Text(text = text)
                MSpacer(0, 10)
                Icon(imageVector = icon, contentDescription = null)
            }
        },
        onClick = { onClick() }
    )
}
@Composable
fun MenuSettingBlog(
    isShowMenu: Boolean,
    onDismissRequest: () -> Unit,
    blog: Blog,
    navController: NavController
) {
    val coroutineScope = rememberCoroutineScope()
    if(isShowMenu) {
        DropdownMenu(expanded = isShowMenu, onDismissRequest = { onDismissRequest() }) {
            MenuSettingBlogItem(
                text = "Chỉnh sửa",
                icon = Icons.Default.Edit,
                onClick = {
                    navController.navigate(Screen.EditBlog.withArgs(blog.id!!))
                }
            )
            MenuSettingBlogItem(
                text = "Ẩn bài viết",
                icon = Icons.Default.Person,
                onClick = {}
            )
            MenuSettingBlogItem(
                text = "Xoá",
                icon = Icons.Default.Delete,
                onClick = {
                    coroutineScope.launch {
                        moveToGarbage(blog)
                    }
                }
            )

        }
    }
}