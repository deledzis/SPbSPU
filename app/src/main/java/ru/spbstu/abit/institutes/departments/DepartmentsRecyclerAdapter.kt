package ru.spbstu.abit.institutes.departments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_department.view.*
import ru.spbstu.abit.App
import ru.spbstu.abit.R
import ru.spbstu.abit.institutes.ICardActions

class DepartmentsRecyclerAdapter (
    private val departments: List<Department>,
    private val listener: OnDepartmentInteractionListener?
) : RecyclerView.Adapter<DepartmentsRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item_department,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val department = departments[position]
        holder.init(department)
    }

    override fun getItemCount(): Int {
        return departments.size
    }

    inner class ViewHolder (val view: View) : RecyclerView.ViewHolder(view) {
        private val separator: View = view.department_separator
        //private val logo: ImageView = view.department_logo
        private val title: TextView = view.department_title_text
        private val directorate: TextView = view.department_directorate_text
        private val subs: TextView = view.department_subs_text
        private val url: Button = view.department_url_button
        private val phone: Button = view.department_phone_button
        private val email: Button = view.department_email_button

        lateinit var item: Department

        fun init(department: Department) {
            item = department

            /*var requestOptions = RequestOptions()
            requestOptions = requestOptions.transform(RoundedCorners(40))
            Glide.with(view)
                .load(department.logoUrl)
                .placeholder(logoRes)
                .apply(requestOptions)
                .into(logo)*/

            title.text = department.title
            department.directorate?.let {
                directorate.visibility = View.VISIBLE
                directorate.text = App.instance.getString(R.string.directorate_persons_valued, it.size)
            } ?: apply { directorate.visibility = View.GONE }
            department.subDepartments?.let {
                subs.visibility = View.VISIBLE
                subs.text = App.instance.getString(R.string.subdepartments_valued, it.size)
            } ?: apply { subs.visibility = View.GONE }

            view.setOnClickListener { listener?.onDepartmentClick(item) }
            view.setOnLongClickListener {
                listener?.onDepartmentLongClick(item)
                true
            }

            separator.visibility = if (!department.phone.isNullOrBlank() || !department.email.isNullOrBlank()) {
                View.VISIBLE
            } else {
                View.GONE
            }

            department.phone?.apply {
                phone.visibility = View.VISIBLE
                phone.setOnClickListener { listener?.onPhoneClick(this) }
                phone.setOnLongClickListener {
                    listener?.onPhoneLongClick(this)
                    true
                }
            } ?: apply { phone.visibility = View.GONE }

            department.email?.apply {
                email.visibility = View.VISIBLE
                email.setOnClickListener { listener?.onEmailClick(this) }
                email.setOnLongClickListener {
                    listener?.onEmailLongClick(this)
                    true
                }
            } ?: apply { email.visibility = View.GONE }

            department.url?.apply {
                url.visibility = View.VISIBLE
                url.setOnClickListener { listener?.onUrlClick(this) }
                url.setOnLongClickListener {
                    listener?.onUrlLongClick(this)
                    true
                }
            } ?: apply { url.visibility = View.GONE }
        }
    }

    interface OnDepartmentInteractionListener : ICardActions {
        fun onDepartmentClick(department: Department)
        fun onDepartmentLongClick(department: Department)
    }
}

