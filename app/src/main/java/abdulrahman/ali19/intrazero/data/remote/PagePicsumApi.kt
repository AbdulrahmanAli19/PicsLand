package abdulrahman.ali19.intrazero.data.remote

import abdulrahman.ali19.intrazero.data.remote.dto.PageDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PagePicsumApi {

    @GET("list")
    suspend fun getAllPages(): Response<List<PageDto>>

    @GET("list")
    suspend fun getPagesWithPageAndLimit(
        @Query("limit") limit: String = "10",
        @Query("page") pageNo: String = "1"
    ): Response<List<PageDto>>
}