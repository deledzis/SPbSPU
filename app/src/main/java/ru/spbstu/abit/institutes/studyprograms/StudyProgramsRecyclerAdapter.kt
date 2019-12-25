package ru.spbstu.abit.institutes.studyprograms

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.list_item_study_program.view.*
import ru.spbstu.abit.R
import ru.spbstu.abit.institutes.studyprograms.model.StudyProgram
import ru.spbstu.abit.App
import ru.spbstu.abit.util.getColorId
import ru.spbstu.abit.util.getColorStateListId

class StudyProgramsRecyclerAdapter (
    private val studyPrograms: List<StudyProgram>,
    private val listener: OnStudyProgramInteractionListener,
    private val colorId: Int,
    private val colorLightId: Int
) : RecyclerView.Adapter<StudyProgramsRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.list_item_study_program,
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
            code.text = studyProgram.code

            studyProgram.subPrograms?.let {
                if (it.isNotEmpty()) {
                    subDirections.visibility = View.VISIBLE
                    subDirections.text = App.instance.getString(R.string.subprograms_valued, it.size)
                    constraintArrowToSubs()
                } else {
                    subDirections.visibility = View.GONE
                    constraintArrowToChips()
                }
            } ?: let {
                subDirections.visibility = View.GONE
                constraintArrowToChips()
            }

            paintChips(getColorId(App.instance, colorId), colorLightId)

            layout.setOnClickListener { listener.onStudyProgramSelected(studyProgram) }
            arrow.setOnClickListener { listener.onStudyProgramSelected(studyProgram) }
        }
    }

    override fun getItemCount(): Int {
        return studyPrograms.size
    }

    inner class ViewHolder (val view: View) : RecyclerView.ViewHolder(view) {
        val layout: ConstraintLayout = view.program_item_layout
        val name: TextView = view.program_item_name_text
        val code: TextView = view.program_item_code_text
        val subDirections: TextView = view.program_item_available_subdirections_text
        val ege1: Chip = view.program_item_chip_ege1
        val ege2: Chip = view.program_item_chip_ege2
        val ege3: Chip = view.program_item_chip_ege3
        val arrow: ImageView = view.program_item_detailed_arrow

        lateinit var item: StudyProgram

        fun paintChips(colorId: Int, colorLightId: Int) {
            ege1.setTextColor(colorId)
            ege2.setTextColor(colorId)
            ege3.setTextColor(colorId)

            ege1.chipBackgroundColor = view.context.getColorStateListId(colorLightId)
            ege2.chipBackgroundColor = view.context.getColorStateListId(colorLightId)
            ege3.chipBackgroundColor = view.context.getColorStateListId(colorLightId)
        }

        fun constraintArrowToChips() {
            val constraintSet = ConstraintSet()
            constraintSet.clone(layout)
            constraintSet.connect(
                R.id.program_item_detailed_arrow,
                ConstraintSet.TOP,
                R.id.program_item_chip_group_ege,
                ConstraintSet.TOP,
                0
            )
            constraintSet.connect(
                R.id.program_item_detailed_arrow,
                ConstraintSet.BOTTOM,
                R.id.program_item_chip_group_ege,
                ConstraintSet.BOTTOM,
                0
            )
            constraintSet.applyTo(layout)
        }

        fun constraintArrowToSubs() {
            val constraintSet = ConstraintSet()
            constraintSet.clone(layout)
            constraintSet.connect(
                R.id.program_item_detailed_arrow,
                ConstraintSet.TOP,
                R.id.program_item_available_subdirections_text,
                ConstraintSet.TOP,
                0
            )
            constraintSet.connect(
                R.id.program_item_detailed_arrow,
                ConstraintSet.BOTTOM,
                R.id.program_item_available_subdirections_text,
                ConstraintSet.BOTTOM,
                0
            )
            constraintSet.applyTo(layout)
        }
    }

    interface OnStudyProgramInteractionListener {
        fun onStudyProgramSelected(studyProgram: StudyProgram)
    }
}

