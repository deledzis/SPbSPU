package ru.spbstu.abit.timeline

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class TimelineEvent(
    val educationForm: String,
    val fundingForm: String,
    val description: String,
    val date: Date,
    var isSelected: Boolean = false
) : Parcelable