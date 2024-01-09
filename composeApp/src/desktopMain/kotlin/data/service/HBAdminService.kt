package data.service

import data.dtos.response.LoginResDTO
import data.dtos.response.SignUpResDTO

interface HBAdminService {

    suspend fun signUp(
        fullName: String,
        mpesaNumber: Long,
        email: String,
        password: String,
        userName: String
    ): SignUpResDTO?

    suspend fun login(userName: String, password: String): LoginResDTO?
}