package ru.spbstu.abit.base

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.CalendarContract
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import ru.spbstu.abit.MainActivity
import ru.spbstu.abit.R
import ru.spbstu.abit.locations.model.Building
import ru.spbstu.abit.timeline.TimelineEvent
import ru.spbstu.abit.util.getColorId
import java.util.*

abstract class BaseFragment : Fragment() {

    protected lateinit var activity: MainActivity

    protected abstract fun parseArguments()

    protected abstract fun initViews()

    protected abstract fun initToolbar()

    protected abstract fun setContent()

    abstract fun onBackPressed(): Boolean

    override fun onAttach(context: Context) {
        super.onAttach(context)

        activity = context as MainActivity
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        var animation: Animation? = super.onCreateAnimation(transit, enter, nextAnim)

        if (animation == null && nextAnim != 0) {
            animation = AnimationUtils.loadAnimation(activity, nextAnim)
        }

        val view = view ?: return animation

        if (animation != null) {
            view.setLayerType(View.LAYER_TYPE_HARDWARE, null)

            animation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {}

                override fun onAnimationEnd(animation: Animation) {
                    view.setLayerType(View.LAYER_TYPE_NONE, null)
                }

                override fun onAnimationRepeat(animation: Animation) {}
            })
        }

        return animation
    }

    fun paintStatusBar(colorId: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = activity.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = colorId
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val view = activity.window.decorView
            if (colorId == getColorId(activity, R.color.colorWhite)) {
                view.systemUiVisibility = view.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                view.systemUiVisibility = view.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
            }
        }
    }

    fun openWebPage(url: String) {
        val webpage: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        if (intent.resolveActivity(activity.packageManager) != null) {
            startActivity(intent)
        }
    }

    fun dialPhoneNumber(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }
        if (intent.resolveActivity(activity.packageManager) != null) {
            startActivity(intent)
        }
    }

    fun composeEmail(address: String, subject: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:") // only email apps should handle this
            putExtra(Intent.EXTRA_EMAIL, address)
            putExtra(Intent.EXTRA_SUBJECT, subject)
        }
        if (intent.resolveActivity(activity.packageManager) != null) {
            startActivity(intent)
        }
    }

    fun addToCalendar(timelineEvent: TimelineEvent) {
        val calIntent = Intent(Intent.ACTION_INSERT)
        calIntent.type = "vnd.android.cursor.item/event"
        calIntent.putExtra(CalendarContract.Events.TITLE, getString(R.string.calendar_title))
        calIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, Building.MAIN_BUILDING.addressName)
        calIntent.putExtra(CalendarContract.Events.DESCRIPTION, timelineEvent.description)

        val calDate = GregorianCalendar()
        calDate.time = timelineEvent.date
        calIntent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true)
        calIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, calDate.timeInMillis)
        calIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, calDate.timeInMillis)

        startActivity(calIntent)
    }

    companion object {
        private const val TAG = "BaseFragment"
    }
}
