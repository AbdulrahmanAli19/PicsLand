package abdulrahman.ali19.intrazero.domain.usercase

import abdulrahman.ali19.intrazero.domain.model.Page
import abdulrahman.ali19.intrazero.domain.repository.Repository
import abdulrahman.ali19.intrazero.utils.NetworkResponse
import abdulrahman.ali19.intrazero.utils.NetworkResult
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import javax.inject.Inject

class GetPagesUseCase @Inject constructor(
    private val repo: Repository
) {
    operator fun invoke(): LiveData<NetworkResult<List<Page>>> = liveData {

        emit(NetworkResult.Loading)

        when (val pages = repo.getAllPages()) {
            is NetworkResponse.SuccessResponse -> {
                if (pages.data.isNotEmpty())
                    emit(NetworkResult.Success(pages.data.map { it.toPage() }))
                else
                    emit(NetworkResult.EmptyResult)
            }
            is NetworkResponse.FailureResponse -> {
                emit(NetworkResult.Error(errorString = pages.errorString))
            }
        }

    }
}