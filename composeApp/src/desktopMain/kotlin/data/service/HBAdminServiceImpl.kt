package data.service

import data.dtos.request.LoginReqDTO
import data.dtos.request.SignUpReqDTO
import data.dtos.response.LoginResDTO
import data.dtos.response.SignUpResDTO
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.header
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.json.Json
import org.slf4j.LoggerFactory
import util.APP_ID
import util.HBAPIEndpoints
import util.MASTER_KEY

class HBAdminServiceImpl(
    private val defaultCSAService: HttpClient
) : HBAdminService {

    val log = LoggerFactory.getLogger(HBAdminServiceImpl::class.java)
    override suspend fun signUp(
        fullName: String,
        mpesaNumber: Long,
        email: String,
        password: String,
        userName: String
    ): SignUpResDTO? {
        try {

            log.debug(
                "SIGNUP_HBServI", SignUpReqDTO(
                    email = email,
                    fullName = fullName,
                    mpesaNumber = mpesaNumber,
                    password = password,
                    userName = userName

                ).toString()
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
                        userName = userName
                    ),
                )
                log.debug("SIGNUP_API_REQUEST", this.body.toString())
            }
            log.debug("SIGNUP_API_RESPONSE", response.toString())
            log.debug("SIGNUP_API_RES_Headers", response.headers.toString())
            log.debug("SIGNUP_API_RES_Body", response.body())


            return response.body()

        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

    override suspend fun login(userName: String, password: String): LoginResDTO? {
        try {

            log.debug("LOGIN_HBServI", LoginReqDTO(
                    password = password,
                    userName = userName
                ).toString()
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
                log.debug("LOGIN_API_REQUEST", this.body.toString())
            }
            log.debug("LOGIN_API_RESPONSE", response.toString())
            log.debug("LOGIN_API_RES_Headers", response.headers.toString())
            log.debug("LOGIN_API_RES_Body", response.body())

            return Json.decodeFromString<LoginResDTO>(response.body())

        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

}