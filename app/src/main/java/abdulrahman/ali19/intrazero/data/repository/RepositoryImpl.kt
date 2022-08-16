package abdulrahman.ali19.intrazero.data.repository

import abdulrahman.ali19.intrazero.data.local.IntrazeroDatabase
import abdulrahman.ali19.intrazero.data.paging.PicsumPigination
import abdulrahman.ali19.intrazero.data.paging.PicsumRemoteMediator
import abdulrahman.ali19.intrazero.data.remote.PagePicsumApi
import abdulrahman.ali19.intrazero.data.remote.dto.PageDto
import abdulrahman.ali19.intrazero.domain.model.Page
import abdulrahman.ali19.intrazero.domain.model.RemoteKeys
import abdulrahman.ali19.intrazero.domain.repository.Repository
import abdulrahman.ali19.intrazero.utils.NetworkResponse
import android.util.Log
import androidx.paging.*
import kotlinx.coroutines.flow.Flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

private const val NETWORK_PAGE_SIZE = 30

@OptIn(ExperimentalPagingApi::class)
class RepositoryImpl @Inject constructor(
    private val db: IntrazeroDatabase,
    private val picsumRemoteMediator: PicsumRemoteMediator,
    private val picsumPigination: PicsumPigination
) : Repository {

    override fun getPagesWithPageAndLimit(): Flow<PagingData<Page>> {

        //val pagingSourceFactory = { db.pageDao().getAllPages() }

        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            //remoteMediator = picsumRemoteMediator,
            pagingSourceFactory = { picsumPigination }
        ).flow
    }

    override suspend fun insertPage(page: Page) = db.pageDao().insertPage(page)

    override suspend fun deletePage(page: Page) = db.pageDao().deletePage(page)

    override suspend fun getAllPages(): PagingSource<Int, Page> = db.pageDao().getAllPages()

    override suspend fun clearAllPages() = db.pageDao().clearAllPages()

    override suspend fun insertAll(remoteKey: List<RemoteKeys>) =
        db.remoteKeysDao().insertAll(remoteKey)

    override suspend fun remoteKeysPageId(pageId: Long): RemoteKeys? =
        db.remoteKeysDao().remoteKeysPageId(pageId)

    override suspend fun clearRemoteKeys() = db.remoteKeysDao().clearRemoteKeys()

    override suspend fun insertAllPages(pages: List<Page>) = db.pageDao().insertAll(pages)

}