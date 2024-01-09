package util

import androidx.compose.ui.unit.dp



const val LOGIN_SCREEN = "Login"

const val SIGNUP_SCREEN = "signUp"

const val HOME_SCREEN = "home"

//const val HB_BASE_API_Endpoints = "http://localhost:1337/parse"


const val APP_ID = "myAppId"
const val MASTER_KEY = "main-key-1-!"
const val BASE_SERVER_URL = "  https://hewabnb-server.onrender.com/parse"

sealed class HBAPIEndpoints(val url: String) {

    object SignUpUser : HBAPIEndpoints(url = "$BASE_SERVER_URL/users")

    object LoginUser : HBAPIEndpoints(url = "$BASE_SERVER_URL/Login")
}

sealed class ServiceResult<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : ServiceResult<T>(data)
    class Failure<T>(message: String? = null) : ServiceResult<T>(message = message)
}


enum class SearchAppBarState {
    OPENED,
    CLOSED,
    TRIGGERED
}

enum class TrailingIconState {
    READY_TO_DELETE,
    READY_TO_CLOSE
}

val TOP_APP_BAR_HEIGHT = 56.dp

val TONAL_ELEVATION = 8.dp