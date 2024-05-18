package com.example.liteblog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.liteblog.Follow.FindUser.FindUserScreen
import com.example.liteblog.Follow.MyFollower.MyFollowerScreen
import com.example.liteblog.Home.CreateBlog.presentation.CreateBlogScreen
import com.example.liteblog.Home.Main.CheckScreen
import com.example.liteblog.Labs.VertexApi.presentation.VertexApiScreen
import com.example.liteblog.Login.LoginScreen
import com.example.liteblog.Register.RegisterScreen
import com.example.liteblog.Setting.MainSetting.MainSettingScreen
import com.example.liteblog.ui.theme.LiteBlogTheme
import com.example.liteblog.utils.Functions.PickSinglePhoto

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                android.graphics.Color.TRANSPARENT,
                android.graphics.Color.TRANSPARENT,
            )
        )
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
val ROUTE_CREATE_BLOG = "create_blog"
val ROUTE_SETTINGS = "settings"
val ROUTE_PICK_SINGLE_PHOTO = "pick_single_photo"
val ROUTE_LAB_AI = "VertexApi"
val ROUTE_FRIEND = "friend"
val ROUTE_FIND_USER = "find_user"

@Composable
fun MainApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ROUTE_LOGIN) {
        composable(ROUTE_LOGIN) {
            LoginScreen(navController = navController)
        }
        composable(ROUTE_REGISTER) {
            RegisterScreen(navController = navController)
        }
        composable(ROUTE_BLOG) {
            CheckScreen(navController = navController)
        }
        composable(ROUTE_SETTINGS) {
            MainSettingScreen(navController = navController)
        }
        composable(ROUTE_CREATE_BLOG) {
            CreateBlogScreen(navController = navController)
        }
        composable(ROUTE_PICK_SINGLE_PHOTO) {
            PickSinglePhoto(navController)
        }
        composable(ROUTE_LAB_AI) {
            VertexApiScreen(
                navController
            )
        }
        composable(ROUTE_FRIEND) {
            MyFollowerScreen(
                navController = navController
            )
        }
        composable(ROUTE_FIND_USER) {
            FindUserScreen(
                navController = navController
            )
        }
    }
}