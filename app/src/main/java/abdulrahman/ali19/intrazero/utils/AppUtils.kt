package abdulrahman.ali19.intrazero.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

fun ImageView.setGlideImg(
    imageUrl: String,
    onFailed: () -> (Unit) = {},
    onSuccess: (Drawable?) -> (Unit) = {}
) {
    Glide
        .with(this)
        .load(imageUrl)
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                onFailed()
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                onSuccess(resource)
                return false
            }

        })
        .centerCrop()
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)

}

fun ImageView.setGlideImg(imageUrl: String) {
    Glide
        .with(this)
        .load(imageUrl)
        .transition(DrawableTransitionOptions.withCrossFade())
        .centerCrop()
        .into(this)
}