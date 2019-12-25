package ru.spbstu.abit.base

import android.content.Context
import android.content.res.ColorStateList
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import net.opacapp.multilinecollapsingtoolbar.CollapsingToolbarLayout
import ru.spbstu.abit.R
import ru.spbstu.abit.util.DebugUtils.logw
import ru.spbstu.abit.util.ResizeAnimation
import ru.spbstu.abit.util.getColorId
import ru.spbstu.abit.util.getDrawableCompat

abstract class BaseFragmentWithScrollableAppBar : BaseFragment() {

    protected var closingListener: IClosingListener? = null

    protected lateinit var mainView: View
    protected lateinit var recyclerView: RecyclerView
    protected lateinit var appBarLayout: AppBarLayout
    protected lateinit var collapsingToolbarLayout: CollapsingToolbarLayout
    protected lateinit var toolbar: Toolbar
    protected lateinit var bottomSheet: View
    protected lateinit var bottomSheetBehavior: BottomSheetBehavior<*>
    protected lateinit var bottomSheetCloseIcon: ImageView

    private var substrateHolder: ViewGroup? = null
    private lateinit var substrateImage: ImageView

    protected lateinit var toolbarTitle: String
    protected var subtractColorId = R.color.colorGreen
    protected var subtractColorLightId = R.color.colorLightSalad
    protected var subtractDrawableId = R.drawable.menu_texture

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (activity is IClosingListener) {
            closingListener = activity
        }
    }

    override fun parseArguments() {
        arguments?.let {
            if (it.containsKey(ARGUMENT_TITLE)) {
                toolbarTitle = it.getString(ARGUMENT_TITLE) ?: getString(
                    R.string.title_questions
                )
            }
            if (it.containsKey(ARGUMENT_COLOR)) {
                subtractColorId = it.getInt(ARGUMENT_COLOR)
            }
            if (it.containsKey(ARGUMENT_COLOR_LIGHT)) {
                subtractColorLightId = it.getInt(ARGUMENT_COLOR_LIGHT)
            }
            if (it.containsKey(ARGUMENT_DRAWABLE)) {
                subtractDrawableId = it.getInt(ARGUMENT_DRAWABLE)
            }
        }
    }

    override fun initViews() {
        with(mainView) {
            appBarLayout = findViewById(R.id.appbar)
            toolbar = findViewById(R.id.toolbar)
            collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar)
            substrateHolder = findViewById(R.id.substrate_holder)
            substrateImage = findViewById(R.id.substrate_image)
        }
    }

    override fun setContent() {
        collapsingToolbarLayout.setCollapsedTitleTypeface(
            ResourcesCompat.getFont(activity, R.font.roboto_bold)
        )
        collapsingToolbarLayout.setExpandedTitleTypeface(
            ResourcesCompat.getFont(activity, R.font.roboto_bold)
        )
    }

    protected fun scaleAppbarHeight(millis: Long = 750L) {
        var height = resources.getDimension(R.dimen.appbar_height_xs).toInt()
        when {
            toolbarTitle.length >= 40 -> height = resources.getDimension(R.dimen.appbar_height_xxl).toInt()
            toolbarTitle.length >= 37 -> height = resources.getDimension(R.dimen.appbar_height_xl).toInt()
            toolbarTitle.length >= 32 -> height = resources.getDimension(R.dimen.appbar_height_l).toInt()
            toolbarTitle.length >= 26 -> height = resources.getDimension(R.dimen.appbar_height_m).toInt()
            toolbarTitle.length >= 20 -> height = resources.getDimension(R.dimen.appbar_height_s).toInt()
        }

        val resizeAnimation = ResizeAnimation(
            appBarLayout,
            height,
            resources.getDimension(R.dimen.appbar_height_xs).toInt()
        )
        resizeAnimation.duration = millis
        appBarLayout.startAnimation(resizeAnimation)
    }

    protected fun setToolbarSpannableTitle(
        colorId: Int,
        drawableId: Int
    ) {
        if (colorId == R.color.colorDark) {
            setToolbarTitle(R.color.colorDark)
            toggleSubstrate(
                View.GONE,
                getColorId(activity, R.color.colorWhite),
                drawableId
            )
            return
        }

        toggleSubstrate(
            View.VISIBLE,
            getColorId(activity, colorId),
            drawableId
        )
        setToolbarTitle(R.color.colorWhite)
    }

    private fun setToolbarTitle(
        color: Int
    ) {
        collapsingToolbarLayout.title = toolbarTitle
        setToolbarTitleColor(color)
    }

    private fun setToolbarTitleColor(
        colorId: Int
    ) {
        collapsingToolbarLayout.setCollapsedTitleTextColor(
            ColorStateList.valueOf(getColorId(activity, colorId))
        )
        collapsingToolbarLayout.setExpandedTitleTextColor(
            ColorStateList.valueOf(getColorId(activity, colorId))
        )
    }

    private fun toggleSubstrate(
        visibility: Int,
        colorId: Int,
        drawableId: Int
    ) {
        if (substrateHolder == null) {
            return
        }
        substrateHolder?.visibility = visibility
        appBarLayout.setBackgroundColor(getColorId(activity, R.color.colorTransparent))
        collapsingToolbarLayout.setContentScrimColor(colorId)
        if (drawableId != 0) {
            substrateImage.setImageDrawable(getDrawableCompat(activity, drawableId))
        } else {
            substrateImage.setImageResource(0)
            substrateImage.setBackgroundColor(colorId)
        }

        paintStatusBar(colorId)
    }

    protected fun closeBottomSheet(): Boolean {
        return if (bottomSheetBehavior.state != BottomSheetBehavior.STATE_HIDDEN) {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
            true
        } else {
            false
        }
    }

    fun collapseAppBar() {
        appBarLayout.setExpanded(false)
    }

    override fun onDestroy() {
        //closingListener?.fragmentClosed()
        logw(TAG, "On Destroy called fragment closed")
        super.onDestroy()
    }

    interface IClosingListener {
        fun fragmentClosed()
    }

    companion object {
        private const val TAG = "BaseFragmentWithScroll"

        const val ARGUMENT_LIST = "list"
        const val ARGUMENT_TITLE = "title"
        const val ARGUMENT_COLOR = "color"
        const val ARGUMENT_COLOR_LIGHT = "color_light"
        const val ARGUMENT_DRAWABLE = "drawable"
    }
}