package ru.spbstu.abit.util

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout
import ru.spbstu.abit.R


class CollapsingImageBehavior(context: Context, attrs: AttributeSet?) :
    CoordinatorLayout.Behavior<View>() {

    private var targetId: Int = 0
    private var viewAttrs: IntArray? = null
    private var targetAttrs: IntArray? = null

    init {
        if (attrs != null) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.CollapsingImageBehavior)
            targetId = a.getResourceId(R.styleable.CollapsingImageBehavior_collapsedTarget, 0)
            a.recycle()
        }
        if (targetId == 0) {
            throw IllegalStateException("collapsedTarget attribute not specified on view for behavior")
        }
    }

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        return dependency is AppBarLayout
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {

        setup(parent, child)

        val appBarLayout = dependency as AppBarLayout

        val range = appBarLayout.totalScrollRange
        val factor = -appBarLayout.y / range

        val left = viewAttrs!![X] + (factor * (targetAttrs!![X] - viewAttrs!![X]))
        val top = viewAttrs!![Y] + (factor * (targetAttrs!![Y] - viewAttrs!![Y]))
        val width =
            viewAttrs!![WIDTH] + (factor * (targetAttrs!![WIDTH] - viewAttrs!![WIDTH])).toInt()
        val height =
            viewAttrs!![HEIGHT] + (factor * (targetAttrs!![HEIGHT] - viewAttrs!![HEIGHT])).toInt()

        val lp = child.layoutParams as CoordinatorLayout.LayoutParams
        lp.width = width
        lp.height = height
        child.layoutParams = lp
        child.x = left
        child.y = top

        return true
    }

    private fun setup(parent: CoordinatorLayout, child: View) {

        if (viewAttrs != null) return

        viewAttrs = IntArray(4)
        targetAttrs = IntArray(4)

        (viewAttrs ?: return)[X] = child.x.toInt()
        (viewAttrs ?: return)[Y] = child.y.toInt()
        (viewAttrs ?: return)[WIDTH] = child.width
        (viewAttrs ?: return)[HEIGHT] = child.height

        val target: View =
            parent.findViewById(targetId) ?: throw IllegalStateException("target view not found")

        (targetAttrs ?: return)[WIDTH] += target.width
        (targetAttrs ?: return)[HEIGHT] += target.height

        var view: View = target
        while (view !== parent) {
            (targetAttrs ?: return)[X] += view.x.toInt()
            (targetAttrs ?: return)[Y] += view.y.toInt()
            view = view.parent as View
        }

    }

    companion object {

        private const val X = 0
        private const val Y = 1
        private const val WIDTH = 2
        private const val HEIGHT = 3
    }
}