package abdulrahman.ali19.intrazero.presentation.ui.home.viewmodel

import abdulrahman.ali19.intrazero.domain.model.PageListItem
import abdulrahman.ali19.intrazero.domain.usercase.GetPagesUseCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.insertSeparators
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    pagesUseCase: GetPagesUseCase
) : ViewModel() {

    val state: Flow<PagingData<PageListItem>>

    init {
        state = pagesUseCase.invoke()
            .map { pageListItem ->
                pageListItem.insertSeparators { after, _ ->
                    if (after == null)
                        return@insertSeparators null
                    after as PageListItem.PageItem
                    if (after.index % 5 == 0 && after.index > 0)
                        return@insertSeparators PageListItem.SeparatorItem
                    null
                }
            }.cachedIn(viewModelScope)
    }

}

