package com.example.liteblog.utils.Storage

import android.net.Uri
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.tasks.await
import java.net.URL
import java.util.UUID
val BLOG_PATH = "images/blogs"
class FireStorage {
    companion object {
        val storage = Firebase.storage
        val storageRef = storage.reference

        suspend fun uploadImage(uri: Uri): String {
            var key = UUID.randomUUID().toString()
            val imageRef = storageRef.child("images/" + key)
            imageRef.putFile(uri).await()
            return key
        }
        suspend fun getUrimage(key: String): Uri? {
            val imageRef = storageRef.child("images/" + key).downloadUrl.await()
            return imageRef
        }
    }
}