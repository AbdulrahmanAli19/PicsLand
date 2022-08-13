package abdulrahman.ali19.intrazero.data.remote.dto

import abdulrahman.ali19.intrazero.domain.model.Page
import com.google.gson.annotations.SerializedName

data class PageDto(
    val author: String,
    @SerializedName("download_url") val imageUrl: String,
    val height: Int,
    val id: String,
    @SerializedName("url") val pageUrl: String,
    val width: Int
) {
    fun toPage() = Page(
        id = id.toInt(),
        imageUrl = imageUrl,
        pageUrl = pageUrl,
        author = author
    )
}


