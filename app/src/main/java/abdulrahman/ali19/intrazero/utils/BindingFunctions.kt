package abdulrahman.ali19.intrazero.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("setGlideImage")
fun ImageView.setGlideImage(imageUrl: String?) {
    if (imageUrl != null)
        Glide
            .with(this)
            .load(imageUrl)
            .centerCrop()
            .into(this)
}