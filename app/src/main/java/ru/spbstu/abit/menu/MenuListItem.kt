package ru.spbstu.abit.menu

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MenuListItem<T : Parcelable>(
    val title: String? = null,
    val iconId: Int = 0,
    val iconColorId: Int = 0,
    val iconBackgroundColorId: Int = 0,
    val action: String? = null,
    var obj: T? = null
) : Parcelable