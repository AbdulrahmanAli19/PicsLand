package abdulrahman.ali19.intrazero.presentation.ui.home.adapters

import abdulrahman.ali19.intrazero.databinding.AdLayoutBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class AdViewHolder(private val bining: AdLayoutBinding) : RecyclerView.ViewHolder(bining.root) {

    companion object {
        fun create(view: ViewGroup) =
            AdViewHolder(
                AdLayoutBinding.inflate(
                    LayoutInflater.from(view.context), view, false
                )
            )
    }
}