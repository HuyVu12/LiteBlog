package com.example.liteblog.Home.presentation.Blog

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.liteblog.Home.presentation.Blog.component.FullImageScreen
import kotlin.math.log

@Preview(showBackground = true)
@Composable
fun PreviewBlogScreen() {
    BlogScreen()
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun BlogScreen(
    modifier: Modifier = Modifier,
    viewModel: BlogScreenViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()
    var selectImage:String? by rememberSaveable {
        mutableStateOf(null)
    }
    if(selectImage!=null) {
        FullImageScreen(
            uriImage = selectImage!!,
            onDismiss = { selectImage = null },
            modifier = Modifier.zIndex(1f)
        )
    }
//    else
    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        modifier = modifier
    ) {
        items(state.listBlogs) {blog ->
            Column {
                BlogItem(
                    blogDefault = blog,
                    modifier = Modifier.padding(10.dp),
                    selectImage = {selectImage = it}
                )
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(0.5.dp)
                    .background(Color.LightGray))
            }
        }
    }
}