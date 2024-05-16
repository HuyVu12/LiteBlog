package com.example.liteblog.Home.presentation.CreateBlog.presentation

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.liteblog.utils.Data.Database.FBcreateBlog
import com.example.liteblog.utils.Model.Blog
import com.example.liteblog.utils.Storage.FireStorage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CreateBlogViewModel: ViewModel() {
    private val _state = MutableStateFlow(CreateBlogState())
    val state = _state.asStateFlow()

    var title by mutableStateOf("")
    var description by mutableStateOf("")
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
                imageList.add(FireStorage.uploadImage(image!!))
            }
            FBcreateBlog(
                Blog(
                    title = title,
                    description = description,
                    imageList = imageList
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