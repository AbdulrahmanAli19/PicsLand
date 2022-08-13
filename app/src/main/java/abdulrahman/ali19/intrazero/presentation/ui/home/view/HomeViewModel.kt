package abdulrahman.ali19.intrazero.presentation.ui.home.view

import abdulrahman.ali19.intrazero.domain.usercase.GetPagesUseCase
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPagesUseCase: GetPagesUseCase
) : ViewModel() {

    val getPagesState get() = getPagesUseCase()

}