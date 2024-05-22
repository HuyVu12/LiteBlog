package com.example.liteblog.utils.Data.Database

import UserData
import com.example.liteblog.utils.Functions.MyFunction
import com.example.liteblog.utils.Model.Blog
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
        suspend fun get(userInfor: UserInfor? = null) :List<Blog> {
            if(userInfor == null) {
                val docs = collection.orderBy(Field.TimePost.field_name, Query.Direction.DESCENDING).limit(20).get().await()
                val listsBlog = mutableListOf<Blog>()

                for(doc in docs) {
                    listsBlog.add(doc.toObject<Blog>())
                }
                return listsBlog
            }
            else {
                val doc = collection.whereEqualTo(Field.UserInfor.field_name, UserData.userinfor).get().await()
                return doc.toObjects()
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

    }
}