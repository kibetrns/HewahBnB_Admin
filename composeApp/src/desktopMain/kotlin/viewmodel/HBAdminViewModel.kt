package viewmodel


import data.dtos.request.LoginReqDTO
import data.dtos.request.SignUpReqDTO
import data.dtos.response.LoginResDTO
import data.dtos.response.SignUpResDTO
import data.repository.HBAdminRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope
import org.slf4j.LoggerFactory
import util.Result

class HBAdminViewModel(
    private val repository: HBAdminRepository
): ViewModel() {

    val log = LoggerFactory.getLogger(HBAdminViewModel::class.java)


    var passwordVisible = MutableStateFlow(false)
    var isLoading = MutableStateFlow(false)


    val password = MutableStateFlow("")
    val email = MutableStateFlow("")
    val fullName = MutableStateFlow("")
    val mpesaNumber = MutableStateFlow(254711223344)
    val confirmedPassword = MutableStateFlow("")

    val errorMessage = MutableStateFlow("")


    val isUserCreated = MutableStateFlow(false)


    private val _createdUser = MutableStateFlow<Result<SignUpResDTO>>(Result.Idle)
    val createdUser: StateFlow<Result<SignUpResDTO>> = _createdUser

    private val _loggedInUser = MutableStateFlow<Result<LoginResDTO>>(Result.Idle)
    val loggedInUser: StateFlow<Result<LoginResDTO>> = _loggedInUser


    suspend fun signUpUser() {
        _createdUser.value = Result.Loading
        isUserCreated.value = false

        try {
            val createdUser = viewModelScope.async(Dispatchers.IO) {
                log.debug("SIGNUP_VM", SignUpReqDTO(
                    email = email.value,
                    fullName = fullName.value,
                    mpesaNumber = mpesaNumber.value,
                    password = password.value,
                    userName = email.value
                ).toString())

                repository.signUp(
                    fullName = fullName.value,
                    mpesaNumber = mpesaNumber.value,
                    email = email.value,
                    password = password.value,
                    userName = email.value
                )
            }.await()

            if (createdUser != null) {
                _createdUser.value = Result.Success(data = createdUser)
                isUserCreated.value = true

                log.debug("SIGNUP_VM", _createdUser.value.toString())
            } else {
                Result.Error(message = "Something went wrong. User not created")
                errorMessage.value = "Something went wrong. User not created"

                log.debug("SIGNUP_VM", _createdUser.value.toString())
            }
        } catch (e: Exception) {
            // Handle exceptions if any
            log.debug("SIGNUP_VM", "Exception: ${e.message}")
            errorMessage.value = "Something went wrong. User not created: ${e.message}"
        }
    }


    suspend fun loginUser() {
        try {
            val loggedInUser = viewModelScope.async(Dispatchers.IO) {
                log.debug("LOGIN_VM", LoginReqDTO(
                    password = password.value,
                    userName = email.value
                ).toString())

                repository.login(
                    userName = email.value,
                    password = password.value
                )
            }.await()

            if (loggedInUser != null) {
                _loggedInUser.value = Result.Success(data = loggedInUser)

                log.debug("LOGIN_VM", _loggedInUser.value.toString())
            } else {
                Result.Error(message = "Something went wrong. Unable to Login")
                errorMessage.value = "Something went wrong. Unable to Login"

                log.debug("LOGIN_VM", _loggedInUser.value.toString())
            }
        } catch (e: Exception) {
            // Handle exceptions if any
            log.debug("LOGIN_VM", "Exception: ${e.message}")
            errorMessage.value = "Something went wrong. Unable to Login: ${e.message}"
        }
    }
}