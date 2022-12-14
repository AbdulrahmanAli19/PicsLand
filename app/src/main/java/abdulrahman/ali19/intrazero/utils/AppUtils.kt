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
import coil.request.ErrorResult
import coil.request.ImageRequest
import coil.size.Scale

fun ImageView.setCoilImage(
    imageUrl: String?,
    onSuccess: (Drawable) -> (Unit) = {},
    onStart: () -> (Unit) = {},
    onError: (ImageRequest, ErrorResult) -> (Unit) = { _, _ -> }
) {
    if (imageUrl != null) {
        load(imageUrl) {
            crossfade(750)
            placeholder(R.drawable.img_placeholder)
            error(R.drawable.error)
            scale(Scale.FILL)
            allowHardware(false)
            listener(
                onSuccess = { _, result -> onSuccess(result.drawable) },
                onStart = { onStart() },
                onError = { imageResult, errorResult -> onError(imageResult, errorResult) })
        }
    }
}

fun View.setDominantBackgroundColorWithAnimation(
    image: Drawable,
    duration: Long = 750,
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