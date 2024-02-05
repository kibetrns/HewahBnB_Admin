package data.service

import data.dtos.response.LoginResDTO
import data.dtos.response.SignUpResDTO
import data.model.UserType

interface HBAdminService {

    suspend fun signUp(
        fullName: String,
        mpesaNumber: Long,
        email: String,
        password: String,
        userName: String,
        userType: UserType
    ): SignUpResDTO

    suspend fun login(userName: String, password: String): LoginResDTO?
}