package ru.spbstu.abit.institutes.persons.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import ru.spbstu.abit.locations.model.Building

@Parcelize
data class Location(
    val addressTitle: String,
    val building: Building
): Parcelable