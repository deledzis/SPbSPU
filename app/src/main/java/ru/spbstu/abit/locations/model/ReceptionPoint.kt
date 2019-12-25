package ru.spbstu.abit.locations.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ReceptionPoint(
    val instituteAbbreviation: String? = null,
    val room: Int = 0
): Parcelable