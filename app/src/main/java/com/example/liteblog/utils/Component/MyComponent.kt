package com.example.liteblog.utils.Component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun MSpacer(
    height: Int = 0,
    width: Int = 0
) {
    Spacer(modifier = Modifier.height(height.dp).width(width.dp))
}
@Composable
fun InputPassword(
    input1Value: String = "",
    onClickIcon1:() ->Unit = {},
    onInput1Change: (String) -> Unit = {},
    label1Text: String = "",
    placeholder1: String = "",
    isShowPassword: Boolean = false
) {
    Row (
        modifier = Modifier.fillMaxWidth()
    ){
        OutlinedTextField(
            value = input1Value,
            onValueChange = {
                onInput1Change(it)
            },
            label = { Text(text = label1Text) },
            placeholder = { Text(text = placeholder1) },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            trailingIcon = {
                IconButton(onClick = {
                    onClickIcon1()
                }) {
                    if(isShowPassword) Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = null)
                    else Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = null)
                }
            },
            visualTransformation = if(isShowPassword) VisualTransformation.None else PasswordVisualTransformation(),
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(12.dp)
        )

    }
}
