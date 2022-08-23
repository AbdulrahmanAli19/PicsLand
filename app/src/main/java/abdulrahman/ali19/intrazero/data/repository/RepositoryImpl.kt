package abdulrahman.ali19.intrazero.data.repository

import abdulrahman.ali19.intrazero.data.local.IntrazeroDatabase
import abdulrahman.ali19.intrazero.data.paging.PicsPagination
import abdulrahman.ali19.intrazero.data.paging.PicsumRemoteMediator
import abdulrahman.ali19.intrazero.domain.model.Page
import abdulrahman.ali19.intrazero.domain.repository.Repository
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

private const val NETWORK_PAGE_SIZE = 30

@OptIn(ExperimentalPagingApi::class)
class RepositoryImpl @Inject constructor(
    private val db: IntrazeroDatabase,
    private val picsumRemoteMediator: PicsumRemoteMediator,
    private val picsumPigination: PicsPagination
) : Repository {

    override fun getPagesWithPageAndLimit(): Flow<PagingData<Page>> {
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { picsumPigination }
        ).flow
    }

    override fun getPagesFromMediator(): Flow<PagingData<Page>> {
        val pagingSourceFactory = { db.pageDao().getAllPages() }
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            remoteMediator = picsumRemoteMediator,
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

}