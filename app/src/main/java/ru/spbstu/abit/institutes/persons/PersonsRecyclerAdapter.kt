package ru.spbstu.abit.institutes.persons

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_item_person.view.*
import ru.spbstu.abit.App
import ru.spbstu.abit.R
import ru.spbstu.abit.institutes.ICardActions
import ru.spbstu.abit.institutes.persons.model.Person

class PersonsRecyclerAdapter (
    private val persons: List<Person>,
    private val listener: OnPersonInteractionListener?
) : RecyclerView.Adapter<PersonsRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item_person,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val person = persons[position]
        holder.init(person)
    }

    override fun getItemCount(): Int {
        return persons.size
    }

    inner class ViewHolder (val view: View) : RecyclerView.ViewHolder(view) {
        private val separator: View = view.person_separator
        private val photo: ImageView = view.person_photo
        private val name: TextView = view.person_name_text
        private val respTitle: TextView = view.person_responses_title_text
        private val resp: TextView = view.person_response_text
        private val phone: Button = view.person_phone_button
        private val email: Button = view.person_email_button

        lateinit var item: Person

        fun init(person: Person) {
            item = person

            Glide.with(view)
                .load(item.photoUrl)
                .placeholder(R.drawable.placeholder_profile_photo_round)
                .apply(com.bumptech.glide.request.RequestOptions.circleCropTransform())
                .into(photo)

            name.text = item.fullName

            item.responsibilities?.get(0)?.let {
                respTitle.visibility = View.VISIBLE
                resp.visibility = View.VISIBLE

                resp.text = App.instance.getString(
                    R.string.position_valued,
                    it.positionName,
                    it.institute?.instituteName
                )
            } ?: apply {
                item.positions?.get(0)?.let {
                    respTitle.visibility = View.VISIBLE
                    resp.visibility = View.VISIBLE

                    resp.text = App.instance.getString(
                        R.string.position_valued,
                        it.positionName,
                        it.department?.title
                    )
                } ?: apply {
                    respTitle.visibility = View.GONE
                    resp.visibility = View.GONE
                }
            }

            view.setOnClickListener { listener?.onPersonClick(item) }
            view.setOnLongClickListener {
                listener?.onPersonLongClick(item)
                true
            }

            separator.visibility = if (!item.phone.isNullOrBlank() || !item.email.isNullOrBlank()) {
                View.VISIBLE
            } else {
                View.GONE
            }

            item.phone?.apply {
                phone.visibility = View.VISIBLE
                phone.setOnClickListener { listener?.onPhoneClick(this) }
                phone.setOnLongClickListener {
                    listener?.onPhoneLongClick(this)
                    true
                }
            } ?: apply { phone.visibility = View.GONE }

            item.email?.apply {
                email.visibility = View.VISIBLE
                email.setOnClickListener { listener?.onEmailClick(this) }
                email.setOnLongClickListener {
                    listener?.onEmailLongClick(this)
                    true
                }
            } ?: apply { email.visibility = View.GONE }
        }
    }

    interface OnPersonInteractionListener : ICardActions {
        fun onPersonClick(person: Person)
        fun onPersonLongClick(person: Person)
    }
}