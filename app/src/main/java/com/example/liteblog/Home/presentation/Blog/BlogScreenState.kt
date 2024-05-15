package com.example.liteblog.Home.presentation.Blog

import com.example.liteblog.utils.Model.Blog

data class BlogScreenState(
    val isLoading: Boolean = false,
    var listBlogs: List<Blog> = emptyList(),
    val isLoadingBlog: Boolean = false
)
