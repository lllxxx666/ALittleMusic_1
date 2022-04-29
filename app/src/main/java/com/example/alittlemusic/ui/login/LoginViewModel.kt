package com.example.alittlemusic.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import androidx.lifecycle.Transformations

import com.example.alittlemusic.R
import com.example.alittlemusic.data.logic.network.Repository.UserRepository
import com.example.alittlemusic.util.toast

class LoginViewModel : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val refreshLiveData = MutableLiveData<Any?>()

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    var phone: String? = ""
    var email: String? = ""
    var password: String? = ""
    private var login_m = 1

    val loginLiveData = Transformations.switchMap(refreshLiveData){
        UserRepository.login(this.phone!!, this.password!!,login_m)
//        when(login_m) {
//            1 ->  return@switchMap UserRepository.login(this.phone!!, this.password!!)
//            2 -> return@switchMap UserRepository.login(this.email!!, this.password!!)
//            else -> return@switchMap null
//        }

    }
    fun login(phone: String, password: String) {
        // can be launched in a separate asynchronous job
        this.phone = phone
        this.password = password
        refreshLiveData.value = refreshLiveData.value
    }
    fun emailogin(email: String, password: String) {
        this.email = email
        this.password = password
        refreshLiveData.value = refreshLiveData.value
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(phoneError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}