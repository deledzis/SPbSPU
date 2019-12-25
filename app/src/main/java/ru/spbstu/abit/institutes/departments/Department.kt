package ru.spbstu.abit.institutes.departments

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import ru.spbstu.abit.institutes.persons.model.Location
import ru.spbstu.abit.institutes.persons.model.Person
import java.util.*

@Parcelize
data class Department(
    val title: String? = null,
    val url: String? = null,
    val email: String? = null,
    val phone: String? = null,
    val location: Location? = null,
    var subDepartments: ArrayList<Department>? = null,
    var directorate: ArrayList<Person>? = null,
    val logoUrl: String? = null,
    val info: String? = null,
    val descriptionMdFileUrl: String? = null
) : Parcelable