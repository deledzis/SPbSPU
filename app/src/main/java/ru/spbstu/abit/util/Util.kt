@file:Suppress("DEPRECATION")

package ru.spbstu.abit.util

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.res.ResourcesCompat
import ru.spbstu.abit.R

fun setPtSansTypeface(toolbar: Toolbar) {
    for (i in 0 until toolbar.childCount) {
        val view = toolbar.getChildAt(i)
        if (view is TextView && view.text == toolbar.title) {
            view.textSize = 22.0f
            view.typeface = ResourcesCompat.getFont(view.context, R.font.roboto_bold)
            break
        }
    }
}

fun getColorId(context: Context, colorId: Int): Int {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        context.resources.getColor(colorId, context.theme)
    } else {
        context.resources.getColor(colorId)
    }
}

fun getDrawableCompat(context: Context, drawableId: Int): Drawable {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        context.resources.getDrawable(drawableId, context.theme)
    } else {
        context.resources.getDrawable(drawableId)
    }
}

fun Context.getColorStateListId(colorId: Int): ColorStateList {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        resources.getColorStateList(colorId, theme)
    } else {
        resources.getColorStateList(colorId)
    }
}

fun Drawable.setColorFilter(color: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        this.colorFilter = BlendModeColorFilter(color, BlendMode.SRC_ATOP)
    } else {
        this.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
    }
}