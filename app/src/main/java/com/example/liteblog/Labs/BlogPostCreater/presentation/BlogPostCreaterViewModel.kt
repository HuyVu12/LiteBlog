package com.example.liteblog.Labs.BlogPostCreater.presentation

import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.liteblog.Labs.BlogPostCreater.data.API_BlogPostCreater
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BlogPostCreaterViewModel: ViewModel() {
    private val _state = MutableStateFlow(BlogPostCreaterState())
    val state = _state.asStateFlow()

    var textPrompt by mutableStateOf("")
    var pickImage: Uri? by mutableStateOf(null)
    var textGenerate by mutableStateOf("")
    var bitmap: Bitmap? by mutableStateOf(null)
    fun genarate() {
        if(pickImage != null) {
            textGenerate = ""
            viewModelScope.launch {
                _state.update {
                    it.copy(
                        isLoading = true
                    )
                }
                textGenerate = API_BlogPostCreater.geneate(
                    bitmap = bitmap!!,
                    prompt = textPrompt!!
                ).toString()
                _state.update {
                    it.copy(
                        isLoading = false
                    )
                }
            }
        }
    }
}