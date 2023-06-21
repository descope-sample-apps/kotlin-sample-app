package com.example.kotlinsampleapp.pages.Welcome


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinsampleapp.pages.UserRepository

class WelcomeViewModel(private val userRepository: UserRepository) : ViewModel() {
//    fun handleContinue(
//        email: String,
//        onNavigateToSignIn: (email: String) -> Unit,
//        onNavigateToSignUp: (email: String) -> Unit,
//    ) {
//        if (userRepository.isKnownUserEmail(email)) {
//            onNavigateToSignIn(email)
//        } else {
//            onNavigateToSignUp(email)
//        }
//    }
}

class WelcomeViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WelcomeViewModel::class.java)) {
            return WelcomeViewModel(UserRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
