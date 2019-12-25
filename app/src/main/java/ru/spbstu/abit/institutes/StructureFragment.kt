package ru.spbstu.abit.institutes

import android.content.Context
import android.util.Log
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import ru.spbstu.abit.MainActivity.Companion.INACTIVE_TOOLBAR_BACK_BUTTON
import ru.spbstu.abit.R
import ru.spbstu.abit.base.BaseFragmentWithList
import ru.spbstu.abit.data.DefaultRepository
import java.util.*

class StructureFragment : BaseFragmentWithList(),
    StructureInstitutesRecyclerAdapter.OnStructureInstituteInteractionListener {

    private var listener: OnStructureInstituteInteractionListener? = null

    private lateinit var institutes: ArrayList<Institute>

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is OnStructureInstituteInteractionListener) {
            listener = context
        } else {
            Log.e(
                TAG, "[onAttach] $context not implementing OnStructureInstituteInteractionListener"
            )
        }
    }

    override fun initToolbar() {
        activity.setSupportActionBar(toolbar)
        activity.toggleToolbarBackButton(INACTIVE_TOOLBAR_BACK_BUTTON)
        setToolbarSpannableTitle(R.color.colorDark, 0)
    }

    override fun setContent() {
        super.setContent()

        institutes = DefaultRepository.getDefaultStructureInstitutesList(activity)

        recyclerView.adapter = StructureInstitutesRecyclerAdapter(institutes, this)
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onStructureInstituteClicked(institute: Institute) {
        listener?.onStructureInstituteSelected(institute)
    }

    override fun onStructureInstituteLongClicked(institute: Institute) {
        institute.url?.let {
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

    interface OnStructureInstituteInteractionListener {
        fun onStructureInstituteSelected(institute: Institute)
    }

    companion object {
        private const val TAG = "StructureFragment"

        @JvmStatic
        fun newInstance(): StructureFragment {
            return StructureFragment()
        }
    }
}