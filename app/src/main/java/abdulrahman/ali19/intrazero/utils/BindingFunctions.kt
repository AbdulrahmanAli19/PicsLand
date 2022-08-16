package abdulrahman.ali19.intrazero.utils

import abdulrahman.ali19.intrazero.R
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.size.Scale

@BindingAdapter("setCoilImage")
fun ImageView.setCoilImage(imageUrl: String?) {
    if (imageUrl != null)
        load(imageUrl) {
            placeholder(R.drawable.img_placeholder)
            error(R.drawable.error)
            crossfade(1000)
            scale(Scale.FILL)
        }
}