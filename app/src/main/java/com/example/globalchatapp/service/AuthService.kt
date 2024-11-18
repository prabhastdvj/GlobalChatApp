package com.example.globalchatapp.service

import com.example.globalchatapp.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.UUID

class AuthService private constructor() {
    private val _isAuthenticated = MutableStateFlow(false)
    val isAuthenticated: StateFlow<Boolean> = _isAuthenticated

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser

    companion object {
        @Volatile
        private var instance: AuthService? = null
        
        fun getInstance(): AuthService {
            return instance ?: synchronized(this) {
                instance ?: AuthService().also { instance = it }
            }
        }
    }

    suspend fun login(email: String, password: String): Result<User> {
        return try {
            // Here you would typically make an API call to your authentication server
            // For demo purposes, we'll simulate a successful login
            val user = User(
                id = UUID.randomUUID().toString(),
                name = email.substringBefore("@"),
                email = email
            )
            _currentUser.value = user
            _isAuthenticated.value = true
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun logout() {
        _currentUser.value = null
        _isAuthenticated.value = false
    }
} 