package com.example.liteblog.utils.Data.Database

import UserData
import UserData.username
import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import com.example.liteblog.utils.Data.Database.Collection.UserCollection
import com.example.liteblog.utils.Data.Database.Collection.UserInforCollection
import com.example.liteblog.utils.Model.Blog
import com.example.liteblog.utils.Model.Follow
import com.example.liteblog.utils.Model.User
import com.example.liteblog.utils.Model.UserInfor
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
suspend fun FBgetUserInforByUsername(username: String): UserInfor? {
    try {
        val doc = UserInforCollection.document(username).get().await()
        if(doc.exists()) {
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
        val docUser = UserCollection.document(userInfor.username).set(user).await()
        val docUserInfor = UserInforCollection.document(userInfor.username).set(userInfor).await()
    } catch (e: Exception) {

    }
}
suspend fun FBgetUserByUsernameAndPassword(username: String, password: String): User? {
    try {
        val query = UserCollection
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
suspend fun FBupdateAllUserInfor(userInfor: UserInfor) {
    val docUserInfor = Collection.UserInforCollection.document(userInfor.username).set(userInfor).await()
    val docsFollow = Collection.FollowCollection.get().await()
    for(docFollow in docsFollow) {
        val follow = docFollow.toObject<Follow>()
        val follow1 = follow.followers.toMutableList()
        val follow2 = follow.myFollowers.toMutableList()
        for(i in 0..<follow1.size) {
            if(follow1[i].username == userInfor.username) {
                follow1[i] = userInfor
            }
        }
        for(i in 0..<follow2.size) {
            if(follow2[i].username == userInfor.username) {
                follow2[i] = userInfor
            }
        }
        follow.followers = follow1
        follow.myFollowers = follow2
        Collection.FollowCollection.document(docFollow.id).set(follow).await()
    }
    val docsBlog = Collection.BlogCollection.get().await()
    for(docBlog in docsBlog) {
        var blog = docBlog.toObject<Blog>()
        val mu_comments = blog.comments.toMutableList()
        for (i in 0..< mu_comments.size) {
            if(mu_comments[i].userinfor!!.username == userInfor.username) {
                mu_comments[i].userinfor = userInfor
            }
        }
        if(blog.userinfor!!.username == userInfor.username) {
            blog.userinfor = userInfor
        }
        blog.comments = mu_comments
        Collection.BlogCollection.document(docBlog.id).set(blog).await()
    }
}
suspend fun FBgetAllUserInfor(): List<UserInfor> {
    val docs = Collection.UserInforCollection.get().await()
    val list = mutableListOf<UserInfor>()
    for (doc in docs) {
        val userInfor = doc.toObject<UserInfor>()
        if(userInfor.username == UserData.username) continue
        list.add(userInfor)
    }
    return list
}