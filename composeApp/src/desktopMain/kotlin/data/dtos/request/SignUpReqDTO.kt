package data.dtos.request

import data.model.UserType
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
    val userName: String,
    @SerialName("userType")
    val userType: UserType,
)


