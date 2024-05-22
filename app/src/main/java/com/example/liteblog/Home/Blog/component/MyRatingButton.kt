package com.example.liteblog.Home.Blog.component

import UserData
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.liteblog.utils.Model.Rating

@Preview(showBackground = true)
@Composable
fun PreviewMyRatingButton() {
    MyRatingButton()
}
@Composable
fun MyRatingButton(
    modifier: Modifier = Modifier,
    rating: Rating = Rating(),
    onClickUpRate: () -> Unit = {},
    onClickDownRate: () -> Unit = {}
) {
    Row (
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ){
        Icon(
            imageVector = Icons.Default.KeyboardArrowUp,
            contentDescription = null,
            tint =
                if(rating.upRating.contains(UserData.username))MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.clickable {
                onClickUpRate()
            }
        )
        Text(
            text = "${rating.upRating.size - rating.downRating.size}",
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.SemiBold
        )
        Icon(
            imageVector = Icons.Default.KeyboardArrowDown,
            contentDescription = null,
            tint =
                if(rating.downRating.contains(UserData.username))MaterialTheme.colorScheme.error
                else MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.clickable {
                onClickDownRate()
            }
        )
//        Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
    }
}
