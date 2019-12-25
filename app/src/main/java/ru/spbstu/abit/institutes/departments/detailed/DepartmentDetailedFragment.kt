package ru.spbstu.abit.institutes.departments.detailed

import android.os.Handler
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import ru.spbstu.abit.R
import ru.spbstu.abit.base.BaseFragmentWithList
import ru.spbstu.abit.data.DefaultRepository
import ru.spbstu.abit.institutes.departments.Department
import ru.spbstu.abit.institutes.persons.model.Person
import us.feras.mdv.MarkdownView

class DepartmentDetailedFragment : BaseFragmentWithList(),
    DepartmentDetailedRecyclerAdapter.OnDepartmentDetailedInteractionListener {

    private lateinit var department: Department

    override fun parseArguments() {
        super.parseArguments()

        arguments?.let {
            if (it.containsKey(ARGUMENT_DEPARTMENT)) {
                department = it.getParcelable(ARGUMENT_DEPARTMENT) ?: DefaultRepository.defaultDepartmentsList[0]
            }
        }
    }

    override fun setContent() {
        super.setContent()

        if (!::department.isInitialized) {
            department = DefaultRepository.defaultDepartmentsList[0]
        }

        recyclerView.adapter =
            DepartmentDetailedRecyclerAdapter(
                department,
                this
            )
    }

    override fun onReadMoreClick(department: Department) {
        closeBottomSheet()
        val title = bottomSheet.findViewById<TextView>(R.id.bottom_sheet_main_menu_title_text)
        val description = bottomSheet.findViewById<MarkdownView>(R.id.bottom_sheet_main_menu_description)
        title.text = getString(R.string.department_content_description)
        description.loadMarkdownFile(department.descriptionMdFileUrl)
        Handler().postDelayed({ bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED) }, 250)
    }

    override fun onPersonClick(person: Person) {}

    override fun onPersonLongClick(person: Person) {
        person.url?.let {
            MaterialAlertDialogBuilder(activity)
                .setTitle(getString(R.string.dialog_action_open_site))
                .setMessage(getString(R.string.dialog_site_url, it))
                .setPositiveButton(getString(R.string.yes)) { dialog, _ ->
                    onUrlClick(it)
                    dialog.dismiss()
                }
                .setNegativeButton(getString(R.string.no)) { dialog, _->
                    dialog.dismiss()
                }
                .show()
        }
    }

    companion object {
        private const val TAG = "DepartmentDetailedFragment"

        const val ARGUMENT_DEPARTMENT = "department"

        @JvmStatic
        fun newInstance(): DepartmentDetailedFragment {
            return DepartmentDetailedFragment()
        }
    }
}