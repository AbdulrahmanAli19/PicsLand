package abdulrahman.ali19.intrazero.domain.model

data class PageListState(
    val isLoading: Boolean = false,
    val isEmpty: Boolean = false,
    val error: String = "error",
    val data: List<Page> = emptyList()
)