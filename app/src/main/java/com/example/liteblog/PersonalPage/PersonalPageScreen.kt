package com.example.liteblog.PersonalPage

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
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
import com.example.liteblog.utils.Model.Follow
import com.example.liteblog.utils.Model.UserInfor
import androidx.lifecycle.viewmodel.compose.viewModel

@Preview
@Composable
fun PreviewPersonalPageScreen() {
    PersonalPageScreen(
        userInfor = UserInfor(
            username = "huyvu_3107",
            firstname = "Vũ",
            lastname = "Huy"
        )
    )
}
@Composable
fun PersonalPageScreen(
    navController: NavController = rememberNavController(),
    userInfor: UserInfor
) {
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
                }
            )
        }
    ){
        PersonalPageMainScreen(
            modifier = Modifier.padding(it),
            userInfor = userInfor
        )
    }
}

@Composable
fun PersonalPageMainScreen(
    modifier: Modifier = Modifier,
    userInfor: UserInfor,
    viewModel: PersonalPageViewModel = viewModel()
) {
    val photoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            viewModel.selectedImageUri = it
        }
    )
    val state by viewModel.state.collectAsState()

    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            Divider()
            if(state.isLoading) {
                LinearProgressIndicator(modifier.fillMaxWidth())
            }
            MSpacer(20)
            Box {
                Icon(
                    imageVector = if (viewModel.selectedImageUri == null) Icons.Default.Add else Icons.Default.Check,
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
                                viewModel.saveData()
                            }
                        }
                    ,
                    tint = MaterialTheme.colorScheme.primary,
                )
                UserIconDefault(
                    userinfor = userInfor,
                    size = 120,
                    onClick = {},
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
            MSpacer(10)
            Divider(
                modifier = Modifier.height(5.dp)
            )
        }
    }
}

