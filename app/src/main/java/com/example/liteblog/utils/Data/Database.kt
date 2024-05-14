package com.example.liteblog.utils.Data

import android.util.Log
import com.example.liteblog.utils.Data.Database.UserInforColl
import com.example.liteblog.utils.Data.Database.UsersColl
import com.example.liteblog.utils.Model.User
import com.example.liteblog.utils.Model.UserInfor
import com.google.firebase.firestore.dataObjects
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

object Database {
    val db = Firebase.firestore
    val UserInforColl by lazy {
        db.collection("user_infors")
    }
    val UsersColl by lazy {
        db.collection("users")
    }
}

suspend fun FBgetUserInforByUsername(username: String): UserInfor? {
    try {
        val doc = UserInforColl.document(username).get().await()
        if(doc.exists()) {
            Log.i("huyvu", "${doc.toObject<UserInfor>()}")
            return doc.toObject<UserInfor>()
        }
        else return null
    } catch (e: Exception) {
        return null
    }
    return null
}

suspend fun FBcreateUser(user: User) {
    val userInfor = UserInfor(
        firstname = user.firstname?:"",
        lastname = user.lastname?:"",
        username = user.username?:""
    )
    try {
        val docUser = UsersColl.document(userInfor.username).set(user).await()
        val docUserInfor = UserInforColl.document(userInfor.username).set(userInfor).await()
    } catch (e: Exception) {

    }
}

suspend fun FBgetUserByUsernameAndPassword(username: String, password: String): User? {
    try {
        val query = UsersColl
            .whereEqualTo("username", username)
            .whereEqualTo("password", password)
        val docs = query.get().await()
        var userData = User()
        Log.i("HuyVu", "${docs.query}")
        if(docs.isEmpty)return null
        else {
            for(doc in docs) {
                userData = doc.toObject<User>()
            }
            return userData
        }
    } catch (e: Exception) {
        return null
    }
}