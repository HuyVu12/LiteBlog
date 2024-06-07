package com.example.liteblog.Home.CreateBlog.presentation.component

import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

var topics_data = listOf(
    "Chung",
    "Đời sống",
    "Kiến thức",
    "Làm đẹp",
    "Sức khỏe",
    "Thời trang",
    "Nội thất",
    "Du lịch",
    "Sách",
    "Thể thao",
    "Review",
    "Tin khuyến mãi",
)
@Composable
fun TopicMenu(
    expanded: Boolean = false,
    onDismissRequest: () -> Unit,
    onSelectItem: (String) -> Unit
) {
    DropdownMenu(expanded = expanded, onDismissRequest = { onDismissRequest() }, modifier = Modifier.heightIn(0.dp, 300.dp)) {
        for (item in topics_data) {
            DropdownMenuItem(text = { Text(text = item) }, onClick = { onSelectItem(item) })
        }
    }
}

var view_mode_data = listOf(
    "Công khai",
    "Riêng tư",
    "Người theo dõi"
)
@Composable
fun ViewModeMenu(
    expanded: Boolean = false,
    onDismissRequest: () -> Unit,
    onSelectItem: (String) -> Unit
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { onDismissRequest() },
    ) {
        for (item in view_mode_data) {
            DropdownMenuItem(text = { Text(text = item) }, onClick = { onSelectItem(item) })
        }
    }
}
