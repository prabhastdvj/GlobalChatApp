package com.example.globalchatapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.globalchatapp.model.ChatRoom

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatRoomsScreen(navController: NavController) {
    val chatRooms = remember {
        mutableStateListOf(
            ChatRoom("1", "General Chat", "Welcome to the general chat!"),
            ChatRoom("2", "Tech Talk", "Latest tech discussions"),
            ChatRoom("3", "Random", "Random conversations")
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Chat Rooms") },
                actions = {
                    IconButton(onClick = { /* Handle new chat room */ }) {
                        Icon(Icons.Default.Add, contentDescription = "Add Chat Room")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            items(chatRooms) { room ->
                ChatRoomItem(room) {
                    navController.navigate("chat/${room.id}")
                }
            }
        }
    }
}

@Composable
fun ChatRoomItem(room: ChatRoom, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable(onClick = onClick)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = room.name,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = room.lastMessage,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
} 