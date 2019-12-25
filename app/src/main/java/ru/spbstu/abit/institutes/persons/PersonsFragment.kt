package ru.spbstu.abit.institutes.persons

import android.content.Context
import android.util.Log
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import ru.spbstu.abit.R
import ru.spbstu.abit.base.BaseFragmentWithList
import ru.spbstu.abit.data.DefaultRepository
import ru.spbstu.abit.institutes.persons.model.Person

class PersonsFragment : BaseFragmentWithList(),
    PersonsRecyclerAdapter.OnPersonInteractionListener {

    private var listener: OnPersonInteractionListener? = null

    private lateinit var personsList: List<Person>

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is OnPersonInteractionListener) {
            listener = context
        } else {
            Log.e(
                TAG, "[onAttach] $context not implementing OnPersonInteractionListener"
            )
        }
    }

    override fun parseArguments() {
        super.parseArguments()

        arguments?.let {
            if (it.containsKey(ARGUMENT_LIST)) {
                personsList = it.getParcelableArrayList(ARGUMENT_LIST) ?: DefaultRepository.defaultPersonsList
            }
        }
    }

    override fun setContent() {
        super.setContent()

        if (!::personsList.isInitialized) {
            personsList = DefaultRepository.defaultPersonsList
        }

        recyclerView.adapter = PersonsRecyclerAdapter(
            personsList,
            this
        )
    }

    override fun onPersonClick(person: Person) {
        listener?.onPersonSelected(
            person, subtractColorId, subtractDrawableId
        )
    }

    override fun onPersonLongClick(person: Person) {
        person.url?.let {
            MaterialAlertDialogBuilder(activity)
                .setTitle(getString(R.string.dialog_action_open_site))
                .setMessage(getString(R.string.dialog_site_url, it))
                .setPositiveButton(getString(R.string.yes)) { dialog, _ ->
                    openWebPage(it)
                    dialog.dismiss()
                }
                .setNegativeButton(getString(R.string.no)) { dialog, _->
                    dialog.dismiss()
                }
                .show()
        }
    }

    interface OnPersonInteractionListener {
        fun onPersonSelected(
            person: Person,
            colorId: Int,
            backgroundDrawableId: Int
        )
    }

    companion object {
        private const val TAG = "PersonsFragment"

        @JvmStatic
        fun newInstance(): PersonsFragment {
            return PersonsFragment()
        }
    }
}