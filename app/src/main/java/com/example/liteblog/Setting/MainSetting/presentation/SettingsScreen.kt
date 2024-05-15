package com.example.liteblog.Setting.MainSetting.presentation

import UserStorage
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.liteblog.Home.presentation.component.HS_TopAppBar
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.liteblog.ROUTE_LOGIN

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainSettingScreen(
    navController: NavController = rememberNavController()
) {
    Scaffold(
        topBar = {
            HS_TopAppBar(
                navController = navController
            )
        }
    ) {
        MainSettingsScreen(
            modifier = Modifier.padding(it),
            navController = navController
        )
    }
}

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
        OutlinedButton(
            onClick = { viewModel.onLogout(datastorage) },
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = MaterialTheme.colorScheme.onErrorContainer,
                containerColor = MaterialTheme.colorScheme.errorContainer
            ),
            enabled = !state.isLoading,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
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