package com.example.liteblog.Home.Blog.component

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.liteblog.Home.CreateBlog.presentation.component.TopicMode
import com.example.liteblog.Home.CreateBlog.presentation.component.view_mode_data

var topics_data_select = listOf(
    "Tất cả",
    "Yêu thích",
    TopicMode.Public.mode,
    TopicMode.Live.mode,
    TopicMode.Knowledge.mode,
    TopicMode.Act.mode,
    TopicMode.Literature.mode,
    TopicMode.Beauty.mode,
    TopicMode.Fashion.mode,
    TopicMode.Interior.mode,
    TopicMode.Tourism.mode,
    TopicMode.Book.mode,
    TopicMode.Sport.mode,
    TopicMode.Review.mode,
    TopicMode.Seld.mode,
)
@Composable
fun MenuTopicSelect(
    expanded: Boolean = false,
    onDismissRequest: () -> Unit,
    onSelectItem: (String) -> Unit
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { onDismissRequest() },
        modifier = Modifier.height(300.dp),
    ) {
        for (item in topics_data_select) {
            DropdownMenuItem(text = { Text(text = item) }, onClick = { onSelectItem(item) })
        }
    }
}
