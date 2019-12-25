package ru.spbstu.abit.institutes.studyprograms

import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexboxLayout
import kotlinx.android.synthetic.main.list_item_study_subprogram.view.*
import ru.spbstu.abit.R
import ru.spbstu.abit.institutes.studyprograms.model.StudyProgram
import ru.spbstu.abit.App
import ru.spbstu.abit.util.getColorId
import ru.spbstu.abit.util.setColorFilter
import java.text.NumberFormat
import java.util.*

class StudySubProgramsRecyclerAdapter (
    private val studyPrograms: List<StudyProgram>,
    private val listener: OnStudySubProgramInteractionListener,
    private val colorId: Int,
    private val colorLightId: Int
) : RecyclerView.Adapter<StudySubProgramsRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.list_item_study_subprogram,
            parent,
            false
        )

        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val studyProgram = studyPrograms[position]

        with(holder) {
            item = studyProgram
            name.text = studyProgram.name

            studyProgram.gfEduForms.let {
                if (it.isAvailable()) {
                    governmentFundLayout.visibility = View.VISIBLE
                    if (it.fullTimeAvailable()) {
                        governmentFundO.visibility = View.VISIBLE
                        governmentFundO.text = App.instance.getString(R.string.full_time_valued, it.fullTimePlaces)
                    } else {
                        governmentFundO.visibility = View.GONE
                    }
                    if (it.extramuralAvailable()) {
                        governmentFundZ.visibility = View.VISIBLE
                        governmentFundZ.text = App.instance.getString(R.string.extramural_valued, it.extramuralPlaces)
                    } else {
                        governmentFundZ.visibility = View.GONE
                    }
                    if (it.partTimeAvailable()) {
                        governmentFundOZ.visibility = View.VISIBLE
                        governmentFundOZ.text = App.instance.getString(R.string.part_time_valued, it.partTimePlaces)
                    } else {
                        governmentFundOZ.visibility = View.GONE
                    }
                } else {
                    governmentFundLayout.visibility = View.GONE
                }
            }

            studyProgram.sfEduForms.let {
                if (it.isAvailable()) {
                    selfFundLayout.visibility = View.VISIBLE
                    if (it.fullTimeAvailable()) {
                        selfFundO.visibility = View.VISIBLE
                        selfFundO.text = App.instance.getString(R.string.full_time_valued, it.fullTimePlaces)
                    } else {
                        selfFundO.visibility = View.GONE
                    }
                    if (it.extramuralAvailable()) {
                        selfFundZ.visibility = View.VISIBLE
                        selfFundZ.text = App.instance.getString(R.string.extramural_valued, it.extramuralPlaces)
                    } else {
                        selfFundZ.visibility = View.GONE
                    }
                    if (it.partTimeAvailable()) {
                        selfFundOZ.visibility = View.VISIBLE
                        selfFundOZ.text = App.instance.getString(R.string.part_time_valued, it.partTimePlaces)
                    } else {
                        selfFundOZ.visibility = View.GONE
                    }
                } else {
                    selfFundLayout.visibility = View.GONE
                }
            }

            price.visibility = if (item.priceBaseRur > 0) View.VISIBLE else View.GONE
            // printing formatted price
            val locale = Locale("ru", "RU")
            val numberFormat = NumberFormat.getCurrencyInstance(locale)
            val moneyString = numberFormat.format(item.priceBaseRur)
            price.text = moneyString

            item.subProgramId?.let {
                id.visibility = View.VISIBLE
                id.text = it.toString()
            } ?: let { id.visibility = View.GONE }

            setTextViewBackgroundsColor(getColorId(name.context, colorLightId))
            setTextViewTextsColor(getColorId(name.context, colorId))

            view.setOnClickListener { listener.onStudySubProgramSelected(studyProgram) }
        }
    }

    override fun getItemCount(): Int {
        return studyPrograms.size
    }

    inner class ViewHolder (val view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.program_subitem_name_text
        val governmentFundLayout: FlexboxLayout = view.program_subitem_gfund_layout
        val governmentFund: TextView = view.program_subitem_gfund_text
        val governmentFundO: TextView = view.program_subitem_gfund_o_text
        val governmentFundZ: TextView = view.program_subitem_gfund_z_text
        val governmentFundOZ: TextView = view.program_subitem_gfund_oz_text
        val selfFundLayout: FlexboxLayout = view.program_subitem_sfund_layout
        val selfFund: TextView = view.program_subitem_sfund_text
        val selfFundO: TextView = view.program_subitem_sfund_o_text
        val selfFundZ: TextView = view.program_subitem_sfund_z_text
        val selfFundOZ: TextView = view.program_subitem_sfund_oz_text
        val price: TextView = view.program_subitem_price_text
        val id: TextView = view.program_subitem_id_text

        lateinit var item: StudyProgram

        fun setTextViewTextsColor(colorId: Int) {
            governmentFund.setTextColor(colorId)
            governmentFundO.setTextColor(colorId)
            governmentFundZ.setTextColor(colorId)
            governmentFundOZ.setTextColor(colorId)

            selfFund.setTextColor(colorId)
            selfFundO.setTextColor(colorId)
            selfFundZ.setTextColor(colorId)
            selfFundOZ.setTextColor(colorId)

            id.setTextColor(colorId)
        }

        fun setTextViewBackgroundsColor(colorId: Int) {
            governmentFund.background.setColorFilter(colorId)
            selfFund.background.setColorFilter(colorId)
            var drawable = governmentFundO.background as GradientDrawable
            drawable.setStroke(7, colorId)
            drawable = governmentFundZ.background as GradientDrawable
            drawable.setStroke(7, colorId)
            drawable = governmentFundOZ.background as GradientDrawable
            drawable.setStroke(7, colorId)

            drawable = selfFundO.background as GradientDrawable
            drawable.setStroke(7, colorId)
            drawable = selfFundZ.background as GradientDrawable
            drawable.setStroke(7, colorId)
            drawable = selfFundOZ.background as GradientDrawable
            drawable.setStroke(7, colorId)

            drawable = id.background as GradientDrawable
            drawable.setColor(colorId)
        }
    }

    interface OnStudySubProgramInteractionListener {
        fun onStudySubProgramSelected(studyProgram: StudyProgram)
    }
}


