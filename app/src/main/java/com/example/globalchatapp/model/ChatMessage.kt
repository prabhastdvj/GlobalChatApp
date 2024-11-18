package com.example.globalchatapp.model

data class ChatMessage(
    val id: String,
    val senderId: String,
    val message: String,
    val timestamp: Long,
    val senderName: String
)

data class ChatRoom(
    val id: String,
    val name: String,
    val lastMessage: String = "",
    val timestamp: Long = System.currentTimeMillis()
)

data class User(
    val id: String,
    val name: String,
    val email: String
) 