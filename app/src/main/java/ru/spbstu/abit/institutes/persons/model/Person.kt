package ru.spbstu.abit.institutes.persons.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Person(
    val url: String? = null,
    val fullName: String? = null,
    var responsibilities: ArrayList<Position>? = null,
    var positions: ArrayList<Position>? = null,
    val email: String? = null,
    val phone: String? = null,
    val location: Location? = null,
    val degreeStringResId: Int? = null,
    val photoUrl: String? = null,
    val scopusUrl: String? = null,
    val rsciUrl: String? = null,
    val description: String? = null
) : Parcelable
