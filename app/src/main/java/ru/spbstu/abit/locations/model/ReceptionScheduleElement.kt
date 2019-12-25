package ru.spbstu.abit.locations.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ReceptionScheduleElement(
    val startWeekDay: WeekDay? = null,
    val endWeekDay: WeekDay? = null,
    val startTime: String? = null,
    val endTime: String? = null
): Parcelable

enum class WeekDay(val abbreviation: String) {
    MONDAY("Пн"),
    TUESDAY("Вт"),
    WEDNESDAY("Ср"),
    THURSDAY("Чт"),
    FRIDAY("Пт"),
    SATURDAY("Сб"),
    SUNDAY("Вс")
}