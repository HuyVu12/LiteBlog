package com.example.liteblog.Follow.MyFollower

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.liteblog.Follow.components.ItemFriendDefault
import com.example.liteblog.ROUTE_FIND_USER
import com.example.liteblog.Screen
import com.example.liteblog.utils.Component.MSpacer
import com.example.liteblog.utils.Component.MyBasicTopBar
import com.example.liteblog.utils.Component.MyTextField

@Preview(showBackground = true)
@Composable
fun PreViewMyFollowScreen() {
    MyFollowerScreen()
}
@Composable
fun MyFollowerScreen(
    navController: NavController = rememberNavController(),
) {
    Scaffold(
        topBar = {
            MyBasicTopBar(
                navController = navController,
                 title = {
                    Text(text = "Theo dõi", fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
                },
                actions = {
                    TextButton(onClick = { navController.navigate(ROUTE_FIND_USER) }) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = null)
                    }
                }
            )
        }
    ) {
        MainMyFollowerScreen(
            modifier = Modifier.padding(it),
            navController = navController
        )
    }
}
@Composable
fun MainMyFollowerScreen(
    modifier: Modifier = Modifier,
    viewModel: MyFollowerViewModel = viewModel(),
    navController: NavController
) {
    val state by viewModel.state.collectAsState()
    AnimatedVisibility(!state.isLoading) {
        LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
    }
    Column(
        modifier = modifier
            .padding(horizontal = 15.dp),
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
                    viewModel.onFindFriend()
                }
            ),
        )
        MSpacer(10)
        Text(
            text = "${state.follow.myFollowers.size} người dùng",
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal
            )
        MSpacer(15)
        LazyColumn {
            items(state.follow.myFollowers) { userInfor ->
                MSpacer(10)
                ItemFriendDefault(
                    user = userInfor,
                    onClick = {
                        navController.navigate(Screen.PersonalPage.withArgs(userInfor.username))
                    }
                )
                MSpacer(10)
                Divider()
            }
        }
    }
}

