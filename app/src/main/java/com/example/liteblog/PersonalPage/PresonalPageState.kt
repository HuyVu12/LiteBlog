package com.example.liteblog.PersonalPage

import com.example.liteblog.utils.Model.Blog

data class PresonalPageState (
    var isLoading: Boolean = false,
    var listBlogs: List<Blog> = emptyList(),
    var isLoadData: Boolean = true
)