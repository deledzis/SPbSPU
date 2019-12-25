package ru.spbstu.abit.institutes.studyprograms

import android.content.Intent
import android.net.Uri
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import ru.spbstu.abit.R
import ru.spbstu.abit.base.BaseFragmentWithList
import ru.spbstu.abit.data.DefaultRepository
import ru.spbstu.abit.institutes.studyprograms.model.StudyProgram

class StudyProgramsFragment : BaseFragmentWithList(),
    StudyProgramsRecyclerAdapter.OnStudyProgramInteractionListener/*,
    StudySubProgramsRecyclerAdapter.OnStudySubProgramInteractionListener*/ {

    private lateinit var studyProgramList: List<StudyProgram>

    override fun parseArguments() {
        super.parseArguments()

        arguments?.let {
            if (it.containsKey(ARGUMENT_LIST)) {
                studyProgramList = it.getParcelableArrayList(ARGUMENT_LIST) ?: DefaultRepository.defaultStudyProgramsList
            }
        }
    }

    override fun setContent() {
        super.setContent()

        if (!::studyProgramList.isInitialized) {
            studyProgramList = DefaultRepository.defaultStudyProgramsList
        }

        recyclerView.adapter = StudyProgramsRecyclerAdapter(
            studyProgramList,
            this,
            subtractColorId,
            subtractColorLightId
        )
    }

    override fun onStudyProgramSelected(studyProgram: StudyProgram) {
        studyProgram.docs?.let {
            val builder = MaterialAlertDialogBuilder(activity)
            builder.setTitle(getString(R.string.documents))

            val docs = it.keys.toTypedArray()
            builder.setItems(docs) { _, id ->
                for (key in it.keys) {
                    if (key == docs[id]) {
                        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(it[key]))
                        startActivity(browserIntent)
                    }
                }
            }

            val dialog = builder.create()
            dialog.show()
        }
    }

    /*override fun onStudySubProgramSelected(studyProgram: StudyProgram) {
        onStudyProgramSelected(studyProgram)
    }*/

    companion object {
        private const val TAG = "StudyProgramsFragment"

        @JvmStatic
        fun newInstance(): StudyProgramsFragment {
            return StudyProgramsFragment()
        }
    }
}
