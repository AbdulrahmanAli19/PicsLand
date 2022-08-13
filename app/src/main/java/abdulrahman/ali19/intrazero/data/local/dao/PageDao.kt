package abdulrahman.ali19.intrazero.data.local.dao

import abdulrahman.ali19.intrazero.domain.model.Page
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PageDao {

    @Insert
    fun insertPage(page: Page): Int

    @Delete
    fun deletePage(page: Page): Int

    @Query("SELECT * FROM page_table")
    suspend fun getAllPages(): List<Page>

    @Query("DELETE FROM page_table")
    suspend fun deleteAllPages(): Int
}