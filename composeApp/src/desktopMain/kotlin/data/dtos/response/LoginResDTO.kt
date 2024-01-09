package data.dtos.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResDTO(
    @SerialName("objectId")
    val objectId: String,
    @SerialName("fullName")
    val fullName: String,
    @SerialName("mpesaNumber")
    val mpesaNumber: Long,
    @SerialName("email")
    val email: String,
    @SerialName("username")
    val username: String,
    @SerialName("createdAt")
    val createdAt: String,
    @SerialName("updatedAt")
    val updatedAt: String,
    @SerialName("ACL")
    val acl: Map<String, Map<String, Boolean>>,  // ACL is a map with dynamic keys
    @SerialName("sessionToken")
    val sessionToken: String
)