package com.example.liteblog.Setting.MainSetting

import UserStorage
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.liteblog.utils.Data.Database.FB_Blog
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingsViewModel: ViewModel() {
    private val _state = MutableStateFlow(SettingsState())
    val state = _state.asStateFlow()

    fun onLogout(
        datastorage: UserStorage
    ) {
        if(_state.value.isLoading == false) {
            viewModelScope.launch {
                _state.update {
                    it.copy(isLoading = true)
                }
                datastorage.saveUsername("no_data")
                _state.update {
                    it.copy(isLoading = false, isExit = true)
                }
            }
        }
    }

    fun Function() {
        viewModelScope.launch {
            FB_Blog.reUpdateBlog()
        }
    }

}