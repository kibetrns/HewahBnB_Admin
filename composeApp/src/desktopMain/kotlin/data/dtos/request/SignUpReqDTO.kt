package data.dtos.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignUpReqDTO(
    @SerialName("email")
    val email: String,
    @SerialName("fullName")
    val fullName: String,
    @SerialName("mpesaNumber")
    val mpesaNumber: Long,
    @SerialName("password")
    val password: String,
    @SerialName("username")
    val userName: String
)