package com.example.liteblog.Login.Component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.liteblog.utils.Component.MSpacer

//@Preview(showBackground = true)
@Composable
fun LSInputComp(
    modifier: Modifier = Modifier,
    usernameInput: String = "",
    onUserInputChange: (String) -> Unit = {},
    onClickIconUserInput: () -> Unit = {},

    passwordInput: String = "",
    onPassInputChange: (String) -> Unit = {},
    onClickIconPassInput: () -> Unit = {},
    isShowPassword: Boolean = false,

    onClickSignin: () -> Unit = {},
) {
    Column (
        modifier = modifier
    ){
        OutlinedTextField(
            value = usernameInput,
            onValueChange = {
                onUserInputChange(it)
            },
            label = { Text(text = "Tên đăng nhập") },
            placeholder = { Text(text = "Tên đăng nhập") },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            trailingIcon = {
                if(usernameInput.length > 0) {
                    IconButton(onClick = {
                        onClickIconUserInput()
                    }) {
                        Icon(imageVector = Icons.Default.Clear, contentDescription = null)
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )
        MSpacer(10)
        OutlinedTextField(
            value = passwordInput,
            onValueChange = {
                onPassInputChange(it)
            },
            label = { Text(text = "Mật khẩu") },
            placeholder = { Text(text = "Mật khẩu") },
            visualTransformation = if(!isShowPassword) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Go
            ),
            trailingIcon = {
                IconButton(onClick = {onClickIconPassInput() }) {
                    if(!isShowPassword)
                        Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = null)
                    else
                        Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = null)
                }
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )
        MSpacer(12)
        Button(
            onClick = {
                onClickSignin()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = "Đăng nhập",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun LSBotButton(
    modifier: Modifier = Modifier,
    onChangeToRegister: ()->Unit = {}
) {
    OutlinedButton(
        onClick = { onChangeToRegister() },
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 30.dp)
            .height(50.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(
            text = "Tạo tài khoản mới",
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}