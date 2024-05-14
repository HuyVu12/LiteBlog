package com.example.liteblog.Testing

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.liteblog.R
import com.example.liteblog.ui.theme.MyFont

//@Preview(showBackground = true)
@Composable
fun Main() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "HELLO WORLD",
            fontFamily = MyFont.MontserratAlternates
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ImageBlur() {
    Image(
        painter = painterResource(id = R.drawable.liteblog_removebg_preview),
        contentDescription = null,
        modifier = Modifier.scale(3f, 3f),

    )
}