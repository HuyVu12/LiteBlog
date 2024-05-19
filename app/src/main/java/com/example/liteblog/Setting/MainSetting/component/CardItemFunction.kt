package com.example.liteblog.Setting.MainSetting.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.liteblog.utils.Component.MSpacer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardItemFunction(
    onClick: () -> Unit,
    textValue: String,
    icon: ImageVector
) {
    Card(
        onClick = { onClick()},
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        ),
        modifier = Modifier.padding(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        )
    ) {
        Column (
            modifier = Modifier.padding(15.dp)
        ){
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(25.dp)
            )
            MSpacer(5)
            Text(
                text = textValue,
                fontSize = 16.sp
            )
        }
    }
}