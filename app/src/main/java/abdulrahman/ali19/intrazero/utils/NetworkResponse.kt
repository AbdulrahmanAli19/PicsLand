package abdulrahman.ali19.intrazero.utils

sealed class NetworkResponse<out R> {
    data class SuccessResponse<out T>(val data: T) : NetworkResponse<T>()
    data class FailureResponse(val errorString: String) : NetworkResponse<Nothing>()
}

sealed class NetworkResult<out R> {
    object Loading : NetworkResult<Nothing>()
    object EmptyResult : NetworkResult<Nothing>()
    data class Error(val errorString: String) : NetworkResult<Nothing>()
    data class Success<T>(val data: T) : NetworkResult<T>()
}