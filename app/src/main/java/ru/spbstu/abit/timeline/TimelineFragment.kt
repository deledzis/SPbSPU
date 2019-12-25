package ru.spbstu.abit.timeline

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.fragment_timeline.view.*
import ru.spbstu.abit.MainActivity.Companion.INACTIVE_TOOLBAR_BACK_BUTTON
import ru.spbstu.abit.R
import ru.spbstu.abit.base.BaseFragmentWithScrollableAppBar
import ru.spbstu.abit.data.DefaultRepository
import java.text.SimpleDateFormat
import java.util.*

class TimelineFragment : BaseFragmentWithScrollableAppBar(),
    TimelineEventsRecyclerAdapter.OnTimelineEventsListener {

    protected lateinit var recyclerViewOutdated: RecyclerView

    private lateinit var timelineEventsUpcoming: ArrayList<TimelineEvent>
    private lateinit var timelineEventsOutdated: ArrayList<TimelineEvent>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainView = inflater.inflate(
            R.layout.fragment_timeline,
            container,
            false
        )

        parseArguments()
        initViews()
        initToolbar()
        setContent()

        return mainView
    }

    override fun initViews() {
        super.initViews()
        with(mainView) {
            recyclerViewOutdated = list_outdated
            recyclerView = list_upcoming
        }
    }

    override fun initToolbar() {
        activity.setSupportActionBar(toolbar)
        activity.toggleToolbarBackButton(INACTIVE_TOOLBAR_BACK_BUTTON)
        setToolbarSpannableTitle(
            R.color.colorDark,
            0
        )
    }

    override fun setContent() {
        super.setContent()

        timelineEventsUpcoming = arrayListOf()
        timelineEventsOutdated = arrayListOf()

        setNextEventSelected(DefaultRepository.getDefaultEventsList(activity))

        recyclerViewOutdated.layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerViewOutdated.adapter = TimelineEventsRecyclerAdapter(timelineEventsOutdated, this)
        recyclerView.adapter = TimelineEventsRecyclerAdapter(timelineEventsUpcoming, this)
    }

    override fun onBackPressed(): Boolean {
        return false
    }

    override fun onTimelineEventClicked(timelineEvent: TimelineEvent) {
        val dateFormat = SimpleDateFormat("dd.MM.YYYY", Locale.getDefault())
        MaterialAlertDialogBuilder(activity)
            .setTitle(getString(R.string.add_to_calendar))
            .setMessage(
                getString(
                    R.string.calendar_message,
                    timelineEvent.description,
                    dateFormat.format(timelineEvent.date)
                )
            )
            .setPositiveButton(getString(R.string.yes)) { _, _ ->
                addToCalendar(timelineEvent)
            }
            .setNegativeButton(getString(R.string.no)) { dialog, _ -> dialog.dismiss() }
            .show()
    }

    private fun setNextEventSelected(timelineEvents: ArrayList<TimelineEvent>) {
        for (i in 0 until timelineEvents.size) {
            if (timelineEvents[i].date.after(Calendar.getInstance().time)) {
                timelineEvents[i].isSelected = true
                timelineEventsUpcoming.addAll(timelineEvents.subList(i, timelineEvents.size))
                return
            } else {
                timelineEventsOutdated.add(timelineEvents[i])
            }
        }
    }

    override fun onTimelineEventLongClicked(timelineEvent: TimelineEvent) {
        onTimelineEventClicked(timelineEvent)
    }

    companion object {
        private const val TAG = "TimelineFragment"

        @JvmStatic
        fun newInstance(): TimelineFragment {
            return TimelineFragment()
        }
    }
}