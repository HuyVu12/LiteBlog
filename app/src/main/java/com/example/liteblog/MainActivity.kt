package com.example.liteblog

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
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
import com.example.liteblog.Setting.MainSetting.presentation.MainSettingScreen
import com.example.liteblog.ui.theme.LiteBlogTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
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

//enum class Route(val route: String) {
//    Login("login"),
//    Register("register"),
//    Home("home")
//}

val ROUTE_LOGIN = "login"
val ROUTE_REGISTER = "register"
val ROUTE_HOME = "home"
val ROUTE_BLOG = "blog"
val ROUTE_MAINSETTING = "main_setting"

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ROUTE_BLOG) {
        composable(ROUTE_LOGIN) {
            LoginScreen(
                navController = navController
            )
        }
        composable(ROUTE_REGISTER) {
            RegisterScreen(
                navController = navController
            )
        }
        composable(ROUTE_BLOG) {
            CheckScreen(
                navController = navController
            )
        }
        composable(ROUTE_MAINSETTING) {
            MainSettingScreen(
                navController = navController)
        }
    }
}