package com.example.liteblog.utils.Data.Database

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

object Collection {
    val db = Firebase.firestore

    val UserInforCollection by lazy {
        db.collection("user_infors")
    }
    val UserCollection by lazy {
        db.collection("users")
    }
    val BlogCollection by lazy {
        db.collection("blogs")
    }
    val FollowCollection by lazy {
        db.collection("follows")
    }
    val ChatStorageCollection by lazy {
        db.collection("chat_storages")
    }
    val ChatCollection by lazy {
        db.collection("chat")
    }
}
