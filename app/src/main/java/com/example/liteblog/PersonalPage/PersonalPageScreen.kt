package com.example.liteblog.PersonalPage

import UserData
import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.liteblog.utils.Component.MSpacer
import com.example.liteblog.utils.Component.MyBasicTopBar
import com.example.liteblog.utils.Component.UserIconDefault
import com.example.liteblog.utils.Model.UserInfor
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.liteblog.Home.Blog.component.ListBlogShow
import com.example.liteblog.ROUTE_CREATE_BLOG

@Preview
@Composable
fun PreviewPersonalPageScreen() {
    PersonalPageScreen(
        userInforDefault = UserInfor(
            username = "huyvu_3107",
            firstname = "Vũ",
            lastname = "Huy"
        )
    )
}
@SuppressLint("SuspiciousIndentation")
@Composable
fun PersonalPageScreen(
    navController: NavController = rememberNavController(),
    userInforDefault: UserInfor,
    username: String = "",
    viewModel: PersonalPageViewModel = viewModel(),
) {
    var userInfor by remember {
        mutableStateOf(userInforDefault)
    }
    val state by viewModel.state.collectAsState()
    LaunchedEffect(key1 = viewModel.userInfor) {
        if(username == "" || username == UserData.username) {
            viewModel.getBlogs()
        }
        else {
            if(viewModel.userInfor == null)
                viewModel.initUser(username = username)
            else userInfor = viewModel.userInfor!!
        }
    }
    Scaffold (
        topBar = {
            MyBasicTopBar(
                navController = navController,
                title = {
                    Text(
                        text = userInfor.username,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                actions = {
                    if(viewModel.selectedImageUri != null)
                    TextButton(
                        onClick = {
                            viewModel.saveData()
                        }
                    ) {
                        Text(
                            text = "Lưu",
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        }
    ){
        if(!state.isLoadData)
        PersonalPageMainScreen(
            modifier = Modifier.padding(it),
            userInfor = userInfor,
            navController = navController
        )
    }
}

@Composable
fun PersonalPageMainScreen(
    modifier: Modifier = Modifier,
    userInfor: UserInfor,
    viewModel: PersonalPageViewModel = viewModel(),
    navController: NavController = rememberNavController()
) {
    val photoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            viewModel.selectedImageUri = it
        }
    )
    val state by viewModel.state.collectAsState()

    ListBlogShow(
        somethingTop = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Divider()
                if(state.isLoading) {
                    LinearProgressIndicator(Modifier.fillMaxWidth())
                }
                MSpacer(20)
                Box {
                    if(userInfor.username == UserData.username)
                    Icon(
                        imageVector = if (viewModel.selectedImageUri == null) Icons.Default.Add else Icons.Default.Close,
                        contentDescription = null,
                        modifier = Modifier
                            .clip(shape = CircleShape)
                            .size(30.dp)
                            .zIndex(1f)
                            .background(MaterialTheme.colorScheme.onPrimary)
                            .border(1.dp, MaterialTheme.colorScheme.primary, CircleShape)
                            .align(Alignment.BottomEnd)
                            .clickable {
                                if (viewModel.selectedImageUri == null) {
                                    photoPicker.launch(
                                        input = PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                    )
                                } else {
                                    viewModel.selectedImageUri = null
                                }
                            }
                        ,
                        tint = MaterialTheme.colorScheme.primary,
                    )
                    UserIconDefault(
                        userinfor = userInfor,
                        size = 120,
                        onClick = {},
                        uriImage = viewModel.selectedImageUri
                    )
                }
                MSpacer(10)
                Text(
                    text = userInfor.lastname + " " + userInfor.firstname,
                    fontSize = 30.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.SemiBold
                )
                MSpacer(10)
                if(userInfor.username == UserData.username) {
                    Button(
                        onClick = {
                            navController.navigate(ROUTE_CREATE_BLOG)
                        },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = MaterialTheme.colorScheme.onPrimary,
                            containerColor = MaterialTheme.colorScheme.primary
                        ),
                        modifier = Modifier.fillMaxWidth(.9f)
                    ) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = null)
                        MSpacer(0, 10)
                        Text(
                            text = "Viết thêm Blog"
                        )
                    }
                    MSpacer(10)
                    Button(
                        onClick = { /*TODO*/ },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                            containerColor = MaterialTheme.colorScheme.secondaryContainer
                        ),
                        modifier = Modifier.fillMaxWidth(.9f)
                    ) {
                        Icon(imageVector = Icons.Default.Create, contentDescription = null)
                        MSpacer(0, 10)
                        Text(text = "Chỉnh sửa thông tin cá nhân")
                    }
                }
                else {
                    Button(
                        onClick = { /*TODO*/ },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = MaterialTheme.colorScheme.onPrimary,
                            containerColor = MaterialTheme.colorScheme.primary
                        ),
                        modifier = Modifier.fillMaxWidth(.9f)
                    ) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = null)
                        MSpacer(0, 10)
                        Text(
                            text = "Theo dõi"
                        )
                    }
                }
                MSpacer(10)
                Divider(
                    modifier = Modifier.height(5.dp)
                )
            }
        },
        listBlogs = state.listBlogs,
        modifier = modifier,
        navController = navController
    )

}

