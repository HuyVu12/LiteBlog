package com.example.liteblog

import UserData
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.liteblog.Follow.FindUser.FindUserScreen
import com.example.liteblog.Follow.Follower.FollowerScreen
import com.example.liteblog.Follow.MyFollower.MyFollowerScreen
import com.example.liteblog.Home.CreateBlog.presentation.CreateBlogScreen
import com.example.liteblog.Home.EditBlog.EditBlogScreen
import com.example.liteblog.Home.Main.CheckScreen
import com.example.liteblog.Labs.BlogPostCreater.presentation.BlogPostCreaterScreen
import com.example.liteblog.Labs.VertexApi.presentation.VertexApiScreen
import com.example.liteblog.Login.LoginScreen
import com.example.liteblog.PersonalPage.PersonalPageScreen
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

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
    object Home : Screen("home")
    object Blog : Screen("blog")
    object CreateBlog : Screen("create_blog")
    object Settings : Screen("settings")
    object PickSinglePhoto : Screen("pick_single_photo")
    object VertexApi : Screen("VertexApi")
    object MyFollower : Screen("my_follower")
    object FindUser : Screen("find_user")
    object Follower : Screen("follower")
    object PersonalPage : Screen("personal_page")
    object ApiCreateBlog : Screen("Api_create_Blog")
    object EditBlog: Screen(route = "edit_blog")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}

val ROUTE_LOGIN = "login"
val ROUTE_REGISTER = "register"
val ROUTE_HOME = "home"
val ROUTE_BLOG = "blog"
val ROUTE_CREATE_BLOG = "create_blog"
val ROUTE_SETTINGS = "settings"
val ROUTE_PICK_SINGLE_PHOTO = "pick_single_photo"
val ROUTE_LAB_AI = "VertexApi"
val ROUTE_MY_FOLLOWER = "my_follower"
val ROUTE_FIND_USER = "find_user"
val ROUTE_FOLLOWER = "follower"
val ROUTE_PERSONAL_PAGE = "personal_page"
val ROUTE_LAB_GENATATE_BLOG = "Api_create_Blog"

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
        composable(ROUTE_MY_FOLLOWER) {
            MyFollowerScreen(
                navController = navController
            )
        }
        composable(ROUTE_FIND_USER) {
            FindUserScreen(
                navController = navController
            )
        }
        composable(ROUTE_FOLLOWER) {
            FollowerScreen(
                navController
            )
        }
        composable(
            route = Screen.PersonalPage.route + "/{username}",
            arguments = listOf(
                navArgument("username") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = ""
                }
            )
        ) {
            val username = it.arguments?.getString("username")
            Log.i("HuyVu", username?:"")
            PersonalPageScreen(
                navController = navController,
                userInforDefault = UserData.userinfor,
                username = username?:""
            )
        }
        composable(
            route = Screen.EditBlog.route + "/{id_Blog}",
            arguments = listOf(
                navArgument("id_Blog") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = ""
                }
            )
        ) {
            val id_Blog = it.arguments?.getString("id_Blog")
            Log.i("HuyVu", id_Blog?:"no_id")
            EditBlogScreen(navController = navController, id_Blog = id_Blog?:"")
        }
        composable(ROUTE_LAB_GENATATE_BLOG) {
            BlogPostCreaterScreen(
                navController = navController
            )
        }
    }
}