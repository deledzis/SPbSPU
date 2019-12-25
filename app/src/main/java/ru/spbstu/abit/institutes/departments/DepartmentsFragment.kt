package ru.spbstu.abit.institutes.departments

import android.content.Context
import android.util.Log
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import ru.spbstu.abit.R
import ru.spbstu.abit.base.BaseFragmentWithList
import ru.spbstu.abit.data.DefaultRepository

class DepartmentsFragment : BaseFragmentWithList(),
    DepartmentsRecyclerAdapter.OnDepartmentInteractionListener {

    private var listener: OnDepartmentInteractionListener? = null

    private lateinit var departmentsList: List<Department>

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is OnDepartmentInteractionListener) {
            listener = context
        } else {
            Log.e(
                TAG, "[onAttach] $context not implementing OnDepartmentInteractionListener"
            )
        }
    }

    override fun parseArguments() {
        super.parseArguments()

        arguments?.let {
            if (it.containsKey(ARGUMENT_LIST)) {
                departmentsList = it.getParcelableArrayList(ARGUMENT_LIST) ?: DefaultRepository.defaultDepartmentsList
            }
        }
    }

    override fun setContent() {
        super.setContent()

        if (!::departmentsList.isInitialized) {
            departmentsList = DefaultRepository.defaultDepartmentsList
        }

        recyclerView.adapter = DepartmentsRecyclerAdapter(
            departmentsList,
            this
        )
    }

    override fun onDepartmentClick(department: Department) {
        listener?.onDepartmentSelected(
            department, subtractColorId, subtractDrawableId
        )
    }

    override fun onDepartmentLongClick(department: Department) {
        department.url?.let {
            MaterialAlertDialogBuilder(activity)
                .setTitle(getString(R.string.dialog_action_open_site))
                .setMessage(getString(R.string.dialog_site_url, it))
                .setPositiveButton(getString(R.string.yes)) { dialog, _ ->
                    openWebPage(it)
                    dialog.dismiss()
                }
                .setNegativeButton(getString(R.string.no)) { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }

    interface OnDepartmentInteractionListener {
        fun onDepartmentSelected(
            department: Department,
            colorId: Int,
            backgroundDrawableId: Int
        )
    }

    companion object {
        private const val TAG = "DepartmentsFragment"

        @JvmStatic
        fun newInstance(): DepartmentsFragment {
            return DepartmentsFragment()
        }
    }
}