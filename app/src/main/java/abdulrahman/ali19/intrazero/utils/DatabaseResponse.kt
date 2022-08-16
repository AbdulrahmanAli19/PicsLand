package abdulrahman.ali19.intrazero.utils

sealed class DatabaseResponse<out R> {
    data class Success<out T>(val data: T) : DatabaseResponse<T>()
    data class Failure(val errorMsg: String) : DatabaseResponse<Nothing>()
}
