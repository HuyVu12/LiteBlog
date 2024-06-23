package com.example.liteblog.Chat.data

import androidx.compose.animation.core.snap
import com.example.liteblog.Chat.model.Chat
import com.example.liteblog.Chat.model.ChatStorage
import com.example.liteblog.Chat.model.Message
import com.example.liteblog.utils.Data.Database.Collection
import com.example.liteblog.utils.Model.UserInfor
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await

object FB_ChatStorage {
    val collection by lazy { Collection.ChatStorageCollection }
    suspend fun get(username: String): ChatStorage {
        val doc = collection.document(username).get().await()
        return doc.toObject<ChatStorage>() ?: ChatStorage()
    }
    suspend fun create(user1: UserInfor, user2: UserInfor) {
        val list1: ChatStorage = (
                collection.document(user1.username).get().await().toObject<ChatStorage>()
                )?:ChatStorage()
        val list2: ChatStorage = (
                collection.document(user2.username).get().await().toObject<ChatStorage>()
                )?:ChatStorage()
        val mlist1 = list1.chats_id.toMutableList()
        val mlist2 = list2.chats_id.toMutableList()
        val id = FB_Chat.create(user1, user2)
        if(id != null) {
            mlist1.add(id)
            mlist2.add(id)
            collection.document(user1.username).set(mlist1).await()
            collection.document(user2.username).set(mlist2).await()
        }
    }
}

object FB_Chat {
    val collection by lazy { Collection.ChatCollection }
    suspend fun create(user1: UserInfor, user2: UserInfor): String? {
        val docs = collection.get().await()
        var ok = false
        for(doc in docs) {
            val chat = doc.toObject<Chat>()
            if(
                (chat.user1.username == user1.username && chat.user2.username == user2.username)
                || (chat.user2.username == user1.username && chat.user1.username == user2.username)
            ) ok = true
        }
        if(ok == false) {
            val id = collection.document().id
            val chat = Chat(
                user1 = user1, user2 = user2, id = id
            )
            collection.document(id).set(chat)
            return id
        }
        else return null
    }
    suspend fun get(username: String) : List<Chat> {
        val lists = mutableListOf<Chat>()
        val docs = collection.get().await()
        for(doc in docs) {
            val chat = doc.toObject<Chat>()
            if(
                (chat.user1.username == username)
                || (chat.user2.username == username)
            ) lists.add(chat)
        }
        return lists
    }
    suspend fun getChatById(chat_id: String): Chat {
        val doc = collection.document(chat_id).get().await()
        return doc.toObject<Chat>()!!
    }
    suspend fun sendMessage(chat: Chat, message: Message) {
        val mMessage = chat.messages.toMutableList()
        mMessage.add(message)
        collection.document(chat.id).update("messages", mMessage)
    }
    fun autoFetchMessage(chat: Chat, onUpdate: (Chat) -> Unit) {
        val doc = collection.document(chat.id).addSnapshotListener {
            snap, e ->
            if(e != null) {

            }
            if(snap != null && snap.exists()) {
                onUpdate(
                    snap.toObject<Chat>()!!
                )
            }
        }
    }
    fun deleteMessage(chat: Chat, message: Message) {
        val mMessage = chat.messages.toMutableList()
        mMessage.remove(message)
        collection.document(chat.id).update("messages", mMessage)
    }
}