package com.example.liteblog.Home.EditBlog

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.liteblog.Home.CreateBlog.presentation.CreateBlogHead
import com.example.liteblog.Home.EditBlog.comp.EditBlogHead
import com.example.liteblog.utils.Component.MSpacer
import com.example.liteblog.utils.Component.MyBasicTopBar
import com.example.liteblog.utils.Model.Blog

@Composable
fun EditBlogScreen(
    navController: NavController,
    viewModel: EditBlogViewModel = viewModel(),
    id_Blog: String
) {
    val state by viewModel.state.collectAsState()
    Scaffold(
        topBar = {
            MyBasicTopBar(
                navController = navController,
                title = {Text(text = "Chỉnh sửa bài viết")},
                actions = {
                    Button(
                        onClick = {
                            viewModel.updateBlog()
                        },
                        enabled = !state.isLoading
                    ) {
                        Text(text = "Lưu")
                    }
                }
            )
        }
    ) {
        MainEditBlogScreen(
            modifier = Modifier.padding(it),
            id_blog = id_Blog
        )
    }
}

@Composable
fun MainEditBlogScreen(
    modifier: Modifier = Modifier,
    viewModel: EditBlogViewModel = viewModel(),
    id_blog: String
) {
    viewModel.initBlog(idBlog = id_blog)
    val pickPhotoLaucher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(),
        onResult = {
            if(it.size > 0) {
                viewModel.listImages = it.toMutableList()
            }
        }
    )
    val state by viewModel.state.collectAsState()
    if(state.isLoadData) {

    }
    else {
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
                    EditBlogHead(viewModel.blog.userinfor!!)
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
}