package abdulrahman.ali19.intrazero.domain.usercase

import abdulrahman.ali19.intrazero.domain.model.PageListItem
import abdulrahman.ali19.intrazero.domain.repository.Repository
import androidx.paging.PagingData
import androidx.paging.insertSeparators
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPagesUseCase @Inject constructor(
    private val repo: Repository,
) {

    operator fun invoke(): Flow<PagingData<PageListItem>> = flow {
        emitAll(repo.getPagesWithPageAndLimit()
            .map { pagingData ->
                var index = 0
                pagingData.map { PageListItem.PageItem(it, ++index) }
            }
            .map { pageListItem ->
                pageListItem.insertSeparators { after, _ ->
                    if (after == null)
                        return@insertSeparators null
                    if (after.index % 5 == 0 && after.index > 0)
                        return@insertSeparators PageListItem.SeparatorItem
                    else
                        null
                }
            })
    }
}