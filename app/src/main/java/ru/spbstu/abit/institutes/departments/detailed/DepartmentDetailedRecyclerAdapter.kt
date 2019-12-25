package ru.spbstu.abit.institutes.departments.detailed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_item_department_detailed_contacts.view.*
import kotlinx.android.synthetic.main.list_item_department_detailed_description.view.*
import kotlinx.android.synthetic.main.list_item_person.view.*
import ru.spbstu.abit.App
import ru.spbstu.abit.R
import ru.spbstu.abit.institutes.ICardActions
import ru.spbstu.abit.institutes.departments.Department
import ru.spbstu.abit.institutes.persons.model.Person

class DepartmentDetailedRecyclerAdapter(
    private val department: Department,
    private val listener: OnDepartmentDetailedInteractionListener?
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return when(position) {
            0 -> ABOUT_CARD
            1 -> CONTACTS_CARD
            else -> PERSON_CARD
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        when(viewType) {
            ABOUT_CARD -> return ViewHolderDescription(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.list_item_department_detailed_description,
                    parent,
                    false
                )
            )
            CONTACTS_CARD -> return ViewHolderContacts(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.list_item_department_detailed_contacts,
                    parent,
                    false
                )
            )
            else -> return ViewHolderPerson(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.list_item_person,
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        when(holder) {
            is ViewHolderDescription -> holder.init()
            is ViewHolderContacts -> holder.init()
            else -> {
                department.directorate?.let {
                    val person = it[position - 2]
                    (holder as ViewHolderPerson).init(person)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        var count = (department.directorate?.size ?: 0) + 1 // contacts
        if (!department.info.isNullOrBlank() || !department.descriptionMdFileUrl.isNullOrBlank()) {
            count++
        }
        return count
    }

    inner class ViewHolderDescription (val view: View) : RecyclerView.ViewHolder(view) {
        private val descriptionPreview: TextView = view.department_details_description_text
        private val readMore: Button = view.department_details_description_read_more
        private val expand: ImageView = view.department_details_description_expand

        private val item: Department = department

        fun init() {
            if (item.info.isNullOrBlank() && item.descriptionMdFileUrl.isNullOrBlank()) {
                view.visibility = View.GONE
            } else {
                view.visibility = View.VISIBLE
            }
            item.info?.apply {
                descriptionPreview.text = this
            }

            item.descriptionMdFileUrl?.apply {
                readMore.visibility = View.VISIBLE
                readMore.setOnClickListener { listener?.onReadMoreClick(item) }
            }
        }
    }

    inner class ViewHolderContacts (val view: View) : RecyclerView.ViewHolder(view) {
        private val map: Button = view.department_details_contacts_map
        private val email: Button = view.department_details_contacts_email
        private val phone: Button = view.department_details_contacts_phone
        private val url: Button = view.department_details_contacts_url

        private val item: Department = department

        fun init() {
            item.location?.apply {
                map.visibility = View.VISIBLE
                map.text = this.addressTitle
                map.setOnClickListener { listener?.onShowOnMapClick(this.building) }
            } ?: apply { map.visibility = View.GONE }

            item.email?.apply {
                email.visibility = View.VISIBLE
                email.text = this
                email.setOnClickListener { listener?.onEmailClick(this) }
                email.setOnLongClickListener {
                    listener?.onEmailLongClick(this)
                    true
                }
            } ?: apply { email.visibility = View.GONE }

            item.phone?.apply {
                phone.visibility = View.VISIBLE
                phone.text = this
                phone.setOnClickListener { listener?.onPhoneClick(this) }
                phone.setOnLongClickListener {
                    listener?.onPhoneLongClick(this)
                    true
                }
            } ?: apply { phone.visibility = View.GONE }

            item.url?.apply {
                url.visibility = View.VISIBLE
                url.setOnClickListener { listener?.onUrlClick(this) }
                url.setOnLongClickListener {
                    listener?.onUrlLongClick(this)
                    true
                }
            } ?: apply { url.visibility = View.GONE }
        }
    }

    inner class ViewHolderPerson (val view: View) : RecyclerView.ViewHolder(view) {
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

    interface OnDepartmentDetailedInteractionListener : ICardActions {
        fun onReadMoreClick(department: Department)
        fun onPersonClick(person: Person)
        fun onPersonLongClick(person: Person)
    }

    companion object {
        private const val ABOUT_CARD = 0
        private const val CONTACTS_CARD = 1
        private const val PERSON_CARD = 2
    }
}