package abdulrahman.ali19.intrazero.presentation.ui.home.adapters

import abdulrahman.ali19.intrazero.databinding.AdLayoutBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdRequest

class AdViewHolder(binding: AdLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(view: ViewGroup): AdViewHolder {
            val binding = AdLayoutBinding.inflate(
                LayoutInflater.from(view.context), view, false
            )
            binding.adView.loadAd(AdRequest.Builder().build())
            return AdViewHolder(binding)
        }
    }
}