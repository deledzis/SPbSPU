package ru.spbstu.abit.institutes.studyprograms.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class EducationForms(
    val fullTimePlaces: Int = 0,
    val extramuralPlaces: Int = 0,
    val partTimePlaces: Int = 0
) : Parcelable {
    fun isAvailable() = fullTimePlaces > 0 || extramuralPlaces > 0 || partTimePlaces > 0
    fun fullTimeAvailable() = fullTimePlaces > 0
    fun extramuralAvailable() = extramuralPlaces > 0
    fun partTimeAvailable() = partTimePlaces > 0
}