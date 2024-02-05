package data.repository

import data.dtos.response.LoginResDTO
import data.dtos.response.SignUpResDTO
import data.model.UserType
import data.service.HBAdminService

class HBAdminRepository(
    private val hbAdminService: HBAdminService
) {

    suspend fun signUp(
        fullName: String,
        mpesaNumber: Long,
        email: String,
        password: String,
        userName: String,
        userType: UserType
    ): SignUpResDTO {
        return hbAdminService.signUp(
            fullName = fullName,
            mpesaNumber = mpesaNumber,
            email = email,
            password = password,
            userName = userName,
            userType = userType
        )
    }

    suspend fun login(
        userName: String,
        password: String
    ): LoginResDTO? {
        return hbAdminService.login(
            userName = userName,
            password = password
        )
    }

}