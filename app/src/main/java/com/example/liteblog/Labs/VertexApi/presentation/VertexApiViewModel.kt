package com.example.liteblog.Labs.VertexApi.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.liteblog.Labs.VertexApi.data.AI_Gemini
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class VertexApiViewModel :ViewModel(){
    private val _state = MutableStateFlow(VertexApiState())
    val state = _state.asStateFlow()

    var promptText by mutableStateOf("")
    var generateText by mutableStateOf("")

    fun onGenarate() {
        viewModelScope.launch {
            if(promptText.isNotBlank()) {
                _state.update {
                    it.copy(isPromtLoading = true)
                }
                AI_Gemini.generateFromPromt(
                    prompt = promptText,
                    onGenerate = {generateText += it}
                )
                _state.update {
                    it.copy(
                        isPromtLoading = false,
                    )
                }
                generateText += "\n\n"
            }
        }
    }
}