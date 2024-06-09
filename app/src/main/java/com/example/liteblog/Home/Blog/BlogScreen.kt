package com.example.liteblog.Home.Blog

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.liteblog.Home.Blog.component.FullImageScreen
import com.example.liteblog.Home.Blog.component.ListBlogShow
import com.example.liteblog.Home.Blog.component.MenuTopicSelect
import com.example.liteblog.utils.Data.Database.FB_Blog
import com.example.liteblog.utils.Model.Blog
import kotlin.math.log

@Preview(showBackground = true)
@Composable
fun PreviewBlogScreen() {
    BlogScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("SuspiciousIndentation")
@Composable
fun BlogScreen(
    modifier: Modifier = Modifier,
    viewModel: BlogScreenViewModel = viewModel(),
    navController: NavController = rememberNavController()
) {
    val state by viewModel.state.collectAsState()

    Box(modifier = modifier) {
        Row (
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .zIndex(10f)
                .fillMaxWidth()
                .background(Color.Transparent.copy(alpha = 0f)),
            horizontalArrangement = Arrangement.Center
        ){
            Column {
                FilterChip(
                    selected = true,
                    onClick = {
                        viewModel.isShowTopicMenu = !viewModel.isShowTopicMenu
                    },
                    trailingIcon = { Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = null) },
                    label = { Text(text = viewModel.topicSelect) }
                )
                MenuTopicSelect(
                    expanded =  viewModel.isShowTopicMenu,
                    onDismissRequest = { viewModel.isShowTopicMenu = !viewModel.isShowTopicMenu },
                    onSelectItem = {
                        viewModel.topicSelect = it
                        viewModel.isShowTopicMenu = !viewModel.isShowTopicMenu
                        viewModel.selectTopic()
                    })
            }
        }
        ListBlogShow(
            modifier = Modifier,
            listBlogs = state.listBlogs,
            navController = navController
        )
    }
}