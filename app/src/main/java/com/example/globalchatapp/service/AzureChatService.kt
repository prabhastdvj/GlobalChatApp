package com.example.globalchatapp.service

import com.azure.android.communication.chat.*
import com.azure.android.communication.common.*
import com.example.globalchatapp.model.ChatMessage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.*

class AzureChatService private constructor() {
    private var chatClient: ChatClient? = null
    private var chatThreadClient: ChatThreadClient? = null
    
    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages: StateFlow<List<ChatMessage>> = _messages

    companion object {
        private const val ENDPOINT = "YOUR_AZURE_ENDPOINT"
        private const val ACCESS_KEY = "YOUR_ACCESS_KEY"
        
        @Volatile
        private var instance: AzureChatService? = null
        
        fun getInstance(): AzureChatService {
            return instance ?: synchronized(this) {
                instance ?: AzureChatService().also { instance = it }
            }
        }
    }

    suspend fun initialize(userId: String, userToken: String) {
        try {
            val credential = CommunicationTokenCredential(userToken)
            chatClient = ChatClient(
                ENDPOINT,
                credential,
                ChatClientBuilder().buildOptions()
            )
        } catch (e: Exception) {
            throw Exception("Failed to initialize chat client: ${e.message}")
        }
    }

    fun subscribeToMessages(threadId: String) {
        chatClient?.let { client ->
            try {
                client.startRealtimeNotifications()
                client.addEventHandler { chatEvent ->
                    when (chatEvent) {
                        is ChatMessageReceivedEvent -> {
                            val azureMessage = chatEvent.chatMessage
                            val message = ChatMessage(
                                id = azureMessage.id,
                                senderId = azureMessage.sender.id,
                                message = azureMessage.content.toString(),
                                timestamp = System.currentTimeMillis(),
                                senderName = azureMessage.senderDisplayName ?: "Unknown"
                            )
                            val currentMessages = _messages.value.toMutableList()
                            currentMessages.add(0, message)
                            _messages.value = currentMessages
                        }
                    }
                }
            } catch (e: Exception) {
                throw Exception("Failed to subscribe to messages: ${e.message}")
            }
        } ?: throw Exception("Chat client not initialized")
    }

    suspend fun sendMessage(threadId: String, message: String) {
        try {
            chatThreadClient = chatClient?.getChatThreadClient(threadId)
            chatThreadClient?.let { client ->
                val options = SendChatMessageOptions()
                options.content = message
                options.type = ChatMessageType.TEXT
                client.sendMessage(options)
            } ?: throw Exception("Chat thread client not initialized")
        } catch (e: Exception) {
            throw Exception("Failed to send message: ${e.message}")
        }
    }

    suspend fun createChatThread(topic: String): String {
        try {
            chatClient?.let { client ->
                val options = CreateChatThreadOptions()
                options.topic = topic
                val result = client.createChatThread(options)
                return result.chatThread.id
            } ?: throw Exception("Chat client not initialized")
        } catch (e: Exception) {
            throw Exception("Failed to create chat thread: ${e.message}")
        }
    }

    fun cleanup() {
        chatClient?.stopRealtimeNotifications()
        chatClient = null
        chatThreadClient = null
    }
} 