package com.example.liteblog.Home.EditBlog.comp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.liteblog.Home.CreateBlog.presentation.CreateBlogViewModel
import com.example.liteblog.Home.CreateBlog.presentation.component.TopicMenu
import com.example.liteblog.Home.CreateBlog.presentation.component.ViewModeMenu
import com.example.liteblog.Home.EditBlog.EditBlogViewModel
import com.example.liteblog.utils.Component.UserIconDefault
import com.example.liteblog.utils.Model.UserInfor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditBlogHead(
    userInfor: UserInfor,
    viewModel: EditBlogViewModel = viewModel()
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
                        trailingIcon = { Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = null) },
                        label = { Text(text = viewModel.viewMode) }
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
                        trailingIcon = { Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = null) },
                        label = { Text(text = viewModel.topic) },
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