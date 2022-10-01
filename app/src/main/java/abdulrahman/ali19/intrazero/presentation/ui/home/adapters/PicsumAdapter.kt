package abdulrahman.ali19.intrazero.presentation.ui.home.adapters

import abdulrahman.ali19.intrazero.domain.model.Page
import abdulrahman.ali19.intrazero.domain.model.PageListItem
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class PicsumAdapter(
    private val onItemClick: (Page) -> (Unit)
) : PagingDataAdapter<PageListItem, RecyclerView.ViewHolder>(PAGE_DIFF_CALLBACK) {


    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is PageListItem.PageItem -> ViewType.PAGE.ordinal
            PageListItem.SeparatorItem -> ViewType.SEPARATOR.ordinal
            null -> throw UnsupportedOperationException("Unexpected View")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == ViewType.PAGE.ordinal) {
            PicsumViewHolder.create(parent, onItemClick)
        } else {
            AdViewHolder.create(parent)
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let {
            when (it) {
                is PageListItem.PageItem -> (holder as PicsumViewHolder).bind(it.page)
                PageListItem.SeparatorItem -> Unit
            }
        }

    }

    companion object {

        private enum class ViewType {
            PAGE, SEPARATOR
        }

        private val PAGE_DIFF_CALLBACK = object : DiffUtil.ItemCallback<PageListItem>() {
            override fun areItemsTheSame(oldItem: PageListItem, newItem: PageListItem): Boolean =
                compareCat(oldItem, newItem) ||
                        compareSeparator(oldItem, newItem)

            override fun areContentsTheSame(oldItem: PageListItem, newItem: PageListItem): Boolean =
                oldItem == newItem
        }

        private fun compareSeparator(
            oldItem: PageListItem,
            newItem: PageListItem
        ) = (oldItem is PageListItem.SeparatorItem && newItem is PageListItem.SeparatorItem &&
                oldItem == newItem)

        private fun compareCat(
            oldItem: PageListItem,
            newItem: PageListItem
        ) = (oldItem is PageListItem.PageItem && newItem is PageListItem.PageItem &&
                oldItem.page.id == newItem.page.id)

    }

}