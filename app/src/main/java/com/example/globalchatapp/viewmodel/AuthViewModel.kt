package com.example.globalchatapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.globalchatapp.model.User
import com.example.globalchatapp.service.AuthService
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val authService = AuthService.getInstance()
    
    val isAuthenticated: StateFlow<Boolean> = authService.isAuthenticated
    val currentUser: StateFlow<User?> = authService.currentUser

    fun login(email: String, password: String, onResult: (Result<User>) -> Unit) {
        viewModelScope.launch {
            val result = authService.login(email, password)
            onResult(result)
        }
    }

    fun logout() {
        authService.logout()
    }
} 