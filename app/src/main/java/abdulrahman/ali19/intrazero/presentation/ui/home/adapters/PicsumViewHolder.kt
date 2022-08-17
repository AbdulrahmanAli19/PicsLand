package abdulrahman.ali19.intrazero.presentation.ui.home.adapters

import abdulrahman.ali19.intrazero.databinding.PageItemBinding
import abdulrahman.ali19.intrazero.domain.model.Page
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PicsumViewHolder(
    private val binding: PageItemBinding,
    private val onItemClick: (Page) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(page: Page) {
        binding.parentCard.setOnClickListener { onItemClick(page) }
        binding.page = page
        binding.pageImage.visibility = View.VISIBLE
    }

    companion object {
        fun create(view: ViewGroup, onItemClick: (Page) -> Unit): PicsumViewHolder {
            return PicsumViewHolder(
                PageItemBinding.inflate(
                    LayoutInflater.from(view.context),
                    view,
                    false
                ), onItemClick
            )
        }
    }
}