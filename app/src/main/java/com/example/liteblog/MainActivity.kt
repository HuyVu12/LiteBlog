package com.example.liteblog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.liteblog.Home.presentation.Main.CheckScreen
import com.example.liteblog.Login.presentation.LoginScreen
import com.example.liteblog.Register.presentation.RegisterScreen
import com.example.liteblog.ui.theme.LiteBlogTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LiteBlogTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainApp()
                }
            }
        }
    }
}

enum class Route(val route: String) {
    Login("login"),
    Register("register"),
    Home("home")
}

@Composable
fun MainApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Route.Login.route) {
        composable(Route.Login.route) {
            LoginScreen(
                navController = navController
            )
        }
        composable(Route.Register.route) {
            RegisterScreen(
                navController = navController
            )
        }
        composable(Route.Home.route) {
            CheckScreen(
                navController = navController
            )
        }
    }
}