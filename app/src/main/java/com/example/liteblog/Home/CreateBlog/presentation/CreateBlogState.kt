package com.example.liteblog.Home.CreateBlog.presentation

data class CreateBlogState (
    var isLoading: Boolean = false,
    var isSaved: Boolean = false,
    var isSaving: Boolean = false
)