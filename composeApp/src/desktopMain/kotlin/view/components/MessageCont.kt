package view.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun MessageCont(
    message: String,
    messageType: MessageType,
) {
    Row(
        modifier = Modifier
            .background(getBackgroundColor(messageType))
            .animateContentSize()
            .padding(16.dp),
        ) {
        Icon(
            imageVector = getMessageIcon(messageType),
            contentDescription = null,
            tint = Color.White
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(message, color = Color.White)
    }
}

enum class MessageType {
    Info, Warning, Error, Success
}

fun getBackgroundColor(type: MessageType): Color {
    return when (type) {
        MessageType.Info -> Color.Blue
        MessageType.Warning -> Color.Yellow
        MessageType.Error -> Color.Red
        MessageType.Success -> Color.Green
    }
}

fun getMessageIcon(type: MessageType): ImageVector {
    return when (type) {
        MessageType.Info -> Icons.Filled.Info
        MessageType.Warning -> Icons.Filled.Warning
        MessageType.Error -> Icons.Filled.Clear
        MessageType.Success -> Icons.Filled.Done
    }
}