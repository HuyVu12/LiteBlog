package com.example.liteblog.Setting.MainSetting

import UserStorage
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.liteblog.ROUTE_FRIEND
import com.example.liteblog.ROUTE_LOGIN
import com.example.liteblog.utils.Component.MSpacer
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
        Card(
            onClick = { navController.navigate(ROUTE_FRIEND)},
            elevation = CardDefaults.cardElevation(
                defaultElevation = 5.dp
            ),
            modifier = Modifier.padding(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer
            )
        ) {
            Column (
                modifier = Modifier.padding(15.dp)
            ){
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    modifier = Modifier.size(25.dp)
                    )
                MSpacer(5)
                Text(
                    text = "Người theo dõi",
                    fontSize = 16.sp
                )
            }
        }
        TextButton(
            onClick = { viewModel.onLogout(datastorage) },
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = MaterialTheme.colorScheme.onErrorContainer,
//                containerColor = MaterialTheme.colorScheme.errorContainer
            ),
            enabled = !state.isLoading,
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(1.dp))
                .padding(10.dp)
                .border(
                    1.dp,
                    MaterialTheme.colorScheme.onErrorContainer,
                    RoundedCornerShape(10.dp)
                )
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