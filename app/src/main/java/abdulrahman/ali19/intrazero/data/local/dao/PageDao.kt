package abdulrahman.ali19.intrazero.data.local.dao

import abdulrahman.ali19.intrazero.domain.model.Page
import androidx.paging.PagingSource
import androidx.room.*

@Dao
interface PageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPage(page: Page)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(pages: List<Page>)

    @Delete
    suspend fun deletePage(page: Page)

    @Query("SELECT * FROM page_table")
    fun getAllPages(): PagingSource<Int, Page>

    @Query("DELETE FROM page_table")
    suspend fun clearAllPages()
}