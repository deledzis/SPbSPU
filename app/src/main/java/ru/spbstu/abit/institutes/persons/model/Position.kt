package ru.spbstu.abit.institutes.persons.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import ru.spbstu.abit.institutes.departments.Department
import ru.spbstu.abit.institutes.InstituteEnum

@Parcelize
data class Position(
    val positionName: String? = null,
    val institute: InstituteEnum? = null,
    val department: Department? = null
) : Parcelable
