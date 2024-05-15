package com.example.liteblog.Home.presentation.CreateBlog.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.liteblog.utils.Data.Database.FBcreateBlog
import com.example.liteblog.utils.Model.Blog
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CreateBlogViewModel: ViewModel() {
    private val _state = MutableStateFlow(CreateBlogState())
    val state = _state.asStateFlow()

    var title by mutableStateOf("")
    var description by mutableStateOf("")

    @RequiresApi(Build.VERSION_CODES.O)
    fun addBlog() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true,
                    isSaved = false
                )
            }
            FBcreateBlog(
                Blog(
                    title = title,
                    description = description
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