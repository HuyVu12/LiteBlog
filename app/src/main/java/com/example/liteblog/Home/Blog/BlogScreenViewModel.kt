package com.example.liteblog.Home.Blog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.liteblog.utils.Data.Database.FB_Blog
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BlogScreenViewModel:ViewModel() {
    private val _state = MutableStateFlow(BlogScreenState())
    val state = _state.asStateFlow()

    init {
        getBlogs()
    }

    fun getBlogs() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoadingBlog = true
                )
            }
            _state.update {
                it.copy(
                    listBlogs = FB_Blog.get()
                )
            }
            _state.update {
                it.copy(isLoadingBlog = false)
            }
        }
    }
}