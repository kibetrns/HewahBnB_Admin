package data.service

import co.touchlab.kermit.Logger
import data.dtos.request.LoginReqDTO
import data.dtos.request.SignUpReqDTO
import data.dtos.response.LoginResDTO
import data.dtos.response.SignUpResDTO
import data.model.UserType
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.header
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.json.Json
import util.APP_ID
import util.HBAPIEndpoints
import util.MASTER_KEY

class HBAdminServiceImpl(
    private val defaultCSAService: HttpClient
) : HBAdminService {

    override suspend fun signUp(
        fullName: String,
        mpesaNumber: Long,
        email: String,
        password: String,
        userName: String,
        userType: UserType
    ): SignUpResDTO {

        Logger.d(
            tag = "SIGNUP_HBServI",
            messageString = (
                    SignUpReqDTO(
                        email = email,
                        fullName = fullName,
                        mpesaNumber = mpesaNumber,
                        password = password,
                        userName = userName,
                        userType = userType
                    ).toString())
        )


        val response = defaultCSAService.post(HBAPIEndpoints.SignUpUser.url) {
            headers {
                header("X-Parse-Application-Id", APP_ID)
                header("X-Parse-REST-API-Key", MASTER_KEY)
                header("X-Parse-Revocable-Session", "1")
                header("Content-Type", "application/json")
            }
            contentType(ContentType.Application.Json)
            setBody(
                SignUpReqDTO(
                    email = email,
                    fullName = fullName,
                    mpesaNumber = mpesaNumber,
                    password = password,
                    userName = userName,
                    userType = userType
                ),
            )

            Logger.d(
                tag = "SIGNUP_API_REQUEST",
                messageString = (
                        this.body.toString())
            )

        }

        Logger.d(
            tag = "SIGNUP_API_STATUS",
            messageString = (
                    response.status.value.toString())
        )

        val isSuccess = response.status.value == 201

        return if (isSuccess) {
            Logger.d(
                tag = "SIGNUP_API_RES_SUC",
                messageString = (
                        response.toString())
            )

            Logger.d(
                tag = "SIGNUP_API_RES_Headers_SUC",
                messageString = (
                        response.headers.toString())
            )

            SignUpResDTO.Success(
                data = response.body()
            )
        } else {
            Logger.d(
                tag = "SIGNUP_API_RES_Headers_ERR",
                messageString = (
                        response.headers.toString())
            )

            Logger.d(
                tag = "SIGNUP_API_RES_Body_ERR",
                messageString = (
                        SignUpResDTO.Error(
                            data = response.body()
                        ).toString())
            )


            SignUpResDTO.Error(
                data = response.body()
            )



        }
    }

    override suspend fun login(userName: String, password: String): LoginResDTO {

        Logger.d(
            tag = "LOGIN_HBServI",
            messageString = (
                    LoginReqDTO(
                        password = password,
                        userName = userName
                    ).toString())
        )

        val response = defaultCSAService.post(HBAPIEndpoints.LoginUser.url) {
            headers {
                header("X-Parse-Application-Id", APP_ID)
                header("X-Parse-REST-API-Key", MASTER_KEY)
                header("X-Parse-Revocable-Session", "1")
                header("Content-Type", "application/json")
            }
            contentType(ContentType.Application.Json)
            setBody(
                LoginReqDTO(
                    password = password,
                    userName = userName
                ),
            )



            Logger.d(
                tag = "LOGIN_API_REQUEST_BODY",
                messageString = (
                        """
                           ${this.body} 
                           
                        """.trimIndent()
                        )
            )

        }
        Logger.d(
            tag = "LOGIN_API_RESPONSE",
            messageString = (
                    """
                        $response
          
                    """.trimIndent()
                    )
        )

        Logger.d(
            tag = "LOGIN_API_RESPONSE_HEADERS",
            messageString = (
                    """
                      ${response.headers}  
                    """.trimIndent()
                    )
        )

        val isSuccess = response.status.value == 200

        return if (isSuccess) {
            Logger.d(
                tag = "LOGIN_API_RES_Body_SUC",
                messageString = (response.body())
            )

            LoginResDTO.Success(
                data = response.body()
            )

        } else {

            LoginResDTO.Error(
                data = response.body()
            )
        }
    }
}