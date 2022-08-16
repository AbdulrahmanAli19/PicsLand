package abdulrahman.ali19.intrazero.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys_table")
data class RemoteKeys (
    @PrimaryKey val pageId: Int,
    val prevKey: Int?,
    val nextKey: Int?
)