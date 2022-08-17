package abdulrahman.ali19.intrazero.presentation.ui.home.view

import abdulrahman.ali19.intrazero.domain.model.PageListItem
import abdulrahman.ali19.intrazero.domain.repository.Repository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.insertSeparators
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    repo: Repository,
) : ViewModel() {

    val state: Flow<PagingData<PageListItem>> = repo.getPagesWithPageAndLimit()
        .map { pagingData -> pagingData.map { PageListItem.PageItem(it) } }
        .map { pageListItem ->
            pageListItem.insertSeparators { after, before ->
                if (after == null) {
                    return@insertSeparators null
                }
                if (after.page.id.toInt() % 5 == 0) {
                    return@insertSeparators PageListItem.SeparatorItem
                } else {
                    null
                }
            }
        }
        .cachedIn(viewModelScope)
}

