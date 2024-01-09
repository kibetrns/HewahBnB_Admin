package data.dtos.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignUpResDTO(
    @SerialName("createdAt")
    val createdAt: String,
    @SerialName("objectId")
    val objectId: String,
    @SerialName("loggedInUser")
    val sessionToken: String
)