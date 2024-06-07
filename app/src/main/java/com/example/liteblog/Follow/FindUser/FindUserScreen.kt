package com.example.liteblog.Follow.FindUser

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.liteblog.Follow.components.ItemFriendFind
import com.example.liteblog.Screen
import com.example.liteblog.utils.Component.MSpacer
import com.example.liteblog.utils.Component.MyBasicTopBar
import com.example.liteblog.utils.Component.MyTextField

@Preview(showBackground = true)
@Composable
fun PreViewFindUserScreen() {
    FindUserScreen()
}
@Composable
fun FindUserScreen(
    navController: NavController = rememberNavController(),
) {
    Scaffold(
        topBar = {
            MyBasicTopBar(
                navController = navController,
                title = {
                    Text(text = "Tìm kiếm người dùng", fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
                },
            )
        }
    ) {
        FindUserScreen(
            modifier = Modifier.padding(it),
            navController = navController
        )
    }
}
@Composable
fun FindUserScreen(
    modifier: Modifier = Modifier,
    viewModel: FindUserViewModel = viewModel(),
    navController: NavController
) {
    val state by viewModel.state.collectAsState()
    Column(
        modifier = modifier
            .padding(10.dp),
    ) {
        MyTextField(
            value = viewModel.friendInput,
            onValueChange = {viewModel.friendInput = it},
            modifier = Modifier
                .fillMaxWidth(),
            trailingIcon = {
                if(viewModel.friendInput.isNotEmpty()) {
                    IconButton(onClick = { viewModel.friendInput = "" }) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = null)
                    }
                }
            },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = null)
            },
            placeholder = "Tìm kiếm người dùng",
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    viewModel.onFindUser()
                }
            ),
            enabled = !state.isLoading
        )
        MSpacer(30)
        if(viewModel.friendFind != null) {
            ItemFriendFind(
                viewModel.friendFind!!,
                onClick = {
//                    viewModel.onClickFollow()
                    navController.navigate(
                        Screen.PersonalPage.withArgs(viewModel.friendFind!!.username)
                    )
                }
            )
        }
        else {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Tìm và theo dõi người dùng",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                )
                MSpacer(10)
                Text(
                    text = "Hãy tìm kiếm bạn bè hoặc người quen để kết nối với họ trên Lite Blog",
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
