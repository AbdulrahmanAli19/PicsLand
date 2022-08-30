package abdulrahman.ali19.intrazero.data.paging

import abdulrahman.ali19.intrazero.data.remote.PagePicsumApi
import abdulrahman.ali19.intrazero.domain.model.Page
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

private const val STARTING_KEY = 1
private const val TAG = "PicsPagination"

class PicsPagination @Inject constructor(
    private val api: PagePicsumApi
) : PagingSource<Int, Page>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Page> {
        val start = params.key ?: STARTING_KEY

        return try {
            val request = api.getPagesWithPageAndLimit(
                pageNo = params.key.toString(),
                limit = params.loadSize.toString()
            ).map { it.toPage() }
            Log.d(TAG, "load: ${params.key} ${request.size}")
            LoadResult.Page(
                data = request,
                prevKey = if (start == STARTING_KEY) null else start.minus(1),
                nextKey = if (request.isEmpty()) null else start.plus(1)
            )
        } catch (ex: HttpException) {
            LoadResult.Error(ex)
        } catch (ex: IOException) {
            LoadResult.Error(ex)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, Page>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
