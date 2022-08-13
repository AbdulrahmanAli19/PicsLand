package abdulrahman.ali19.intrazero.data.repository

import abdulrahman.ali19.intrazero.data.remote.PagePicsumApi
import abdulrahman.ali19.intrazero.data.remote.dto.PageDto
import abdulrahman.ali19.intrazero.domain.repository.Repository
import abdulrahman.ali19.intrazero.utils.NetworkResponse
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val api: PagePicsumApi
) : Repository {

    override suspend fun getAllPages(): NetworkResponse<List<PageDto>> {
        try {
            val response = api.getAllPages()
            return if (response.isSuccessful)
                NetworkResponse.SuccessResponse(data = response.body() ?: emptyList())
            else
                NetworkResponse.FailureResponse(
                    errorString = response.message() ?: "Unknown Error"
                )

        } catch (ex: HttpException) {
            return NetworkResponse.FailureResponse(
                errorString = ex.localizedMessage ?: "Unknown HttpException"
            )
        } catch (ex: IOException) {
            return NetworkResponse.FailureResponse(
                errorString = ex.message ?: "Unknown IOException"
            )
        }

    }

    override suspend fun getPagesWithPageAndLimit(
        limit: String,
        pageNo: String
    ): NetworkResponse<List<PageDto>> {

        try {
            val response = api.getPagesWithPageAndLimit(limit, pageNo)
            return if (response.isSuccessful)
                NetworkResponse.SuccessResponse(data = response.body() ?: emptyList())
            else
                NetworkResponse.FailureResponse(
                    errorString = response.message() ?: "Unknown Error"
                )

        } catch (ex: HttpException) {
            return NetworkResponse.FailureResponse(
                errorString = ex.localizedMessage ?: "Unknown HttpException"
            )
        } catch (ex: IOException) {
            return NetworkResponse.FailureResponse(
                errorString = ex.message ?: "Unknown IOException"
            )
        }

    }

}