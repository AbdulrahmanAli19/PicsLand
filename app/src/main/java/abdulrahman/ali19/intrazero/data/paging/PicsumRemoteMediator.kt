package abdulrahman.ali19.intrazero.data.paging

import abdulrahman.ali19.intrazero.data.local.IntrazeroDatabase
import abdulrahman.ali19.intrazero.data.remote.PagePicsumApi
import abdulrahman.ali19.intrazero.domain.model.Page
import abdulrahman.ali19.intrazero.domain.model.RemoteKeys
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

private const val PICSUM_STARTING_PAGE_INDEX = 1

@OptIn(ExperimentalPagingApi::class)
class PicsumRemoteMediator @Inject constructor(
    private val api: PagePicsumApi,
    private val db: IntrazeroDatabase,
) : RemoteMediator<Int, Page>() {


    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Page>): MediatorResult {

        val currentPage = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: PICSUM_STARTING_PAGE_INDEX
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKeys?.prevKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                prevKey
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                nextKey
            }
        }

        try {
            val data = api.getPagesWithPageAndLimit(
                pageNo = currentPage.toString(),
                limit = state.config.pageSize.toString()
            )
            val endOfPaginationReached = data.isEmpty()
            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    db.remoteKeysDao().clearRemoteKeys()
                    db.pageDao().clearAllPages()
                }
                val prevKey =
                    if (currentPage == PICSUM_STARTING_PAGE_INDEX) null else currentPage - 1
                val nextKey = if (endOfPaginationReached) null else currentPage + 1
                val keys = data.map {
                    RemoteKeys(pageId = it.id.toInt(), prevKey = prevKey, nextKey = nextKey)
                }
                db.remoteKeysDao().insertAll(keys)
                db.pageDao().insertAll(data.map { it.toPage() })
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }

    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, Page>): RemoteKeys? {
        return state.pages.lastOrNull() { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { page ->
                db.remoteKeysDao().remoteKeysPageId(page.id.toLong())
            }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, Page>): RemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { page ->
                db.remoteKeysDao().remoteKeysPageId(page.id.toLong())
            }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, Page>): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { repoId ->
                db.remoteKeysDao().remoteKeysPageId(repoId.toLong())
            }
        }
    }
}