package com.example.liteblog.Home.CreateBlog.presentation

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.liteblog.utils.Data.Database.FB_Blog
import com.example.liteblog.utils.Model.Blog
import com.example.liteblog.utils.Storage.FireStorage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CreateBlogViewModel: ViewModel() {
    private val _state = MutableStateFlow(CreateBlogState())
    val state = _state.asStateFlow()
    var isShowTopicMenu by mutableStateOf(false)
    var isShowViewModeMenu by mutableStateOf(false)
    var title by mutableStateOf("")
    var description by mutableStateOf("")
    var topic by mutableStateOf("Tự do")
    var viewMode by mutableStateOf("Công khai")
    var listImages by mutableStateOf(
        mutableListOf<Uri?>()
    )

    fun addBlog() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true,
                    isSaved = false
                )
            }
            var imageList = mutableListOf<String>()
            for (image in listImages) {
                imageList.add(FireStorage.getUrimage(FireStorage.uploadImage(image!!)).toString())
            }
            FB_Blog.create(
                Blog(
                    title = title,
                    description = description,
                    imageList = imageList,
                    hided = false,
                    topic = topic,
                    viewMode = viewMode
                )
            )
            _state.update {
                it.copy(
                    isLoading = false,
                    isSaved = true
                )
            }
        }
    }
}