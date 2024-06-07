package com.example.liteblog.Home.CreateBlog.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.liteblog.Home.CreateBlog.presentation.component.CreateBlog_TopBar
import com.example.liteblog.utils.Component.MSpacer
import com.example.liteblog.utils.Component.UserIconDefault
import com.example.liteblog.utils.Model.UserInfor
import UserData
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import coil.compose.AsyncImage
import com.example.liteblog.Home.CreateBlog.presentation.component.TopicMenu
import com.example.liteblog.Home.CreateBlog.presentation.component.ViewModeMenu
import com.example.liteblog.ROUTE_BLOG

@Preview
@Composable
fun Preview_CreateBlogScreen() {
    Scaffold(
        topBar = { CreateBlog_TopBar() }
    ) {
        CreateBlogMainScreen(
            userInfor = UserInfor(
                "Vũ", "Nguyễn", "huyvu_3107"
            ),
            modifier = Modifier.padding(it)
        )
    }
}

@Composable
fun CreateBlogScreen(
    navController: NavController = rememberNavController(),
    viewModel: CreateBlogViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()
    LaunchedEffect(key1 = state.isSaved, block = {
        if(state.isSaved) {
            navController.navigate(ROUTE_BLOG)
        }
    })
    Scaffold(
        topBar = { CreateBlog_TopBar(
            navController = navController
        )
        }
    ) {
        CreateBlogMainScreen(
            userInfor = UserData.userinfor,
            modifier = Modifier.padding(it),
        )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateBlogHead(
    userInfor: UserInfor,
    viewModel: CreateBlogViewModel = viewModel()
) {
    Row (
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ){
        UserIconDefault(userinfor = userInfor, size = 50, onClick = { /*TODO*/ })
        Column {
            Text(text = userInfor.username, fontSize = 18.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.fillMaxWidth())
            Row (horizontalArrangement = Arrangement.spacedBy(10.dp)){
                Column {
                    FilterChip(
                        selected = true,
                        onClick = {
                                  viewModel.isShowViewModeMenu = !viewModel.isShowViewModeMenu
                        },
                        trailingIcon = { Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = null)},
                        label = { Text(text = viewModel.viewMode)}
                    )
                    if(viewModel.isShowViewModeMenu) {
                        ViewModeMenu (
                            expanded = viewModel.isShowViewModeMenu,
                            onDismissRequest = {
                                viewModel.isShowViewModeMenu = !viewModel.isShowViewModeMenu
                            },
                            onSelectItem = {
                                viewModel.viewMode = it
                                viewModel.isShowViewModeMenu = !viewModel.isShowViewModeMenu
                            }
                        )
                    }
                }
                Column {
                    FilterChip(
                        selected = true,
                        onClick = {
                            viewModel.isShowTopicMenu = !viewModel.isShowTopicMenu
                        },
                        trailingIcon = { Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = null)},
                        label = { Text(text = viewModel.topic)},
                    )
                    if(viewModel.isShowTopicMenu){
                        TopicMenu (
                            expanded = viewModel.isShowTopicMenu,
                            onDismissRequest = {
                                viewModel.isShowTopicMenu = !viewModel.isShowTopicMenu
                            },
                            onSelectItem = {
                                viewModel.topic = it
                                viewModel.isShowTopicMenu = !viewModel.isShowTopicMenu
                            }
                        )
                    }

                }
            }
        }
    }
}
@Composable
fun CreateBlogMainScreen(
    modifier: Modifier = Modifier,
    userInfor: UserInfor,
    viewModel: CreateBlogViewModel = viewModel()
) {
    val pickPhotoLaucher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(),
        onResult = {
            if(it.size > 0) {
                viewModel.listImages = it.toMutableList()
            }
        }
    )
    val state by viewModel.state.collectAsState()

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        if(state.isLoading){
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            MSpacer(10)
        }
        LazyColumn(
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
        ) {
            item {
                CreateBlogHead(userInfor)
                OutlinedTextField(
                    value = viewModel.title,
                    onValueChange = {viewModel.title = it},
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Tiêu đề")},
                    maxLines = 3,
                    textStyle = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    ),
                    enabled = !state.isLoading
                )
                OutlinedTextField(
                    value = viewModel.description,
                    onValueChange = {viewModel.description = it},
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    label = { Text(text = "Nội dung")},
                    minLines = 10,
                    enabled = !state.isLoading
                )
                MSpacer(10)
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ){
                    items(viewModel.listImages) {uriImage ->
                        AsyncImage(
                            model = uriImage,
                            contentDescription = null,
                            modifier = Modifier
                                .widthIn(0.dp, 300.dp)
                                .heightIn(0.dp, 300.dp)
                                .clip(shape = RoundedCornerShape(12.dp))
                                .wrapContentHeight(),
                            )
                        Divider()
                    }
                }
            }
            item {
                MSpacer(10)
                Divider()
                MSpacer(10)
                Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)){
                    OutlinedButton(
                        onClick = {
                            pickPhotoLaucher.launch(
                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                            )
                        },
                        enabled = !state.isLoading
                    ) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = null)
                        Text(text = "Chọn ảnh")
                    }

                    Button(
                        onClick = {
                            viewModel.listImages = mutableListOf()
                        },
                        enabled = !state.isLoading,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.errorContainer,
                            contentColor = MaterialTheme.colorScheme.onErrorContainer
                        )
                    ) {
                            Icon(imageVector = Icons.Default.Close, contentDescription = null)
                            Text(text = "Xóa")
                    }
                }
            }
        }
    }
}