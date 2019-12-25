package ru.spbstu.abit.timeline

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.list_item_timeline_event.view.*
import ru.spbstu.abit.R
import ru.spbstu.abit.util.getColorId
import java.text.SimpleDateFormat
import java.util.*

class TimelineEventsRecyclerAdapter (
    private val events: List<TimelineEvent>,
    private val listener: OnTimelineEventsListener
) : RecyclerView.Adapter<TimelineEventsRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item_timeline_event,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val event = events[position]
        with(holder) {
            item = event

            when (position) {
                0 -> {
                    divider.visibility = View.INVISIBLE
                    divider2.visibility = View.VISIBLE
                }
                events.size - 1 -> {
                    divider.visibility = View.VISIBLE
                    divider2.visibility = View.INVISIBLE
                }
                else -> {
                    divider.visibility = View.VISIBLE
                    divider2.visibility = View.VISIBLE
                }
            }

            month.text = SimpleDateFormat("MMMM", Locale.getDefault()).format(item.date)
            day.text = SimpleDateFormat("dd", Locale.getDefault()).format(item.date)
            educationForm.text = event.educationForm
            fundingForm.text = event.fundingForm
            description.text = event.description

            when {
                event.isSelected -> {
                    month.setTextColor(getColorId(day.context, R.color.colorPrimary))
                    day.setTextColor(getColorId(day.context, R.color.colorPrimary))
                    setActualColors()
                    addCalendar.visibility = View.VISIBLE
                }
                item.date.before(Calendar.getInstance().time) -> {
                    setOutdatedColors()
                    addCalendar.visibility = View.GONE
                }
                else -> {
                    month.setTextColor(getColorId(day.context, R.color.colorText))
                    day.setTextColor(getColorId(day.context, R.color.colorText))
                    setActualColors()
                    addCalendar.visibility = View.VISIBLE
                }
            }

            addCalendar.setOnClickListener { listener.onTimelineEventClicked(item) }
            addCalendar.setOnLongClickListener {
                listener.onTimelineEventLongClicked(item)
                true
            }

        }
    }

    override fun getItemCount(): Int {
        return events.size
    }

    inner class ViewHolder (val view: View) : RecyclerView.ViewHolder(view) {
        val month: TextView = view.event_month_text
        val day: TextView = view.event_day_text
        val educationForm: TextView = view.event_education_form_text
        val fundingForm: TextView = view.event_funding_form_text
        val description: TextView = view.event_description_text
        val addCalendar: MaterialButton = view.event_add_to_calendar_button
        val divider: View = view.event_timeline_divider_top
        val divider2: View = view.event_timeline_divider_bottom
        private val dividerCircle: View = view.event_timeline_circle

        lateinit var item: TimelineEvent

        fun setOutdatedColors() {
            month.setTextColor(getColorId(day.context, R.color.colorOutdatedEvent))
            day.setTextColor(getColorId(day.context, R.color.colorOutdatedEvent))
            educationForm.setTextColor(getColorId(day.context, R.color.colorOutdatedEvent))
            fundingForm.setTextColor(getColorId(day.context, R.color.colorOutdatedEvent))
            description.setTextColor(getColorId(day.context, R.color.colorOutdatedEvent))
            divider.setBackgroundColor(getColorId(view.context, R.color.colorOutdatedEvent))
            divider2.setBackgroundColor(getColorId(view.context, R.color.colorOutdatedEvent))
            dividerCircle.backgroundTintList = ContextCompat.getColorStateList(view.context, R.color.colorOutdatedEvent)
        }

        fun setActualColors() {
            educationForm.setTextColor(getColorId(day.context, R.color.colorText))
            fundingForm.setTextColor(getColorId(day.context, R.color.colorText))
            description.setTextColor(getColorId(day.context, R.color.colorText))
            divider.setBackgroundColor(getColorId(view.context, R.color.colorPrimary))
            divider2.setBackgroundColor(getColorId(view.context, R.color.colorPrimary))
            dividerCircle.backgroundTintList = ContextCompat.getColorStateList(view.context, R.color.colorPrimary)
        }
    }

    interface OnTimelineEventsListener {
        fun onTimelineEventClicked(timelineEvent: TimelineEvent)
        fun onTimelineEventLongClicked(timelineEvent: TimelineEvent)
    }
}