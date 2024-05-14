package com.example.liteblog.utils.Component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import kotlinx.coroutines.delay

@Preview(showBackground = true)
@Composable
fun MyDialogLoadingPreview() {
    MyDialogLoading(
        isShowDialog = true,
        title = "Thông báo",
        infor = "Tên đăng nhập đã tồn tại"
    )
}

@Composable
fun MyDialogLoading(
    modifier: Modifier = Modifier,
    isShowDialog: Boolean = false,
    timeDelay: Int = 1000,
    title: String,
    infor: String,
    onDismissRequest: () -> Unit = {}
) {
    var progress by remember {
        mutableStateOf(0)
    }
    LaunchedEffect(key1 = progress, block = {
        if(progress < 100) {
            progress += 1
            delay(100)
        }
    })
    if(progress < 100 && isShowDialog) {
        Box (
            modifier = modifier
                .background(Color.White)
                .width(200.dp)
                .border(1.dp, Color.LightGray, shape = RoundedCornerShape(
                    bottomEnd = 12.dp,
                    bottomStart = 12.dp
                ))
        ){
            LinearProgressIndicator(
                progress = progress/100f,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopStart)
            )
            Column (
                modifier = Modifier.align(Alignment.TopStart).padding(10.dp),
                ){
                Text(text = title, fontSize = 22.sp, fontWeight = FontWeight.SemiBold)
                MSpacer(22)
                Text(text = infor, fontSize = 16.sp)
            }
        }
    }
}