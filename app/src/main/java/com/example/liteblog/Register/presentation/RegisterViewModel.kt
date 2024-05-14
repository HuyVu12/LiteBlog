package com.example.liteblog.Register.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.liteblog.utils.Data.FBcreateUser
import com.example.liteblog.utils.Data.FBgetUserInforByUsername
import com.example.liteblog.utils.Model.Birthday
import com.example.liteblog.utils.Model.User
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Date

class RegisterViewModel: ViewModel() {
    private val _state = MutableStateFlow(RegisterViewState())
    val state = _state.asStateFlow()
    var numScreen by mutableStateOf(1)
    var offSetX by mutableStateOf(600)

    var firstname by mutableStateOf("")
    var lastname by mutableStateOf("")
    var username by mutableStateOf("")
    var password by mutableStateOf("")

    var isShowPassword by mutableStateOf(false)

    var birthday by mutableStateOf(Birthday())

    fun onSelectBirthday(selectedDateMillis: Long?) {
        if(selectedDateMillis != null) {
            val date = Date(selectedDateMillis)

            val day = date.date
            val month = date.month + 1
            val year = date.year + 1900
            birthday = Birthday(day = day, month = month, year = year)
        }
    }
    fun checkUserName() {
        if(username.length > 0 && _state.value.isLoading == false){
            viewModelScope.launch {
                _state.update {
                    it.copy(isLoading = true)
                }
                if(FBgetUserInforByUsername(username = username) == null) {
                    nextPage()
                }
                else {

                }
                _state.update {
                    it.copy(isLoading = false)
                }
            }
        }
    }
    fun checkFullName() {
        if(lastname.length > 0 && firstname.length > 0) {
            nextPage()
        }
    }
    fun checkBirthDay() {
        if(birthday.year != 0) {
            nextPage()
        }
    }
    fun checkPassword() {
        if(password.length > 0){
            nextPage()
        }
    }
    fun createUser() {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            FBcreateUser(
                User(
                    username = username,
                    firstname = firstname,
                    lastname = lastname,
                    password = password,
                    birthdate = birthday
                )
            )
            _state.update {
                it.copy(isLoading = false)
            }
        }
    }

    fun nextPage() {
        if(numScreen < 6){
            offSetX = 600
            viewModelScope.launch {
                while (offSetX > 0) {
                    offSetX -= 30
                    delay(10)
                }
                numScreen += 1
                while (offSetX > -600) {
                    offSetX -= 30
                    delay(10)
                }
            }
        }
    }
    fun previousPage() {
        if(numScreen > 1){
            offSetX = -600
            viewModelScope.launch {
                while (offSetX < 0) {
                    offSetX += 30
                    delay(10)
                }
                numScreen -= 1
                while (offSetX < 600) {
                    offSetX += 30
                    delay(10)
                }
            }
        }
    }
}