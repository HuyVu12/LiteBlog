package com.example.liteblog.Home.EditBlog

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.liteblog.Home.CreateBlog.presentation.CreateBlogState
import com.example.liteblog.utils.Data.Database.FB_Blog
import com.example.liteblog.utils.Model.Blog
import com.example.liteblog.utils.Storage.FireStorage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EditBlogViewModel : ViewModel() {
    private val _state = MutableStateFlow(EditBlogState())
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
    var blog by mutableStateOf(
        Blog()
    )

    fun initBlog(idBlog: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            blog = FB_Blog.get(idBlog)?:Blog()
            _state.update {
                it.copy(isLoading = true)
            }
            Log.i("HuyVu", "${blog}")
            title = blog.title!!
            description = blog.description!!
            topic = blog.topic?:"Chung"
            viewMode = blog.viewMode!!
            listImages = mutableListOf()

            for (uri in blog.imageList!!) {
                listImages.add(uri.toUri())
            }
            _state.update {
                it.copy(isLoading = false, isLoadData = false)
            }

        }
    }
    fun updateBlog() {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            blog.title = title
            blog.description = description
            blog.topic = topic
            blog.viewMode = viewMode
//            var imageList = mutableListOf<String>()
//            for (image in listImages) {
//                imageList.add(FireStorage.getUrimage(FireStorage.uploadImage(image!!)).toString())
//            }
//            blog.imageList = imageList
            FB_Blog.update(blog = blog)

            _state.update {
                it.copy(isLoading = false)
            }
        }
    }
}