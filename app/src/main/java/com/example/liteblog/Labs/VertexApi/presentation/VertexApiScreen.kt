package com.example.liteblog.Labs.VertexApi.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.liteblog.utils.Component.MyBasicTopBar

@Preview(showBackground = true)
@Composable
fun PreviewVertexApi() {
    VertexApiScreen(
        navController = rememberNavController()
    )
}

@Composable
fun VertexApiScreen(
    navController: NavController
) {
    Scaffold(
        topBar = {
            MyBasicTopBar(navController = navController, title = { Text(text = "AI Chat")})
        }
    ) {
        VertexApiMainScreen(modifier = Modifier.padding(it))
    }
}
@Composable
fun VertexApiMainScreen(
    viewModel: VertexApiViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsState()
    Column(
        modifier = modifier.fillMaxSize().padding(horizontal = 10.dp),
    ) {
        if(state.isPromtLoading) {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            item {
                Text(text = viewModel.generateText)
            }
        }

        Row (modifier = Modifier.fillMaxWidth()){
            OutlinedTextField(
                value = viewModel.promptText,
                onValueChange = {viewModel.promptText = it},
                modifier = Modifier.weight(1f),
                label = {
                    Text(text = "Prompt")
                },
                enabled = !state.isPromtLoading
            )
            IconButton(
                onClick = { viewModel.onGenarate() },
                enabled = !state.isPromtLoading
            ) {
                Icon(imageVector = Icons.Default.Send, contentDescription = null)
            }
        }

    }
}