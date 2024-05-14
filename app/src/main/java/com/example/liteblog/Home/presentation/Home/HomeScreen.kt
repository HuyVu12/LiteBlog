package com.example.liteblog.Home.presentation.Home


import UserStorage
<<<<<<< HEAD
import android.util.Log
=======
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
>>>>>>> 9afc069 (Add BlogItem)
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
<<<<<<< HEAD
=======
import com.example.liteblog.Home.presentation.Blog.BlogScreen

>>>>>>> 9afc069 (Add BlogItem)
@Composable
fun HS_MainScreen(
    viewModel: HomeScreenViewModel = viewModel()
) {

}

<<<<<<< HEAD
=======
@RequiresApi(Build.VERSION_CODES.O)
>>>>>>> 9afc069 (Add BlogItem)
@Preview(showBackground = true)
@Composable
fun HS_MainScreenPreView() {

}