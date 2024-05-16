package com.example.liteblog.Home.presentation.CreateBlog.presentation.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.liteblog.Home.presentation.CreateBlog.presentation.CreateBlogViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun Preview_CB_TopBar() {
    CreateBlog_TopBar(navController = rememberNavController())
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateBlog_TopBar(
    navController: NavController = rememberNavController(),
    viewModel: CreateBlogViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()
    TopAppBar(
        title = { Text(text = "Tạo bài viết") },
        navigationIcon = {
            IconButton(
                onClick = { navController.popBackStack() }
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
        },
        actions = {
            Button(
                modifier = Modifier.padding(end = 5.dp),
                onClick = { viewModel.addBlog() },
                enabled = (
                        !state.isLoading && (viewModel.description.length > 0 || viewModel.title.length > 0 || viewModel.listImages.size > 0)
                        )
            ) {
                Text(text = "Tạo", fontSize = 16.sp)
            }
        }
    )
}