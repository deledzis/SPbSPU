package ru.spbstu.abit.institutes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import kotlinx.android.synthetic.main.list_item_institute.view.*
import ru.spbstu.abit.App
import ru.spbstu.abit.R

class StructureInstitutesRecyclerAdapter(
    private val institutes: List<Institute>,
    private val listener: OnStructureInstituteInteractionListener
) : RecyclerView.Adapter<StructureInstitutesRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.list_item_institute,
            parent,
            false
        )

        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.item = institutes[position]
        holder.bind()
    }

    override fun getItemCount(): Int {
        return institutes.size
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val holderView: ConstraintLayout = view.institute_holder
        private val name: TextView = view.institute_name
        private val studyPrograms: TextView = view.institute_programs
        private val backgroundImage: ImageView = view.substrate_image

        lateinit var item: Institute

        fun bind() {
            name.text = item.name
            studyPrograms.text = App.instance.getString(
                R.string.study_programs_valued,
                item.studyPrograms?.size ?: 0
            )
            Glide.with(App.instance)
                .load(item.backgroundDrawableId)
                .transition(withCrossFade(DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(backgroundImage)

            holderView.setOnClickListener { listener.onStructureInstituteClicked(item) }
            holderView.setOnLongClickListener {
                listener.onStructureInstituteLongClicked(item)
                true
            }
        }
    }

    interface OnStructureInstituteInteractionListener {
        fun onStructureInstituteClicked(institute: Institute)
        fun onStructureInstituteLongClicked(institute: Institute)
    }
}
