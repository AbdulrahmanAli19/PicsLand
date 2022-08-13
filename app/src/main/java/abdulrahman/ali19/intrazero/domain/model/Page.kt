package abdulrahman.ali19.intrazero.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "page_table")
data class Page(
    @PrimaryKey val id: Int,
    val author: String,
    val pageUrl: String,
    val imageUrl: String
)
