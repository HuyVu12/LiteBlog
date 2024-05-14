package com.example.liteblog.Home.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val HOME_SCREEN_ROUTE = "home"

data class TabItem(
    val iconDefault: ImageVector = Icons.Outlined.Home,
    val iconSelected: ImageVector = Icons.Default.Home,
    val route: String = HOME_SCREEN_ROUTE
)

@Preview(showBackground = true)
@Composable
fun HS_tabItem(
    tabItem: TabItem = TabItem()
) {
    Tab(
        selected = true,
        onClick = { /*TODO*/ },
        text = { Text(text = "Home")},
        icon = {Icon(imageVector = tabItem.iconDefault, contentDescription = null)},
    )
}

@Preview(showBackground = true)
@Composable
fun HS_TabsBar(
    modifier: Modifier = Modifier,
    tabs: List<TabItem> = listOf(TabItem(),TabItem(),TabItem(),TabItem(),TabItem(),TabItem())
) {
    LazyRow (
        modifier = modifier.fillMaxWidth()
    ){
        items(tabs) {tab ->
            HS_tabItem()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun HS_TopAppBar() {
    Column {
        TopAppBar(
            title = {
                Text(
                    text = "Lite Blog",
                    style = MaterialTheme.typography.headlineLarge
                )
            },
            actions = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Notifications, contentDescription = null)
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.AddCircle, contentDescription = null)
                }
//                IconButton(onClick = { /*TODO*/ }) {
//                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
//                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Favorite, contentDescription = null)
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                actionIconContentColor = MaterialTheme.colorScheme.primary,
                navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary
            )
        )
    }
}