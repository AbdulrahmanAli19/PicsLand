package abdulrahman.ali19.intrazero.data.remote

import abdulrahman.ali19.intrazero.data.model.Page
import retrofit2.http.GET

interface PagePicsumApi {

    @GET("/list")
    fun getAllPages(): List<Page>

    @GET("/list?page=1&limit=10")
    fun getTenPages(): List<Page>
}