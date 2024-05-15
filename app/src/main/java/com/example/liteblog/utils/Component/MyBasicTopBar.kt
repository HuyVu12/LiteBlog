package com.example.liteblog.utils.Component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.liteblog.Home.presentation.CreateBlog.presentation.component.CreateBlog_TopBar

@Preview(showBackground = true)
@Composable
fun Preview_MyBasicTopBar() {
    MyBasicTopBar(
        navController = rememberNavController(),
        title = { Text(text = "Hello World")}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBasicTopBar(
    navController: NavController,
    title: @Composable () -> Unit
) {
    TopAppBar(
        title = title,
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
        },
    )
}