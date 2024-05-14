package com.example.liteblog.Login.presentation

import UserStorage
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.liteblog.R
import com.example.liteblog.utils.Component.MSpacer
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.liteblog.Login.presentation.Component.LSBotButton
import com.example.liteblog.Login.presentation.Component.LSInputComp
import kotlinx.coroutines.runBlocking

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun LoginScreen(
    navController: NavController = rememberNavController()
) {
    Scaffold (
        topBar = {
            TopAppBar(
                navigationIcon = { IconButton(onClick = {  }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }},
                title = { Text(text = "Blog Lite")},
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface,
                    navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
                ),
            )
        }
    ){
        MainLoginScreen(
            modifier = Modifier.padding(it),
            onChangeToRegister = {
                navController.navigate("register")
            },
            onChangeToHome = {
                navController.navigate("home")
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainLoginScreen(
    viewModel: LoginViewModel = viewModel(),
    modifier: Modifier = Modifier,
    onChangeToRegister: () -> Unit = {},
    onChangeToHome:() -> Unit = {}

) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current
    val datastorage = UserStorage(context)

    val usernameSaved by datastorage.getUsername.collectAsState(initial = "no_data")

    LaunchedEffect(key1 = usernameSaved, block = {
        Log.i("huyvu1 -> LoginScreen", usernameSaved!!)
        if(usernameSaved !=  "no_data" && usernameSaved != "") {
            onChangeToHome()
        }
    })

    LaunchedEffect(key1 = viewModel.userData, block = {
        Log.i("huyvu2 -> LoginScreen", "${viewModel.userData}")
        if(viewModel.userData != null){
            datastorage.saveUsername(viewModel.userData!!)
        }
    })

    Box (
        modifier = modifier.fillMaxSize(),
    ){
        if(state.isLoading){
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.align(Alignment.TopCenter),
        ) {
            Image(
                painter = painterResource(id = R.drawable.liteblog_removebg_preview),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 10.dp)
                    .size(110.dp)
            )
            Spacer(modifier = Modifier.height(40.dp))
            LSInputComp(
                modifier = Modifier.padding(horizontal = 20.dp),
                usernameInput = viewModel.usernameInput,
                onUserInputChange = {viewModel.usernameInput = it},
                onClickIconUserInput = {viewModel.usernameInput = ""},

                passwordInput = viewModel.passwordInput,
                onPassInputChange = {viewModel.passwordInput = it},
                onClickIconPassInput = {viewModel.isShowPassword = !viewModel.isShowPassword},
                isShowPassword = viewModel.isShowPassword,

                onClickSignin = {
                    viewModel.signIn()
                },
            )
        }
        LSBotButton(
            modifier = Modifier.align(Alignment.BottomCenter),
            onChangeToRegister = onChangeToRegister
        )
    }
}
