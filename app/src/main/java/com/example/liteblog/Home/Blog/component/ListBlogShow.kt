package com.example.liteblog.Home.Blog.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.liteblog.Home.Blog.BlogItem
import com.example.liteblog.utils.Component.MSpacer
import com.example.liteblog.utils.Model.Blog

@Composable
fun ListBlogShow(
    somethingTop: @Composable() ()->Unit = {},
    modifier: Modifier = Modifier,
    listBlogs: List<Blog>,
    navController: NavController = rememberNavController()
) {
    if(listBlogs.size > 0) {
        var selectImage:String? by rememberSaveable {
            mutableStateOf(null)
        }
        if(selectImage!=null) {
            FullImageScreen(
                uriImage = selectImage!!,
                onDismiss = { selectImage = null },
                modifier = Modifier.zIndex(2f)
            )
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            modifier = modifier.zIndex(1f)
        ) {
            item { somethingTop() }
            items(listBlogs) {blog ->
                Column {
                    BlogItem(
                        blogDefault = blog,
                        modifier = Modifier.padding(10.dp),
                        selectImage = {selectImage = it},
                        navController = navController
                    )
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .height(7.dp)
                        .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.25f)))
                }
            }
        }
    }
    else {
        Column(
            modifier = modifier.fillMaxSize(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            somethingTop()
            MSpacer(20)
            Text(
                text = "Chưa có Blog nào.",
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.onBackground
                )
        }
    }
}