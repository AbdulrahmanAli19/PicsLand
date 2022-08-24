package abdulrahman.ali19.intrazero.presentation.ui.home.adapters.load_state

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class PageLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<PagesLoadStateViewHolder>() {

    override fun onBindViewHolder(holder: PagesLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): PagesLoadStateViewHolder {
        return PagesLoadStateViewHolder.create(parent, retry)
    }
}
