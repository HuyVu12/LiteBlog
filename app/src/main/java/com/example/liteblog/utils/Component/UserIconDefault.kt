package com.example.liteblog.utils.Component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.liteblog.utils.Model.User
import com.example.liteblog.utils.Model.UserInfor

@Preview
@Composable
fun PreviewUserIconDefault() {
    UserIconDefault(
        userinfor = UserInfor(firstname = "Vu"),
        size = 40,
        onClick = {}
    )
}
@Composable
fun UserIconDefault(
    userinfor: UserInfor,
    size: Int,
    onClick: ()->Unit,
    modifier: Modifier = Modifier,
) {
    var name = (if (userinfor.firstname.length <= 5) userinfor.firstname else userinfor.firstname[0]).toString()
    Box (
        modifier = modifier
            .size(size.dp)
            .clip(shape = CircleShape)
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .border(.5.dp, MaterialTheme.colorScheme.secondary, shape = CircleShape)
    ){
      Text(
          text = name,
          fontSize = (
                  if(name.length == 1) size / 2
                  else size/name.length + name.length
                  ).sp,
          modifier = Modifier.align(Alignment.Center),
          textAlign = TextAlign.Center,
          color = MaterialTheme.colorScheme.onSecondaryContainer
      )
    }
}