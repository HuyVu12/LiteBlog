package com.example.liteblog.utils.Data.Database

import UserData
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.liteblog.utils.Functions.MyFunction
import com.example.liteblog.utils.Model.Blog
import com.example.liteblog.utils.Model.UserInfor
import com.google.android.gms.tasks.Tasks.await
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.toObject
import com.google.firebase.firestore.toObjects
import kotlinx.coroutines.tasks.await

suspend fun FBcreateBlog(blog: Blog) {
    try {
        val id = Collection.BlogCollection.document().id
        var blogAdd = blog
        blogAdd.id = id
        blogAdd.timePost = MyFunction.getCurrentTime()
        blogAdd.userinfor = UserData.userinfor
        Collection.BlogCollection.document(blogAdd.id!!).set(blog).await()
    } catch (e:Exception) {

    }
}
suspend fun FBGetBlogs(
    userInfor: UserInfor? = null
) :List<Blog> {
    if(userInfor == null) {
        val docs = Collection.BlogCollection.orderBy("timePost", Query.Direction.DESCENDING).limit(20).get().await()
        val listsBlog = mutableListOf<Blog>()

        for(doc in docs) {
            listsBlog.add(doc.toObject<Blog>())
        }
        return listsBlog
    }
    else {
        val doc = Collection.BlogCollection.whereEqualTo("userinfor", UserData.userinfor).get().await()
        return doc.toObjects()
    }

}
fun FBfetchAutoUpdateBlog(blog: Blog, onUpdadte : (Blog) -> Unit) {
    val docs = Collection.BlogCollection.document(blog.id!!)
    docs.addSnapshotListener { snapshot, e ->
        if(e != null) {

        }
        if(snapshot != null && snapshot.exists()) {
            onUpdadte (snapshot.toObject<Blog>()!!)
        }
    }
}
suspend fun FBChangeLikeBlog(blog: Blog) {
    var mu_likes = blog.likes.toMutableList()
    var type = 0
    for(i in 0..<mu_likes.size) {
        if(mu_likes[i].username == UserData.username) {
            mu_likes.removeAt(i)
            type = 1
            break
        }
    }
    if(type == 0) {
        mu_likes.add(UserData.userinfor)
    }
    blog.likes = mu_likes
    Collection.BlogCollection.document(blog.id!!).set(blog)
}

fun FBupdateBlog(blog: Blog) {
    val docs = Collection.BlogCollection.document(blog.id!!).set(blog)
}

suspend fun FBGetMyBlogs(): List<Blog> {
    val doc = Collection.BlogCollection.whereEqualTo("userinfor", UserData.userinfor).get().await()
    return doc.toObjects()
}