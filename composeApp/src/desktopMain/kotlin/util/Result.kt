package util

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val message: String? = null, val statusCode: Int? = null) : Result<Nothing>()
    object Loading : Result<Nothing>()

    object Idle : Result<Nothing>()
}