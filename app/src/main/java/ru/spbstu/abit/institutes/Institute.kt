package ru.spbstu.abit.institutes

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import ru.spbstu.abit.App
import ru.spbstu.abit.R
import ru.spbstu.abit.institutes.departments.Department
import ru.spbstu.abit.institutes.persons.model.Location
import ru.spbstu.abit.institutes.persons.model.Person
import ru.spbstu.abit.institutes.studyprograms.model.StudyProgram

@Parcelize
data class Institute(
    val name: String? = null,
    val abbreviation: String? = null,
    var studyPrograms: ArrayList<StudyProgram>? = null,
    var departments: ArrayList<Department>? = null,
    var directorate: ArrayList<Person>? = null,
    val logoDrawableId: Int = 0,
    val backgroundDrawableId: Int = 0,
    val colorId: Int = 0,
    val colorLightId: Int = 0,
    val url: String? = null,
    val location: Location? = null,
    val emails: ArrayList<Email>? = null,
    val phones: ArrayList<Phone>? = null,
    val descriptionMdFileUrl: String? = null
) : Parcelable

@Parcelize
enum class InstituteEnum(val instituteName: String) : Parcelable {
    IH(App.instance.getString(R.string.institute_ih)),
    IAMM(App.instance.getString(R.string.institute_iamm)),
    IAMT(App.instance.getString(R.string.institute_iamt)),
    ICE(App.instance.getString(R.string.institute_ice)),
    ICST(App.instance.getString(R.string.institute_icst)),
    IETS(App.instance.getString(R.string.institute_iets)),
    IIEP(App.instance.getString(R.string.institute_iiep)),
    IIMET(App.instance.getString(R.string.institute_iimet)),
    IMMET(App.instance.getString(R.string.institute_immet)),
    IPNT(App.instance.getString(R.string.institute_ipnt))
}

@Parcelize
class Phone(
    val number: String,
    val description: String? = null
) : Parcelable

@Parcelize
class Email(
    val address: String,
    val description: String? = null
) : Parcelable