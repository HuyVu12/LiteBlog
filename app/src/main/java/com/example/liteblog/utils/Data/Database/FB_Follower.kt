package com.example.liteblog.utils.Data.Database

import UserData
import android.util.Log
import com.example.liteblog.utils.Model.Follow
import com.example.liteblog.utils.Model.UserInfor
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await

enum class ResultRequest(message: String) {
    Success("Theo dõi thành công"),

}

suspend fun FBgetFollow(userInfor: UserInfor): Follow {
    val doc = Collection.FollowCollection.document(userInfor.username).get().await()
    if(doc.exists()) {
        return doc.toObject<Follow>()!!
    }
    else return Follow()
}
suspend fun FBfollowUser(userInfor: UserInfor) {
    if (userInfor.username == UserData.username) return

    var follower = FBgetFollow(UserData.userinfor)
    var user_follower = FBgetFollow(userInfor)
    Log.i("HuyVu", "${follower}")
    Log.i("HuyVu", "${user_follower}")
    if(!follower.myFollowers.contains(userInfor)) {
        val mu_my_followers = follower.myFollowers.toMutableList()
        mu_my_followers.add(userInfor)
        follower.myFollowers = mu_my_followers
        Collection.FollowCollection
            .document(UserData.username)
            .set(follower).await()
    }
    if(!user_follower.followers.contains(UserData.userinfor)) {
        val mu_user_followers = user_follower.followers.toMutableList()
        mu_user_followers.add(UserData.userinfor)
        user_follower.followers = mu_user_followers
        Collection.FollowCollection
            .document(userInfor.username)
            .set(user_follower).await()
    }
}