package abdulrahman.ali19.intrazero.presentation.ui.home.view

import abdulrahman.ali19.intrazero.domain.model.Page
import abdulrahman.ali19.intrazero.domain.repository.Repository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    repo: Repository,
) : ViewModel() {

    val state: Flow<PagingData<Page>> = repo.getPagesWithPageAndLimit()
        .cachedIn(viewModelScope)
}

