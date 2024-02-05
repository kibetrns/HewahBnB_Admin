package data.dtos.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
data class SignUpResDTOSuccess(
    @SerialName("objectId") val objectId: String,
    @SerialName("createdAt") val createdAt: String,
    @SerialName("sessionToken") val sessionToken: String
)

@Serializable
data class SignUpResDTOError(
    @SerialName("code") val code: Int,
    @SerialName("error") val error: String
)

@Serializable
sealed class SignUpResDTO {
    @Serializable
    data class Success(@SerialName("data") val data: SignUpResDTOSuccess) : SignUpResDTO()

    @Serializable
    data class Error(@SerialName("data") val data: SignUpResDTOError) : SignUpResDTO()
}
