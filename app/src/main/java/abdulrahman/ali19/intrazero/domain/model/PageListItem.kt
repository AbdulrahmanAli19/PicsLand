package abdulrahman.ali19.intrazero.domain.model

sealed class PageListItem {
    data class PageItem(val page: Page, val index: Int) : PageListItem()
    object SeparatorItem : PageListItem()
}