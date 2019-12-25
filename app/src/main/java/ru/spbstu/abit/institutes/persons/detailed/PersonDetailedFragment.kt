package ru.spbstu.abit.institutes.persons.detailed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.fragment_person_detailed.view.*
import ru.spbstu.abit.MainActivity
import ru.spbstu.abit.R
import ru.spbstu.abit.base.BaseFragmentWithScrollableAppBar
import ru.spbstu.abit.data.DefaultRepository
import ru.spbstu.abit.institutes.persons.model.Person
import ru.spbstu.abit.locations.model.Building

class PersonDetailedFragment : BaseFragmentWithScrollableAppBar() {
    private lateinit var viewModel: PersonDetailedViewModel

    private lateinit var person: Person

    private lateinit var photo: ImageView
    private lateinit var respTitle: TextView
    private lateinit var resp: TextView
    private lateinit var posTitle: TextView
    private lateinit var pos: TextView
    private lateinit var degreeTitle: TextView
    private lateinit var degree: TextView
    private lateinit var map: Button
    private lateinit var phone: Button
    private lateinit var email: Button
    private lateinit var url: Button

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PersonDetailedViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainView = inflater.inflate(
            R.layout.fragment_person_detailed,
            container,
            false
        )

        parseArguments()
        initViews()
        initToolbar()
        setContent()

        return mainView
    }

    override fun parseArguments() {
        super.parseArguments()

        arguments?.let {
            if (it.containsKey(ARGUMENT_PERSON)) {
                person = it.getParcelable(ARGUMENT_PERSON) ?: DefaultRepository.defaultPersonsList[0]
            }
        }
    }

    override fun initViews() {
        super.initViews()

        with(mainView) {
            photo = person_detailed_profile_photo
            posTitle = person_detailed_position_title
            pos = person_detailed_position_content
            respTitle = person_detailed_resps_title
            resp = person_detailed_resps_content
            degreeTitle = person_detailed_degree_title
            degree = person_detailed_degree_content
            map = person_detailed_contacts_map
            phone = person_detailed_contacts_phone
            email = person_detailed_contacts_email
            url = person_detailed_contacts_url
        }
    }

    override fun initToolbar() {
        activity.setSupportActionBar(toolbar)
        setToolbarSpannableTitle(
            subtractColorId,
            subtractDrawableId
        )
        activity.toggleToolbarBackButton(MainActivity.ACTIVE_TOOLBAR_BACK_BUTTON)
    }

    override fun setContent() {
        super.setContent()
        if (!::person.isInitialized) {
            person = DefaultRepository.defaultPersonsList[0]
        }

        Glide.with(mainView)
            .load(person.photoUrl)
            .placeholder(R.drawable.placeholder_profile_photo_round)
            .apply(RequestOptions.circleCropTransform())
            .into(photo)

        person.responsibilities?.get(0)?.let {
            respTitle.visibility = View.VISIBLE
            resp.visibility = View.VISIBLE

            resp.text = getString(
                R.string.position_valued,
                it.positionName,
                it.institute?.instituteName
            )
        } ?: apply {
            respTitle.visibility = View.GONE
            resp.visibility = View.GONE
        }

        person.positions?.get(0)?.let {
            posTitle.visibility = View.VISIBLE
            pos.visibility = View.VISIBLE

            pos.text = getString(
                R.string.position_valued,
                it.positionName,
                it.department?.title
            )
        } ?: apply {
            posTitle.visibility = View.GONE
            pos.visibility = View.GONE
        }

        person.degreeStringResId?.let {
            degreeTitle.visibility = View.VISIBLE
            degree.visibility = View.VISIBLE
            degree.text = getString(it)
        } ?: apply {
            degreeTitle.visibility = View.GONE
            degree.visibility = View.GONE
        }

        person.location?.apply {
            map.visibility = View.VISIBLE
            map.text = this.addressTitle
            map.setOnClickListener { onShowOnMapClick(this.building) }
        } ?: apply { map.visibility = View.GONE }

        person.email?.apply {
            email.visibility = View.VISIBLE
            email.text = this
            email.setOnClickListener { onEmailClick(this) }
            email.setOnLongClickListener {
                onEmailLongClick(this)
                true
            }
        } ?: apply { email.visibility = View.GONE }

        person.phone?.apply {
            phone.visibility = View.VISIBLE
            phone.text = this
            phone.setOnClickListener { onPhoneClick(this) }
            phone.setOnLongClickListener {
                onPhoneLongClick(this)
                true
            }
        } ?: apply { phone.visibility = View.GONE }

        person.url?.apply {
            url.visibility = View.VISIBLE
            url.setOnClickListener { onUrlClick(this) }
            url.setOnLongClickListener {
                onUrlLongClick(this)
                true
            }
        } ?: apply { url.visibility = View.GONE }
    }

    override fun onBackPressed(): Boolean {
        return false
    }

    fun onUrlClick(url: String?) {
        url?.let { openWebPage(it) }
    }

    fun onUrlLongClick(url: String?) {
        url?.let {
            MaterialAlertDialogBuilder(activity)
                .setTitle(getString(R.string.dialog_action_open_site))
                .setMessage(getString(R.string.dialog_site_url, it))
                .setPositiveButton(getString(R.string.yes)) { dialog, _ ->
                    onUrlClick(it)
                    dialog.dismiss()
                }
                .setNegativeButton(getString(R.string.no)) { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }

    fun onPhoneClick(phone: String?) {
        phone?.let {
            dialPhoneNumber(it)
        }
    }

    fun onPhoneLongClick(phone: String?) {
        phone?.let {
            MaterialAlertDialogBuilder(activity)
                .setTitle(getString(R.string.dialog_action_dial))
                .setMessage(getString(R.string.dialog_phone_number, it))
                .setPositiveButton(getString(R.string.yes)) { dialog, _ ->
                    onPhoneClick(it)
                    dialog.dismiss()
                }
                .setNegativeButton(getString(R.string.no)) { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }

    fun onEmailClick(email: String?) {
        email?.let {
            composeEmail(it, "")
        }
    }

    fun onEmailLongClick(email: String?) {
        email?.let {
            MaterialAlertDialogBuilder(activity)
                .setTitle(getString(R.string.dialog_action_write_email))
                .setMessage(getString(R.string.dialog_email, it))
                .setPositiveButton(getString(R.string.yes)) { dialog, _ ->
                    onEmailClick(it)
                    dialog.dismiss()
                }
                .setNegativeButton(getString(R.string.no)) { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }

    fun onShowOnMapClick(building: Building?) {
        building?.let {
            activity.showOnMap(it)
        }
    }

    companion object {
        private const val TAG = "PersonDetailedFragment"

        const val ARGUMENT_PERSON = "person"

        fun newInstance() = PersonDetailedFragment()
    }
}
