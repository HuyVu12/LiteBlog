package com.example.liteblog.Home.Blog.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PhotoImage(uri: String, modifier: Modifier = Modifier) {
    var scale by remember {
        mutableStateOf(1f)
    }
    var rotate by remember {
        mutableStateOf(0f)
    }
    var offset by remember {
        mutableStateOf(Offset.Zero)
    }
    val state = rememberTransformableState { zoom, pan, rotation ->
        scale = (scale * zoom).coerceIn(0.5f, 5f)
        offset += pan * scale
        rotate += rotation
    }
    Image(
        painter = rememberAsyncImagePainter(model = uri),
        contentDescription = null,
        modifier = modifier
            .fillMaxWidth()
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
                translationX = offset.x
                translationY = offset.y
                rotationZ = rotate
            }
            .transformable(state = state)
    )
}
@Composable
fun Scrim(
    modifier: Modifier = Modifier,
    onClose: () ->Unit
) {
    Box(modifier = modifier
        .fillMaxSize()
        .clickable { onClose() }
        .background(color = MaterialTheme.colorScheme.surface.copy(alpha = .7f))
    )
}
@Composable
fun FullImageScreen(
    uriImage: String,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Scrim(onClose = {onDismiss()})
        PhotoImage(uriImage)
    }
}
