package com.example.liteblog.Home.presentation.CreateBlog.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.liteblog.Home.presentation.CreateBlog.presentation.component.CreateBlog_TopBar
import com.example.liteblog.utils.Component.MSpacer
import com.example.liteblog.utils.Component.UserIconDefault
import com.example.liteblog.utils.Model.UserInfor
import UserData
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.liteblog.ROUTE_BLOG

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun Preview_CreateBlogScreen() {
    Scaffold(
        topBar = { CreateBlog_TopBar()}
    ) {
        CreateBlogMainScreen(
            userInfor = UserInfor(
                "Vũ", "Nguyễn", "huyvu_3107"
            ),
            modifier = Modifier.padding(it)
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CreateBlogScreen(
    navController: NavController = rememberNavController(),
    viewModel: CreateBlogViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()
    LaunchedEffect(key1 = state.isSaved, block = {
        if(state.isSaved == true) {
            navController.navigate(ROUTE_BLOG)
        }
    })
    Scaffold(
        topBar = { CreateBlog_TopBar(
            navController = navController
        )}
    ) {
        CreateBlogMainScreen(
            userInfor = UserData.userinfor,
            modifier = Modifier.padding(it),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateBlogHead(
    userInfor: UserInfor,
    viewModel: CreateBlogViewModel = viewModel()
) {
    Row (
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ){
        UserIconDefault(userinfor = userInfor, size = 50, onClick = { /*TODO*/ })
        Column {
            Text(text = userInfor.username, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            Row (horizontalArrangement = Arrangement.spacedBy(10.dp)){
                FilterChip(
                    selected = true,
                    onClick = { /*TODO*/ },
                    trailingIcon = { Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = null)},
                    label = { Text(text = "Chỉ mình tôi")}
                )
                FilterChip(
                    selected = true,
                    onClick = { /*TODO*/ },
                    trailingIcon = { Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = null)},
                    label = { Text(text = "Album")}
                )
            }
        }
    }
}

@Composable
fun CreateBlogMainScreen(
    modifier: Modifier = Modifier,
    userInfor: UserInfor,
    viewModel: CreateBlogViewModel = viewModel()
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
    ) {
        CreateBlogHead(userInfor)
        OutlinedTextField(
            value = viewModel.title,
            onValueChange = {viewModel.title = it},
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Tiêu đề")},

        )
        OutlinedTextField(
            value = viewModel.description,
            onValueChange = {viewModel.description = it},
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            label = { Text(text = "Nội dung")},
            )
        MSpacer(10)
        Divider()
        MSpacer(10)
        OutlinedButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
            Text(text = "Thêm ảnh")
        }
    }
}