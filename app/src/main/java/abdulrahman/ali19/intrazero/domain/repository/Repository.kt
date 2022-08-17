package abdulrahman.ali19.intrazero.domain.repository

import abdulrahman.ali19.intrazero.domain.model.Page
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun getPagesWithPageAndLimit(): Flow<PagingData<Page>>

    fun getPagesFromMediator(): Flow<PagingData<Page>>


}