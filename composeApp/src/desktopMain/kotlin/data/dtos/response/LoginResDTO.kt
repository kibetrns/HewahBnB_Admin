package data.dtos.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResDTOData(
    @SerialName("objectId")
    val objectId: String,
    val email: String,
    val fullName: String,
    val mpesaNumber: Long,
    val username: String,
    val userType: String,
    val createdAt: String,
    val updatedAt: String,
    val roles: Roles,
    val ACL: Map<String, AclPermissions>,
    val sessionToken: String
)

@Serializable
data class Roles(
    @SerialName("__type")
    val type: String,
    val className: String
)

@Serializable
data class AclPermissions(
    val read: Boolean,
    val write: Boolean
)


@Serializable
data class LoginResDTOError(
    @SerialName("code") val code: Int,
    @SerialName("error") val error: String
)

@Serializable
sealed class LoginResDTO {
    @Serializable
    data class Success(val data: LoginResDTOData) : LoginResDTO()

    @Serializable
    data class Error(val data: LoginResDTOError) : LoginResDTO()
}
