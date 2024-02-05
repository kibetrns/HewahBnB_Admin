package viewmodel


import co.touchlab.kermit.Logger
import data.dtos.request.LoginReqDTO
import data.dtos.request.SignUpReqDTO
import data.dtos.response.LoginResDTO
import data.dtos.response.SignUpResDTO
import data.model.UserType
import data.repository.HBAdminRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope
import util.Result

class HBAdminViewModel(
    private val repository: HBAdminRepository
): ViewModel() {

    var passwordVisible = MutableStateFlow(false)
    var isLoading = MutableStateFlow(false)


    val password = MutableStateFlow("")
    val email = MutableStateFlow("")
    val fullName = MutableStateFlow("")
    val mpesaNumber = MutableStateFlow(254711223344)
    val confirmedPassword = MutableStateFlow("")
    val userType = MutableStateFlow(UserType.ADMINISTRATOR)

    val errorMessage = MutableStateFlow("")


    val isUserCreated = MutableStateFlow(false)
    val isUserLoggedIn = MutableStateFlow(false)


    private val _createdUser = MutableStateFlow<Result<SignUpResDTO>>(Result.Idle)
    val createdUser: StateFlow<Result<SignUpResDTO>> = _createdUser


    private val _loggedInUser = MutableStateFlow<Result<LoginResDTO>>(Result.Idle)
    val loggedInUser: StateFlow<Result<LoginResDTO>> = _loggedInUser



    suspend fun signUpUser() {
        _createdUser.value = Result.Loading
        isUserCreated.value = false
        try {
            val createdUser = viewModelScope.async(Dispatchers.IO) {

                Logger.d(
                    tag = "SIGNUP_VM",
                    messageString = (
                            SignUpReqDTO(
                                email = email.value,
                                fullName = fullName.value,
                                mpesaNumber = mpesaNumber.value,
                                password = password.value,
                                userName = email.value,
                                userType = userType.value
                            ).toString())
                )

                repository.signUp(
                    fullName = fullName.value,
                    mpesaNumber = mpesaNumber.value,
                    email = email.value,
                    password = password.value,
                    userName = email.value,
                    userType = userType.value
                )
            }.await()

            when(createdUser) {
                is SignUpResDTO.Error -> {

                    _createdUser.value = Result.Error(
                        message = createdUser.data.error,
                        statusCode = createdUser.data.code
                    )

                    Logger.d(
                        tag = "SIGNUP_VM",
                        messageString = (
                                SignUpReqDTO(
                                    email = email.value,
                                    fullName = fullName.value,
                                    mpesaNumber = mpesaNumber.value,
                                    password = password.value,
                                    userName = email.value,
                                    userType = userType.value
                                ).toString())
                    )
                }

                is SignUpResDTO.Success -> {
                    _createdUser.value = Result.Success( data = createdUser)

                    isUserCreated.value = true

                    Logger.d(
                        tag = "SIGNUP_VM",
                        messageString = (
                                SignUpReqDTO(
                                    email = email.value,
                                    fullName = fullName.value,
                                    mpesaNumber = mpesaNumber.value,
                                    password = password.value,
                                    userName = email.value,
                                    userType = userType.value
                                ).toString())
                    )

                    Logger.d(
                        tag = "SIGNUP_VM",
                        messageString = (_createdUser.value.toString())
                    )
                }
            }

        } catch (e: Throwable) {

            _createdUser.value = Result.Error(
                message = e.message,
            )

            Logger.e(
                tag = "SIGNUP_VM",
                messageString = ("Exception: ${e.message}")
            )

            errorMessage.value = "User not created: ${e.message}"
        }
    }


    suspend fun loginUser() {
        try {
            val loggedInUser = viewModelScope.async(Dispatchers.IO) {


                Logger.d(
                    tag = "LOGIN_VM",
                    messageString = (
                            LoginReqDTO(
                                password = password.value,
                                userName = email.value
                            ).toString())
                )

                repository.login(
                    userName = email.value,
                    password = password.value
                )
            }.await()



            when(loggedInUser) {
                is  LoginResDTO.Error -> {

                    _loggedInUser.value = Result.Error(
                        message = loggedInUser.data.error,
                        statusCode = loggedInUser.data.code
                    )

                    Logger.d(
                        tag = "LOGIN_VM_ERROR",
                        messageString = ( loggedInUser.data.toString() )
                    )

                }

                is LoginResDTO.Success -> {

                    Logger.d(
                        tag = "LOGIN_VM",
                        messageString = (
                                LoginReqDTO(
                                    password = password.value,
                                    userName = email.value,
                                ).toString())
                    )

                    Logger.d(
                        tag = "LOGIN_VM_SUCCESS",
                        messageString = (_loggedInUser.value.toString())
                    )



                    _loggedInUser.value = Result.Success(data = loggedInUser)

                    isUserLoggedIn.value = true

                }

                else -> {}
            }
        } catch (e: Throwable) {

            Logger.d(
                tag = "LOGIN_VM_EXCEPTION",
                messageString = (e.message.toString())
            )

            Logger.d(
                tag = "LOGIN_VM_EXCEPTION",
                messageString = (e.stackTraceToString())
            )

            errorMessage.value = "Unable to Login: ${e.message}"
        }
    }
}