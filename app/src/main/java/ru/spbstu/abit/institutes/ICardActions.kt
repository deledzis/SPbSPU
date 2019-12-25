package ru.spbstu.abit.institutes

import ru.spbstu.abit.locations.model.Building

interface ICardActions {
    fun onUrlClick(url: String?)
    fun onUrlLongClick(url: String?)
    fun onPhoneClick(phone: String?)
    fun onPhoneLongClick(phone: String?)
    fun onEmailClick(email: String?)
    fun onEmailLongClick(email: String?)
    fun onShowOnMapClick(building: Building?)
}