package abdulrahman.ali19.intrazero.data.remote

import abdulrahman.ali19.intrazero.data.remote.dto.PageDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PagePicsumApi {

    @GET("list")
    suspend fun getAllPages(): Response<List<PageDto>>

    @GET("list?page={page_no}&limit={limit}")
    suspend fun getPagesWithPageAndLimit(
        @Path("limit") limit: String = "10",
        @Path("page_no") pageNo: String = "1"
    ): Response<List<PageDto>>
}