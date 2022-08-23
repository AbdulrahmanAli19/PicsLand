package abdulrahman.ali19.intrazero.utils

import abdulrahman.ali19.intrazero.R
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette
import coil.load
import coil.size.Scale

fun ImageView.setCoilImage(imageUrl: String?, onSuccess: (Drawable) -> (Unit) = {}) {
    if (imageUrl != null) {
        load(imageUrl) {
            crossfade(1000)
            placeholder(R.drawable.img_placeholder)
            error(R.drawable.error)
            scale(Scale.FILL)
            allowHardware(false)
            listener(onSuccess = { _, result -> onSuccess(result.drawable) })
        }
    }
}

fun View.setDominantBackgroundColorWithAnimation(
    image: Drawable,
    duration: Long = 1000,
    colorFrom: Int = Color.WHITE
) {
    Palette.from(image.toBitmap()).generate { palette ->

        val colorTo =
            palette?.getDominantColor(
                ContextCompat.getColor(this.context, R.color.white)
            ) ?: Color.WHITE

        ObjectAnimator.ofObject(
            this,
            "backgroundColor",
            ArgbEvaluator(),
            colorFrom,
            colorTo
        ).setDuration(duration).start()
    }
}