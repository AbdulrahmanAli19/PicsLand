package abdulrahman.ali19.intrazero.presentation.ui.home.viewmodel

import abdulrahman.ali19.intrazero.domain.model.PageListItem
import abdulrahman.ali19.intrazero.domain.repository.Repository
import abdulrahman.ali19.intrazero.domain.usercase.GetPagesUseCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    pagesUseCase: GetPagesUseCase
) : ViewModel() {

    val state: Flow<PagingData<PageListItem>>

    init {
        state = pagesUseCase.invoke().cachedIn(viewModelScope)
    }

}

