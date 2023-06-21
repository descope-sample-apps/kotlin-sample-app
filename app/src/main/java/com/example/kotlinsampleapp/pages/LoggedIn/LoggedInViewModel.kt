package com.example.kotlinsampleapp.pages.LoggedIn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinsampleapp.pages.UserRepository

class LoggedInViewModel(private val userRepository: UserRepository) : ViewModel() {
    fun signIn(
        email: String,
        onSignInComplete: () -> Unit,
    ) {
    }
}

class LoggedInViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoggedInViewModel::class.java)) {
            return LoggedInViewModel(UserRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
