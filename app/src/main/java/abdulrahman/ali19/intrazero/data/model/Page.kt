package abdulrahman.ali19.intrazero.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "page_table")
data class Page(
    @PrimaryKey val id: Int,
    val author: String,
    val width: Int,
    val height: Int,
    val url: String,
    val imageUrl: String
)
