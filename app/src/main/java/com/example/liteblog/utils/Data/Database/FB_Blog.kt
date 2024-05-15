package com.example.liteblog.utils.Data.Database

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.liteblog.utils.Functions.MyFunction
import com.example.liteblog.utils.Model.Blog
import com.google.android.gms.tasks.Tasks.await
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await

@RequiresApi(Build.VERSION_CODES.O)
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

suspend fun FBGetBlogs() :List<Blog> {
    val docs = Collection.BlogCollection.orderBy("timePost", Query.Direction.DESCENDING).limitToLast(20).get().await()
    val listsBlog = mutableListOf<Blog>()

    for(doc in docs) {
        listsBlog.add(doc.toObject<Blog>())
    }
    return listsBlog
}