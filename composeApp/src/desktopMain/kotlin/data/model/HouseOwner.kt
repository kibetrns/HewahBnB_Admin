package data.model

import androidx.compose.ui.graphics.painter.Painter

data class HouseOwner(
    val name: String,
    val properties: List<House>,
    val email: String,
    val mobileNumber: String,
    val profilePhoto: Painter //TODO("Change this to type of String")
)
