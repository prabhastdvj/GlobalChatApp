# Global Chat App

A real-time chat application built with Android Jetpack Compose and Azure Communication Services. This app demonstrates modern Android development practices with a focus on real-time communication.

## Features

- Real-time messaging using Azure Communication Services
- User authentication system
- Multiple chat rooms support
- Material Design 3 UI components
- Dark/Light theme support
- Kotlin Coroutines for asynchronous operations
- MVVM architecture
- State management with StateFlow
- Dependency injection pattern
- Error handling and loading states

## Prerequisites

Before running the application, you need:

1. Android Studio Hedgehog (2023.1.1) or newer
2. JDK 11 or newer
3. An Azure account with Azure Communication Services enabled
4. Azure Communication Services endpoint and access key

## Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/prabhastdvj/GlobalChatApp.git
   ```

2. Open the project in Android Studio.

3. Create a `local.properties` file in the root project directory (if it doesn't exist) and add your Azure credentials:
   ```properties
   AZURE_ENDPOINT=your_azure_endpoint
   AZURE_ACCESS_KEY=your_azure_access_key
   ```

4. Update the `AzureChatService.kt` file with your Azure credentials:
   ```kotlin
   companion object {
       private const val ENDPOINT = "YOUR_AZURE_ENDPOINT"
       private const val ACCESS_KEY = "YOUR_ACCESS_KEY"
   }
   ```

## Security Notes

⚠️ **IMPORTANT:**
- Never commit your Azure credentials to version control.
- Always use secure methods to store sensitive information.
- The current implementation uses a placeholder authentication system.
- In production, implement proper security measures.

## Architecture

The app follows the MVVM (Model-View-ViewModel) architecture:
```
app/
├── model/
│ └── Data classes for the app
├── service/
│ ├── AzureChatService.kt
│ └── AuthService.kt
├── ui/
│ ├── screens/
│ │ ├── LoginScreen.kt
│ │ ├── ChatRoomsScreen.kt
│ │ └── ChatScreen.kt
│ └── theme/
└── viewmodel/
├── AuthViewModel.kt
└── ChatViewModel.kt
```
## Azure Communication Services Setup

1. Create an Azure Communication Services resource in the Azure Portal.
2. Get your endpoint URL and access key.
3. Store these securely (see Setup section).
4. Configure CORS if needed in the Azure Portal.

## Running the App

1. Open in Android Studio.
2. Add your Azure credentials as described in the Setup section.
3. Run on an emulator or physical device.
4. Use test credentials to log in:
   - Email: any email format
   - Password: any password (demo mode)

## Production Deployment

Before deploying to production:

1. Implement proper authentication.
2. Secure all API endpoints.
3. Add proper error handling.
4. Implement message persistence.
5. Add user management.
6. Set up proper Azure resource scaling.

## Contributing

1. Fork the repository.
2. Create a feature branch.
3. Commit your changes.
4. Push to the branch.
5. Create a Pull Request.

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Acknowledgments

- Azure Communication Services
- Jetpack Compose
- Material Design 3
- Android Jetpack Libraries

## Contact

email - prabhastdvj777@gmail.com
Project Link: [GlobalChatApp](https://github.com/prabhastdvj/GlobalChatApp)

## Disclaimer

This is a demonstration project. For production use:
- Implement proper security measures.
- Add proper error handling.
- Set up proper user authentication.
- Secure all sensitive information.
- Follow Azure best practices.
