package ru.spbstu.abit.locations.model

import android.os.Parcelable
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class Building(
    val addressName: String,
    val latLng: LatLng,
    val markdownDescription: String? = null
) : Parcelable {
    MAIN_BUILDING("Главный учебный корпус", LatLng(60.007288, 30.372906),
        "### Приём документов\n" +
                "**ИКНТ** - ауд. 228  \n" +
                "**ИСИ** - ауд. 220  \n" +
                "**ИФНИТ** - ауд. 113  \n" +
                "**ИПММ** - ауд. 109  \n\n" +
                "### График работы приёмной комиссии:  \n" +
                "+ **Пн - Пт**: с 10:00 до 17:00  \n" +
                "+ **Сб - Вс**: выходной день, **приёма нет**"),
    CHEMICAL_BUILDING("Химический корпус", LatLng(60.006648, 30.376425), null),
    MECHANICAL_BUILDING("Механический корпус", LatLng(60.008133, 30.377124), null),
    HYDRO_1_BUILDING("Гидрокорпус-1", LatLng(60.005766, 30.382106), null),
    HYDRO_2_BUILDING("Гидрокорпус-2", LatLng(60.006599, 30.383599), null),
    HYDROTOWER_BUILDING("Гидробашня", LatLng(60.005346, 30.374041), null),
    LAB_BUILDING("Лабораторный корпус", LatLng(60.007571, 30.379943), null),
    RNE_CENTER_BUILDING("НОЦ РАН", LatLng(60.003078, 30.373460), null),
    EDU_1_BUILDING("1-й учебный корпус", LatLng(60.008869, 30.372899), null),
    EDU_2_BUILDING("2-й учебный корпус", LatLng(60.008545, 30.374718), null),
    EDU_3_BUILDING("3-й учебный корпус", LatLng(60.007238, 30.381733), null),
    EDU_4_BUILDING("4-й учебный корпус", LatLng(60.007334, 30.376795), null),
    EDU_5_BUILDING("5-й учебный корпус", LatLng(59.999667, 30.374262), null),
    EDU_6_BUILDING("6-й учебный корпус", LatLng(60.000125, 30.367474), null),
    EDU_9_BUILDING("9-й учебный корпус", LatLng(60.000781, 30.366350), null),
    EDU_10_BUILDING("10-й учебный корпус", LatLng(60.000637, 30.369680), null),
    EDU_11_BUILDING("11-й учебный корпус", LatLng(60.009055, 30.378137), null),
    EDU_15_BUILDING("15-й учебный корпус", LatLng(60.007211, 30.390365), null),
    EDU_16_BUILDING("16-й учебный корпус", LatLng(60.008058, 30.390261), null),
    IIMET_BUILDING("Институт промышленного менеджмента, экономики и торговли", LatLng(59.994495, 30.358530), null),
    RESEARCH_BUILDING("Научно-исследовательский корпус (НИК)", LatLng(60.006531, 30.379546), null),
    PRE_STUDY_CENTER_BUILDING("Центр профориентации и довузовской подготовки", LatLng(60.009485, 30.371690), null),
    PROFESSORIAL_1_BUILDING("1-й профессорский корпус", LatLng(60.004988, 30.369991), null),
    PROFESSORIAL_2_BUILDING("2-й профессорский корпус", LatLng(60.004885, 30.378071), null),
    SCIENTISTS_BUILDING("Дом ученых в Лесном", LatLng(60.004351, 30.379542), null),
    SPORT_BUILDING("Спортивный комплекс «Политехник»", LatLng(60.002858, 30.368659), null),
    CAMPUS_MANAGEMENT_BUILDING("Управление Студенческого городка", LatLng(59.998882, 30.374293), null),
    DORM_1_BUILDING("Общежитие № 1", LatLng(59.986064, 30.342285), null),
    DORM_3_BUILDING("Общежитие № 3", LatLng(59.986717, 30.343237), null),
    DORM_4_BUILDING("Общежитие № 4, 4а", LatLng(59.986351, 30.345870), null),
    DORM_5_BUILDING("Общежитие № 5, 5б", LatLng(59.986417, 30.347013), null),
    DORM_6_BUILDING("Общежитие № 6м, 6ф", LatLng(59.986732, 30.348024), null),
    DORM_7_BUILDING("Общежитие № 7", LatLng(59.986703, 30.342404), null),
    DORM_8_BUILDING("Общежитие № 8", LatLng(59.999523, 30.372223), null),
    DORM_10_BUILDING("Общежитие № 10", LatLng(59.998432, 30.370552), null),
    DORM_11_BUILDING("Общежитие № 11", LatLng(59.985522, 30.343805), null),
    DORM_12_BUILDING("Общежитие № 12", LatLng(59.998864, 30.375858), null),
    DORM_13_BUILDING("Общежитие № 13", LatLng(60.008774, 30.392032), null),
    DORM_14_BUILDING("Общежитие №14", LatLng(59.998884, 30.374289), null),
    DORM_15_BUILDING("Общежитие № 15", LatLng(60.007317, 30.389844), null),
    DORM_16_BUILDING("Общежитие № 16", LatLng(60.047685, 30.334384), null),
    DORM_17_BUILDING("Общежитие № 17", LatLng(60.021792, 30.388307), null),
    DORM_18_BUILDING("Общежитие № 18", LatLng(60.022042, 30.387298), null),
    DORM_19_BUILDING("Общежитие № 19", LatLng(59.859133, 30.326908), null),
    DORM_20_BUILDING("Общежитие № 20", LatLng(59.911668, 30.320114), null)
}