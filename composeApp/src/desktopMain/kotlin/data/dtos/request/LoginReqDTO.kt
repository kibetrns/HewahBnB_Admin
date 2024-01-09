package data.dtos.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginReqDTO(
    @SerialName("password")
    val password: String,
    @SerialName("username")
    val userName: String
)
/*
id 'kotlin-kapt'
id 'org.jetbrains.kotlin.plugin.serialization' version '1.9.22'

 */