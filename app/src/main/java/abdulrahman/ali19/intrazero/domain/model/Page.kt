package abdulrahman.ali19.intrazero.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "page_table")
data class Page(
    @PrimaryKey var id: Float = 0f,
    val author: String = "",
    val pageUrl: String = "",
    val imageUrl: String = "",
    var isAd: Boolean = false
)
