package com.example.liteblog.utils.Data.Database

import UserData
import android.adservices.topics.Topic
import android.util.Log
import com.example.liteblog.Home.CreateBlog.presentation.component.TopicMode
import com.example.liteblog.Home.CreateBlog.presentation.component.ViewMode
import com.example.liteblog.utils.Functions.MyFunction
import com.example.liteblog.utils.Model.Blog
import com.example.liteblog.utils.Model.Comment
import com.example.liteblog.utils.Model.Rating
import com.example.liteblog.utils.Model.UserInfor
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.toObject
import com.google.firebase.firestore.toObjects
import kotlinx.coroutines.tasks.await

class FB_Blog {
    companion object {
        val collection = Collection.BlogCollection
        enum class Field(val field_name: String) {
            ID("id"),
            UserInfor("userinfor"),
            Title("title"),
            TimePost("timePost"),
            Description("description"),
            ImageList("imageList"),
            Comments("comments"),
            Likes("likes"),
            Reports("reports"),
            Rating("rating"),
        }

        suspend fun create(blog: Blog) {
            try {
                val id = collection.document().id
                val blogCreate = blog
                blogCreate.id = id
                blogCreate.timePost = MyFunction.getCurrentTime()
                blogCreate.userinfor = UserData.userinfor
                collection.document(blogCreate.id!!).set(blog).await()
            } catch (e:Exception) {
            }
        }
        suspend fun get(userInfor: UserInfor? = null, topic: String = "Tất cả") :List<Blog> {
            if(userInfor == null) {
                val docs = collection.orderBy(Field.TimePost.field_name, Query.Direction.DESCENDING).get().await()
                val listsBlog = mutableListOf<Blog>()
                for(doc in docs) {
                    val blog = doc.toObject<Blog>()
                    if (blog.viewMode == ViewMode.Trash.mode
                        || blog.hided
                        || (blog.userinfor!!.username != UserData.username && blog.viewMode!! == ViewMode.Private.mode)
                        || ((topic != "Tất cả" && topic != "Yêu thích") && blog.topic != topic)
                        || ((topic == "Yêu thích") && !blog.likes.contains(UserData.username))
                        || (blog.blockUser?.contains(UserData.username) == true)
                        ) continue
                    listsBlog.add(doc.toObject<Blog>())
                }
                return listsBlog
            }
            else {
                val docs = collection.get().await()
                val listsBlog = mutableListOf<Blog>()
                for (doc in docs) {
                    val blog = doc.toObject<Blog>()
                    if (blog.viewMode == ViewMode.Trash.mode
                        || blog.hided
                        || (blog.userinfor!!.username != UserData.username && blog.viewMode!! == ViewMode.Private.mode)
                        || (blog.blockUser?.contains(UserData.username) == true)
                        || userInfor.username != blog.userinfor!!.username
                    ) continue
                    listsBlog.add(doc.toObject<Blog>())
                }
                return listsBlog
            }

        }
        fun fetch(blog: Blog, onUpdate: (Blog) -> Unit) {
            collection.document(blog.id!!).addSnapshotListener { snapshot, e ->
                if(e != null) {

                }
                if(snapshot != null && snapshot.exists()) {
                    onUpdate (snapshot.toObject<Blog>()!!)
                }
            }
        }
        fun update(blog: Blog) {
            collection.document(blog.id!!).set(blog)
        }
        suspend fun userLike(blog: Blog) {
            var mLikes = blog.likes.toMutableList()
            if(mLikes.contains(UserData.username))mLikes.remove(UserData.username)
            else mLikes.add(UserData.username)
            collection.document(blog.id!!).update(
                Field.Likes.field_name,
                mLikes
            ).await()
        }
        suspend fun userRate(blog: Blog, isUpRate: Boolean) {
            val m_upRating = blog.rating.upRating.toMutableList()
            val m_downRating = blog.rating.downRating.toMutableList()
            if(isUpRate) {
                if(m_upRating.contains(UserData.username)) {
                    m_upRating.remove(UserData.username)
                }
                else {
                    if(m_downRating.contains(UserData.username))m_downRating.remove(UserData.username)
                    if(!m_upRating.contains(UserData.username))m_upRating.add(UserData.username)
                }
            }
            else {
                if(m_downRating.contains(UserData.username)){
                    m_downRating.remove(UserData.username)
                }
                else {
                    if(m_upRating.contains(UserData.username))m_upRating.remove(UserData.username)
                    if(!m_downRating.contains(UserData.username))m_downRating.add(UserData.username)
                }
            }
            collection.document(blog.id!!).update(
                Field.Rating.field_name,
                Rating(upRating = m_upRating, downRating = m_downRating)
            )
        }
        suspend fun moveToGarbage(blog: Blog) {
            collection.document(blog.id!!).update(
                "viewMode", ViewMode.Trash.mode,
                "hided", true
            ).await()
        }
        suspend fun get(idBlog: String): Blog? {
            val doc = collection.document(idBlog).get().await()
            if(doc.exists()) {
                Log.i("HuyVu2", "${doc.toObject<Blog>()}")
                return doc.toObject<Blog>()
            }
            return null
        }
        suspend fun reUpdateBlog() {
            var docs = collection.get().await()
            for (doc in docs) {
                var blog = doc.toObject<Blog>()
                blog.hided = false
//                blog.viewMode = ViewMode.Public.mode
//                blog.topic = TopicMode.Public.mode
                blog.blockUser = emptyList()
                update(blog)
            }
            docs = Collection.UserInforCollection.get().await()
            for (doc in docs) {
                var user = doc.toObject<UserInfor>()
                user.banned = false
                Collection.UserInforCollection.document(user.username).set(user)
            }
        }
        fun removeComment(
            blog: Blog,
            comment: Comment
        ) {
            val mComments = blog.comments.toMutableList()
            for (i in 0..<mComments.size) {
                if(mComments[i] == comment) {
                    mComments.removeAt(i)
                    break
                }
            }
            collection.document(blog.id!!).update("comments", mComments)
        }
        fun blockUser(
            blog: Blog,
            comment: Comment
        ) {
            val data = comment
            val mComments = mutableListOf<Comment>()
            var mBlockUser = blog.blockUser?.toMutableList()
            if(mBlockUser == null) mBlockUser = mutableListOf()
                for (com in blog.comments) {
                    if(com.userinfor!!.username != data.userinfor!!.username) {
                        mComments.add(com)
                    }
                }
            if(!mBlockUser.contains(data.userinfor!!.username)) {
                mBlockUser.add(data.userinfor!!.username)
            }
            collection.document(blog.id!!).update("comments", mComments, "blockUser", mBlockUser)
        }
    }
}