package data.model

import androidx.compose.ui.graphics.painter.Painter


/*
  TODO(
   Check if I would have to change this to User,
    or how I could merge it HouseOwner. To decide once I finalise on Authorization and Roles)
    */
data class Customer(
    val fullName: String,
    val mpesaNumber: Long,
    val email: String,
    val password: String,
    val username: String,
    val profilePhoto: Painter      //TODO("Change this to type of String")
)