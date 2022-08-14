package abdulrahman.ali19.intrazero.presentation.ui.home.view

import abdulrahman.ali19.intrazero.domain.model.PageListState
import abdulrahman.ali19.intrazero.domain.usercase.GetPagesUseCase
import abdulrahman.ali19.intrazero.utils.NetworkResult
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPagesUseCase: GetPagesUseCase,
) : ViewModel() {

    private val _pagesState = MutableStateFlow(PageListState())
    val getPagesState get() = _pagesState.asStateFlow()

    fun getPages(pageNo: String, limit: String) {
        getPagesUseCase(pageNo, limit).onEach {
            when (it) {
                NetworkResult.EmptyResult -> _pagesState.value = PageListState(isEmpty = true)
                is NetworkResult.Error -> _pagesState.value = PageListState(error = it.errorString)
                NetworkResult.Loading -> _pagesState.value = PageListState(isLoading = true)
                is NetworkResult.Success -> _pagesState.value = PageListState(data = it.data)
            }
        }.launchIn(viewModelScope)
    }

}