package com.example.liteblog.Labs.BlogPostCreater.presentation

import android.content.ContentResolver
import android.content.Context
import android.graphics.ImageDecoder
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.Coil
import coil.compose.AsyncImage
import com.example.liteblog.utils.Component.MSpacer
import com.example.liteblog.utils.Component.MyBasicTopBar
import com.example.liteblog.utils.Component.MyTextField

@Preview
@Composable
fun PreviewBlogPostCreaterScreen() {
    BlogPostCreaterScreen()
}
@Composable
fun BlogPostCreaterScreen(navController: NavController = rememberNavController()) {
    Scaffold(
        topBar = {
            MyBasicTopBar(
                navController = navController,
                title = {
                    Text(
                        text = "Gợi ý AI",
                        fontSize = 20.sp)
                }
            )
        }
    ) {
        Main(modifier = Modifier.padding(it))
    }
}

@Composable
fun Main(
    modifier: Modifier = Modifier,
    viewModel: BlogPostCreaterViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val context = LocalContext.current
    val contentResolver = context.contentResolver

    val pickLaucher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            if(it != null) {
                viewModel.bitmap = MediaStore.Images.Media.getBitmap(
                    contentResolver, it
                )
            }
            viewModel.pickImage = it
        }
    )
    val state by viewModel.state.collectAsState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp, 0.dp, 10.dp, 10.dp)
    ) {
        if (state.isLoading) {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        }
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            item {
                SelectionContainer {
                    Text(text = viewModel.textGenerate)
                }
            }
        }
        if(viewModel.pickImage != null) {
            Divider()
            MSpacer(5)
                AsyncImage(
                    model = viewModel.pickImage,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    filterQuality = FilterQuality.None,
                    modifier = Modifier.height(150.dp)
                )
            MSpacer(5)
            Divider()
        }
        Column {
            Row {
                Button(
                    onClick = {
                        pickLaucher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    },
                    shape = RoundedCornerShape(5.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.tertiary,
                        contentColor = MaterialTheme.colorScheme.onTertiary
                    ),
                    enabled = !state.isLoading
                ) {
                    Text(text = "Chọn ảnh")
                }
            }
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                OutlinedTextField(
                    value = viewModel.textPrompt,
                    onValueChange = {
                        viewModel.textPrompt = it
                    },
                    placeholder = {
                        Text(text = "Mô tả thêm thông tin")
                    },
                    label = {
                        Text(text = "Prompt")
                    },
                    modifier = Modifier.weight(1f),
                    keyboardActions = KeyboardActions(),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                )
                MSpacer(0, 10)
                Button(
                    onClick = { viewModel.genarate() },
                    shape = RoundedCornerShape(5.dp),
                    enabled = !state.isLoading
                ) {
                    Icon(imageVector = Icons.Default.Create, contentDescription = null)
                }
            }
        }
    }
}