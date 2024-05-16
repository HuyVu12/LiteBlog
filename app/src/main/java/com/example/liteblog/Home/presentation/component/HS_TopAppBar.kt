package com.example.liteblog.Home.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.sharp.AddCircle
import androidx.compose.material.icons.sharp.Create
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.liteblog.ROUTE_BLOG
import com.example.liteblog.ROUTE_CREATE_BLOG
import com.example.liteblog.ROUTE_SETTINGS

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun HS_TopAppBar(
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
    navController: NavController = rememberNavController()
) {
    TopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
//            TextButton(
//                onClick = { navController.navigate(ROUTE_BLOG) },
//                modifier = Modifier
//            ) {
                Text(
                    text = "Lite Blog",
                    style = MaterialTheme.typography.headlineLarge,
                )
//            }
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Outlined.Notifications, contentDescription = null)
            }
            IconButton(onClick = { navController.navigate(ROUTE_CREATE_BLOG)}) {
                Icon(imageVector = Icons.Sharp.Create, contentDescription = null)
            }
            IconButton(onClick = {
                navController.navigate(ROUTE_SETTINGS)
            }) {
                Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
            scrolledContainerColor = MaterialTheme.colorScheme.surface,
            actionIconContentColor = MaterialTheme.colorScheme.onSurface,
            navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
    )
}