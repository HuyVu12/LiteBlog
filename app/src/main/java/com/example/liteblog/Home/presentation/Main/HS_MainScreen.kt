package com.example.liteblog.Home.presentation.Main

import UserStorage
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.liteblog.Home.presentation.Blog.BlogScreen
import com.example.liteblog.Home.presentation.component.HS_TopAppBar
import userData

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CheckScreen(
    navController: NavController = rememberNavController(),
    viewModel: MainScreenViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current
    val datastorage = UserStorage(context)
    val username by datastorage.getUsername.collectAsState(initial = "")

    LaunchedEffect(key1 = username, block = {
        if(username == "no_data") {
            Log.i("huyvu -> HomeScreen_01", username!!)
            navController.navigate("login")
        }
        else if(username!!.length > 0) {
            userData.username = username!!
            viewModel.checkinLogin()
        }
    })
    if(state.isLogin) {
        MainScreen()
    }
}
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            HS_TopAppBar(
                scrollBehavior = scrollBehavior
            )
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            BlogScreen()
        }
    }
}