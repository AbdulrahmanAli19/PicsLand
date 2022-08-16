package abdulrahman.ali19.intrazero.presentation.ui.home.adapters

import abdulrahman.ali19.intrazero.databinding.PageItemBinding
import abdulrahman.ali19.intrazero.domain.model.Page
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class PicsumAdapter(
    private val onItemClick: (Page) -> (Unit)
) : PagingDataAdapter<Page, PicsumAdapter.PicsumViewHolder>(PAGE_DIFF_CALLBACK) {

    inner class PicsumViewHolder(private val binding: PageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(page: Page) {
            if (!page.isAd) {
                binding.parentCard.setOnClickListener { onItemClick(page) }
                binding.page = page
                binding.adView.visibility = View.GONE
                binding.pageImage.visibility = View.VISIBLE
            } else {
                binding.parentCard.setOnClickListener { }
                binding.adView.visibility = View.VISIBLE
                binding.pageImage.visibility = View.GONE
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PicsumViewHolder(
            PageItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: PicsumViewHolder, position: Int) =
        holder.bind(getItem(position) ?: Page())

    companion object {
        private val PAGE_DIFF_CALLBACK = object : DiffUtil.ItemCallback<Page>() {
            override fun areItemsTheSame(oldItem: Page, newItem: Page): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Page, newItem: Page): Boolean =
                oldItem == newItem
        }
    }

}