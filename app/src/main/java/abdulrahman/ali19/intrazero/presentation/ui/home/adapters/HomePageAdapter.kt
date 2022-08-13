package abdulrahman.ali19.intrazero.presentation.ui.home.adapters

import abdulrahman.ali19.intrazero.databinding.PageItemBinding
import abdulrahman.ali19.intrazero.domain.model.Page
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class HomePageAdapter(
    private val onItemClick: (Page) -> (Unit)
) : RecyclerView.Adapter<HomePageAdapter.HomePageViewHolder>() {

    private var pages: List<Page> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun setList(newList: List<Page>) {
        pages = newList
        notifyDataSetChanged()
    }

    inner class HomePageViewHolder(private val binding: PageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val page: Page
            get() = pages[bindingAdapterPosition]

        init {
            binding.parentCard.setOnClickListener {
                onItemClick(page)
            }
        }

        fun bind() {
            binding.page = page
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        HomePageViewHolder(
            PageItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: HomePageViewHolder, position: Int) = holder.bind()

    override fun getItemCount() = pages.size

}