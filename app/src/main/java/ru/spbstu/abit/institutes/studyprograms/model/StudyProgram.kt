package ru.spbstu.abit.institutes.studyprograms.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StudyProgram(
    val code: String? = null,
    val fieldOfStudy: String? = null,
    val name: String? = null,
    val gfEduForms: EducationForms, // government funded
    val sfEduForms: EducationForms, // self funded
    val priceBaseRur: Int = 0,
    val priceOthersRur: Int = 0,
    var subPrograms: List<StudyProgram>? = null,
    var subProgramId: Int? = null,
    var docs: HashMap<String, String>? = null
) : Parcelable