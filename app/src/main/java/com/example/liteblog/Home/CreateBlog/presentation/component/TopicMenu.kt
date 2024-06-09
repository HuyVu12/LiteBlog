package com.example.liteblog.Home.CreateBlog.presentation.component

import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

var topics_data = listOf(
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
    ViewMode.Public.mode,
    ViewMode.Private.mode,
//    ViewMode.Fan.mode,
//    ViewMode.Trash.mode,
//    ViewMode.Hide.mode
)

sealed class ViewMode(val mode: String) {
    object Public: ViewMode("Công khai")
    object Private: ViewMode("Riêng tư")
    object Hide: ViewMode("Ẩn")
    object Fan: ViewMode("Người theo dõi")
    object Trash: ViewMode("trash")
}

sealed class TopicMode(val mode: String) {
    object Public: ViewMode("Tự do")
    object Live: ViewMode("Đời sống")
    object Act: ViewMode("Nghệ thuật")
    object Literature: ViewMode("Văn học")
    object Knowledge: ViewMode("Kiến thức")
    object Beauty: ViewMode("Làm đẹp")
    object Fashion: ViewMode("Thời trang")
    object Interior: ViewMode("Nội thất")
    object Tourism: ViewMode("Du lịch")
    object Book: ViewMode("Sách")
    object Sport: ViewMode("Thể thao")
    object Review: ViewMode("Review")
    object Seld: ViewMode("Tin khuyến mãi")
}

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
