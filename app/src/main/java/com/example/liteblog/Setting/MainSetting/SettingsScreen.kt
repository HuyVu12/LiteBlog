package com.example.liteblog.Setting.MainSetting

import UserStorage
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.liteblog.ROUTE_FIND_USER
import com.example.liteblog.ROUTE_FOLLOWER
import com.example.liteblog.ROUTE_LAB_AI
import com.example.liteblog.ROUTE_LAB_GENATATE_BLOG
import com.example.liteblog.ROUTE_LOGIN
import com.example.liteblog.ROUTE_MY_FOLLOWER
import com.example.liteblog.ROUTE_PERSONAL_PAGE
import com.example.liteblog.Setting.MainSetting.component.CardItemFunction
import com.example.liteblog.utils.Component.MyBasicTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainSettingScreen(
    navController: NavController = rememberNavController()
) {
    Scaffold(
        topBar = {
            MyBasicTopBar(navController = navController, title = { Text(text = "Menu")})
        }
    ) {
        MainSettingsScreen(
            modifier = Modifier.padding(it),
            navController = navController
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainSettingsScreen(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController(),
    viewModel: SettingsViewModel = viewModel()
) {
    val context = LocalContext.current
    val datastorage = UserStorage(context)
    val usernameSaved by datastorage.getUsername.collectAsState(initial = "")

    val state by viewModel.state.collectAsState()

    LaunchedEffect(key1 = state.isExit, block = {
        if (state.isExit) {
            navController.navigate(ROUTE_LOGIN)
        }
    })

    Column (
        modifier = modifier.fillMaxSize()
    ){
        if(state.isLoading){
            LinearProgressIndicator()
        }

        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            modifier = Modifier.weight(1f)
        ) {
            item {
                CardItemFunction(
                    onClick = {
                        navController.navigate(ROUTE_PERSONAL_PAGE)
                    },
                    textValue = "Trang cá nhân",
                    icon = Icons.Default.AccountCircle
                )
            }
            item {
                CardItemFunction(
                    onClick = {
                        navController.navigate(ROUTE_MY_FOLLOWER)
                    },
                    textValue = "Người bạn theo dõi",
                    icon = Icons.Outlined.Star
                )
            }
            item {
                CardItemFunction(
                    onClick = {
                        navController.navigate(ROUTE_FOLLOWER)
                    },
                    textValue = "Người theo dõi",
                    icon = Icons.Outlined.Favorite
                )
            }
            item {
                CardItemFunction(
                    onClick = {
                        navController.navigate(ROUTE_FIND_USER)
                    },
                    textValue = "Tìm kiếm",
                    icon = Icons.Default.Search
                )
            }
            item {
                CardItemFunction(
                    onClick = {
                        navController.navigate(ROUTE_LAB_AI)
                    },
                    textValue = "Chat AI",
                    icon = Icons.Default.Build
                )
            }
            item {
                CardItemFunction(
                    onClick = {
                        navController.navigate(ROUTE_LAB_GENATATE_BLOG)
                    },
                    textValue = "Gợi ý AI",
                    icon = Icons.Default.Build
                )
            }
        }
        Divider()
        TextButton(
            onClick = { viewModel.onLogout(datastorage) },
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = MaterialTheme.colorScheme.error,
//                containerColor = MaterialTheme.colorScheme.errorContainer
            ),
            enabled = !state.isLoading,
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(1.dp))
                .padding(10.dp)
//                .border(
//                    1.dp,
//                    MaterialTheme.colorScheme.onErrorContainer,
//                    RoundedCornerShape(10.dp)
//                )
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = "Đăng xuất")
                Icon(
                    imageVector = Icons.Outlined.ExitToApp,
                    contentDescription = null,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainMainSettingScreen() {
    MainSettingsScreen()
}