package com.example.liteblog.Labs.VertexApi.data

import com.google.ai.client.generativeai.GenerativeModel

class AI_Gemini {
    companion object {
        val apiKey = "AIzaSyDt-s60vxIOloaxubAED7zLTGQF8kcdyA8"

        val generativeModel = GenerativeModel(
            modelName = "gemini-1.5-flash-latest",
            apiKey = apiKey
        )
        suspend fun generateFromPromt(
            prompt: String,
            onGenerate: (String) -> Unit
        ) {
            val response = generativeModel.generateContentStream(prompt).collect {
                onGenerate(it.text?:"")
            }
        }
    }
}