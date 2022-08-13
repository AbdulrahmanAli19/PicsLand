package abdulrahman.ali19.intrazero.domain.repository

import abdulrahman.ali19.intrazero.data.remote.dto.PageDto
import abdulrahman.ali19.intrazero.utils.NetworkResponse

interface Repository {

    suspend fun getAllPages(): NetworkResponse<List<PageDto>>

    suspend fun getPagesWithPageAndLimit(
        limit: String = "10",
        pageNo: String = "1"
    ): NetworkResponse<List<PageDto>>
}