package com.example.liteblog.utils.Component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.google.protobuf.DescriptorProtos.FieldDescriptorProto.Label

@Composable
fun MyTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    labelText: String = "",
    trailingIcon: @Composable () -> Unit = {},
    leadingIcon: @Composable () -> Unit = {},
    placeholder: String,
    keyboardActions: KeyboardActions,
    keyboardOptions: KeyboardOptions,
    enabled: Boolean = true

) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        shape = RoundedCornerShape(12.dp),
        trailingIcon = trailingIcon,
        leadingIcon = leadingIcon,
        placeholder = {
            Text(text = placeholder)
        },
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        enabled = enabled
    )

}