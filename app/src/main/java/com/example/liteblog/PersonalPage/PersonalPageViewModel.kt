package com.example.liteblog.PersonalPage

import UserData
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.liteblog.Chat.data.FB_Chat
import com.example.liteblog.utils.Data.Database.Collection
import com.example.liteblog.utils.Data.Database.FB_Blog
import com.example.liteblog.utils.Data.Database.FB_Follower
import com.example.liteblog.utils.Data.Database.FBgetUserInforByUsername
import com.example.liteblog.utils.Data.Database.FBupdateAllUserInfor
import com.example.liteblog.utils.Model.User
import com.example.liteblog.utils.Model.UserInfor
import com.example.liteblog.utils.Storage.FireStorage
import com.example.liteblog.utils.Storage.FireStorage.Companion.uploadImage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PersonalPageViewModel: ViewModel() {
    private val _state = MutableStateFlow(PresonalPageState())
    val state = _state.asStateFlow()
    var selectedImageUri: Uri? by mutableStateOf(null)
    var userInfor: UserInfor? by mutableStateOf(null)
    var isFollow by mutableStateOf(false)
    fun saveData() {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            val userInfor = UserData.userinfor
            userInfor.avatar = FireStorage.getUrimage(FireStorage.uploadImage(selectedImageUri!!)).toString()
            Collection.UserInforCollection.document(userInfor.username).set(userInfor)
            FBupdateAllUserInfor(userInfor = userInfor)
            _state.update {
                it.copy(isLoading = false)
            }
        }
    }
    fun initUser(username: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            userInfor = FBgetUserInforByUsername(username)
            initBlog(userInfor!!)
            _state.update {
                it.copy(isLoading = false, isLoadData = false)
            }
        }
    }
    fun initBlog(userInfor: UserInfor) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }
            _state.update {
                it.copy(
                    listBlogs = FB_Blog.get(userInfor = userInfor)
                )
            }
            _state.update {
                it.copy(isLoading = false)
            }
        }
    }
    fun getBlogs() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }
            _state.update {
                it.copy(
                    listBlogs = FB_Blog.get(userInfor = UserData.userinfor)
                )
            }
            _state.update {
                it.copy(isLoading = false, isLoadData = false)
            }
        }
    }

    fun followUser(userInfor: UserInfor) {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            FB_Follower.followUser(userInfor)
            isFollow = !isFollow
            _state.update {
                it.copy(isLoading = false)
            }
        }
    }
    fun unFollowUser(userInfor: UserInfor) {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            FB_Follower.unFollowUser(userInfor)
            isFollow = !isFollow
            _state.update {
                it.copy(isLoading = false)
            }
        }
    }
    fun chatUser(userInfor: UserInfor) {
        viewModelScope.launch {
            FB_Chat.create(user1 = userInfor, user2 = UserData.userinfor)
        }
    }

}