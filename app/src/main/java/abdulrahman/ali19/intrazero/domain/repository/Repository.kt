package abdulrahman.ali19.intrazero.domain.repository

import abdulrahman.ali19.intrazero.data.remote.dto.PageDto
import abdulrahman.ali19.intrazero.domain.model.Page
import abdulrahman.ali19.intrazero.domain.model.RemoteKeys
import abdulrahman.ali19.intrazero.utils.DatabaseResponse
import abdulrahman.ali19.intrazero.utils.NetworkResponse
import android.util.Log
import androidx.paging.*
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun getPagesWithPageAndLimit(): Flow<PagingData<Page>>

    suspend fun insertPage(page: Page)

    suspend fun deletePage(page: Page)

    suspend fun getAllPages(): PagingSource<Int, Page>

    suspend fun clearAllPages()

    suspend fun insertAll(remoteKey: List<RemoteKeys>)

    suspend fun remoteKeysPageId(pageId: Long): RemoteKeys?

    suspend fun clearRemoteKeys()

    suspend fun insertAllPages(pages: List<Page>)


}