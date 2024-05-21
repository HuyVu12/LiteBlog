package com.example.liteblog.Labs.BlogPostCreater.data

// In Android Studio, add the following dependency to your build.gradle.kts file:
// implementation("com.google.ai.client.generativeai:generativeai:0.6.0")

// Add the following code to your Kotlin source code
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.core.net.toFile
import androidx.fragment.app.Fragment
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.asTextOrNull
import com.google.ai.client.generativeai.type.content
import java.io.File

class API_BlogPostCreater {
    companion object {
        val model = GenerativeModel(
            modelName = "gemini-1.0-pro-vision-latest",
            apiKey = "AIzaSyDt-s60vxIOloaxubAED7zLTGQF8kcdyA8",
        )
        suspend fun geneate (
            bitmap: Bitmap,
            prompt: String
        ) : String? {
            val response = model.generateContent(
                content() {
                    text(prompt)
                    image(bitmap)
                }
            )
            return response.candidates.first().content.parts.first().asTextOrNull()
        }
    }
}

//val images = listOf(
//    R.drawable.image0,
//).map {
//    BitmapFactory.decodeResource(baseContext.resources, it)
//}
//
//if (images.any { it == null }) {
//    throw RuntimeException("Image(s) not found in resources")
//}

//val response = model.generateContent(
//    content() {
//        text("Write a short, engaging blog post based on this picture. It should include a description of the meal in the photo and talk about my journey meal prepping. ")
//        image(images[0])
//    }
//)
//
//// Get the first text part of the first candidate
//print(response.text)
//// Alternatively
//print(response.candidates.first().content.parts.first().asTextOrNull())