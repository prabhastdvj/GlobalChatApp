package com.example.globalchatapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.globalchatapp.model.ChatMessage
import com.example.globalchatapp.service.AzureChatService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {
    private val chatService = AzureChatService.getInstance()
    
    private val _uiState = MutableStateFlow<ChatUiState>(ChatUiState.Loading)
    val uiState: StateFlow<ChatUiState> = _uiState
    
    val messages: StateFlow<List<ChatMessage>> = chatService.messages

    fun joinChatRoom(roomId: String) {
        viewModelScope.launch {
            try {
                chatService.subscribeToMessages(roomId)
                _uiState.value = ChatUiState.Success
            } catch (e: Exception) {
                _uiState.value = ChatUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun sendMessage(roomId: String, message: String) {
        viewModelScope.launch {
            try {
                chatService.sendMessage(roomId, message)
            } catch (e: Exception) {
                _uiState.value = ChatUiState.Error(e.message ?: "Failed to send message")
            }
        }
    }
}

sealed class ChatUiState {
    object Loading : ChatUiState()
    object Success : ChatUiState()
    data class Error(val message: String) : ChatUiState()
} 