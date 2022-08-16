package abdulrahman.ali19.intrazero.data.paging

import abdulrahman.ali19.intrazero.data.remote.PagePicsumApi
import abdulrahman.ali19.intrazero.domain.model.Page
import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

private const val STARTING_KEY = 1

class PicsumPigination @Inject constructor(
    private val api: PagePicsumApi
) : PagingSource<Int, Page>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Page> {
        val start = params.key ?: STARTING_KEY

        try {
            val request = api.getPagesWithPageAndLimit(
                pageNo = params.key.toString()
            ).map { it.toPage() } as ArrayList

            if (request.isNotEmpty()) {
                var counter = 0
                for (i in 0..request.size step 5) {
                    if (i > 0) {
                        request.add(i + counter, Page(isAd = true))
                        counter++
                    }
                }
            }

            //NOTE: ides may have a problem
            return LoadResult.Page(
                data = request,
                prevKey = when (start) {
                    STARTING_KEY -> null
                    else -> start - 1
                },
                nextKey = if (request.isEmpty()) null else start + 1
            )

        } catch (ex: HttpException) {
            return LoadResult.Error(ex)
        } catch (ex: IOException) {
            return LoadResult.Error(ex)
        }

    }


    override fun getRefreshKey(state: PagingState<Int, Page>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
