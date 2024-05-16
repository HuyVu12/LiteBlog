package com.example.liteblog.utils.Functions

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.liteblog.utils.Component.MyBasicTopBar
import com.example.liteblog.utils.Storage.FireStorage

@Preview(showBackground = true)
@Composable
fun PreviewPickSinglePhoto() {
    PickSinglePhoto()
}
@Composable
fun PickSinglePhoto(
    navController: NavHostController = rememberNavController()
) {
    var selectedImageUri by rememberSaveable {
        mutableStateOf<Uri?>(null)
    }
    var saveImageNotice by remember {
        mutableStateOf(false)
    }
    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            if(it != null)
                selectedImageUri = it
            Log.i("HuyVu", "${it}")
        }
    )

    LaunchedEffect(key1 = saveImageNotice, block = {
        if(selectedImageUri != null) {
            FireStorage.uploadImage(selectedImageUri!!)
        }
    })

    Scaffold (
        topBar = { MyBasicTopBar(navController = navController, title = { Text(text = "Pick Photo")}) }
    ){
        Box(modifier = Modifier
            .padding(it)
            .padding(bottom = 10.dp)
            .fillMaxSize())
        {
            AsyncImage(
                model = selectedImageUri,
                contentDescription = null,
                modifier = Modifier.align(Alignment.Center)
                )
            Row (
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                OutlinedButton(onClick = {
                    photoPickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                }) {
                    Text(text = "Load Image")
                }
                OutlinedButton(onClick = {
                    saveImageNotice = !saveImageNotice
                }) {
                    Text(text = "Save Image")
                }
            }
        }
    }
}
