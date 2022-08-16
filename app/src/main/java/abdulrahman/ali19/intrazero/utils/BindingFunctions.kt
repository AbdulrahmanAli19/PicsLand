package abdulrahman.ali19.intrazero.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

@BindingAdapter("setGlideImage")
fun ImageView.setGlideImage(imageUrl: String?) {
    if (imageUrl != null)
        Glide
            .with(this)
            .load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .centerCrop()
            .into(this)
}