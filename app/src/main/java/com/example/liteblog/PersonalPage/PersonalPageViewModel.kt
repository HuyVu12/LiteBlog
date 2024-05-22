package com.example.liteblog.PersonalPage

import UserData
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.liteblog.utils.Data.Database.Collection
import com.example.liteblog.utils.Data.Database.FB_Blog
import com.example.liteblog.utils.Data.Database.FBupdateAllUserInfor
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

    init {
        getBlogs()
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
                it.copy(isLoading = false)
            }
        }
    }
}