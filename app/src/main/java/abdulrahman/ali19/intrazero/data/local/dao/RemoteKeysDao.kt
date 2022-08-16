package abdulrahman.ali19.intrazero.data.local.dao

import abdulrahman.ali19.intrazero.domain.model.RemoteKeys
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RemoteKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<RemoteKeys>)

    @Query("SELECT * FROM remote_keys_table WHERE pageId = :pageId")
    suspend fun remoteKeysPageId(pageId: Long): RemoteKeys?

    @Query("DELETE FROM remote_keys_table")
    suspend fun clearRemoteKeys()
}
