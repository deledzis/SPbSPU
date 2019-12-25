package ru.spbstu.abit.util

import android.content.res.Resources
import android.view.View
import android.view.animation.Animation
import android.view.animation.Transformation

class ResizeAnimation(
    private val view: View,
    private val targetHeight: Int,
    private val startHeight: Int
) : Animation() {

    private val startAlpha = 0.0F
    private val targetAlpha = 1.0F

    override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
        view.layoutParams.height = (startHeight + (targetHeight - startHeight) * interpolatedTime).toInt()
        view.alpha = (startAlpha + (targetAlpha - startAlpha) * interpolatedTime)
        view.requestLayout()
    }

    override fun willChangeBounds(): Boolean {
        return true
    }

    companion object {

        fun dpToPx(dp: Int): Int {
            return (dp * Resources.getSystem().displayMetrics.density).toInt()
        }

        fun pxToDp(px: Int): Int {
            return (px / Resources.getSystem().displayMetrics.density).toInt()
        }
    }
}
