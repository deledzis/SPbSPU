package ru.spbstu.abit.data

import android.content.Context
import android.os.Parcelable
import ru.spbstu.abit.R
import ru.spbstu.abit.locations.model.Building
import ru.spbstu.abit.menu.MenuListItem
import ru.spbstu.abit.institutes.Email
import ru.spbstu.abit.institutes.Institute
import ru.spbstu.abit.institutes.InstituteEnum
import ru.spbstu.abit.institutes.Phone
import ru.spbstu.abit.institutes.departments.Department
import ru.spbstu.abit.institutes.persons.model.Location
import ru.spbstu.abit.institutes.persons.model.Person
import ru.spbstu.abit.institutes.persons.model.Position
import ru.spbstu.abit.institutes.studyprograms.model.EducationForms
import ru.spbstu.abit.institutes.studyprograms.model.StudyProgram
import ru.spbstu.abit.timeline.TimelineEvent
import ru.spbstu.abit.App
import ru.spbstu.abit.util.Constants
import java.util.*

object DefaultRepository {

    // Study Programs
    val defaultStudyProgramsList: ArrayList<StudyProgram>
        get() = arrayListOf(
            StudyProgram(
                "13.03.02",
                null,
                "Электроэнергетика и электротехника",
                EducationForms(0, 0, 0),
                EducationForms(0, 0, 0),
                0,
                0,
                arrayListOf(
                    StudyProgram(
                        null, null,
                        "«Электроэнергетические системы и сети»",
                        EducationForms(40, 0, 0),
                        EducationForms(20, 10, 0),
                        134000,
                        100000,
                        null,
                        1,
                        hashMapOf(
                            "Основная образовательная программа (.pdf)" to "https://www.spbstu.ru/edu/plans/01.03.02_01/oop_01.03.02_01.pdf?t=1561220022",
                            "Учебный план (.pdf)" to "https://www.spbstu.ru/edu/plans/01.03.02_01/2018/ucheb_plan_01.03.02_01_o_2018.pdf?t=1561220022",
                            "График учебного процесса (.pdf)" to "https://www.spbstu.ru/edu/plans/01.03.02_01/2018/calendar_01.03.02_01_o_2018.pdf?t=1561220022",
                            "Аннотации к рабочим программам (.zip)" to "https://www.spbstu.ru/edu/plans/01.03.02_01/2018/annotations_01.03.02_01_o_2018.zip?t=1561220022",
                            "Программа учебных практик (.zip)" to "https://www.spbstu.ru/edu/plans/01.03.02_01/2018/practices_01.03.02_01_o_2018.zip?t=1561220022"
                        )
                    ),
                    StudyProgram(
                        null, null,
                        "«Электростанции и розетки»",
                        EducationForms(100, 15, 0),
                        EducationForms(30, 35, 0),
                        140000,
                        115000,
                        null,
                        2,
                        hashMapOf(
                            "Основная образовательная программа (.pdf)" to "https://www.spbstu.ru/edu/plans/01.03.02_01/oop_01.03.02_01.pdf?t=1561220022",
                            "Учебный план (.pdf)" to "https://www.spbstu.ru/edu/plans/01.03.02_01/2018/ucheb_plan_01.03.02_01_o_2018.pdf?t=1561220022",
                            "График учебного процесса (.pdf)" to "https://www.spbstu.ru/edu/plans/01.03.02_01/2018/calendar_01.03.02_01_o_2018.pdf?t=1561220022",
                            "Аннотации к рабочим программам (.zip)" to "https://www.spbstu.ru/edu/plans/01.03.02_01/2018/annotations_01.03.02_01_o_2018.zip?t=1561220022",
                            "Программа учебных практик (.zip)" to "https://www.spbstu.ru/edu/plans/01.03.02_01/2018/practices_01.03.02_01_o_2018.zip?t=1561220022"
                        )
                    )
                ),
                null,
                null
            ),
            StudyProgram(
                "13.03.02", null,
                "Электроэнергетика и электротехника",
                EducationForms(20, 20, 5),
                EducationForms(0, 10, 0),
                125000,
                100000,
                null,
                null,
                hashMapOf(
                    "Основная образовательная программа (.pdf)" to "https://www.spbstu.ru/edu/plans/01.03.02_01/oop_01.03.02_01.pdf?t=1561220022",
                    "Учебный план (.pdf)" to "https://www.spbstu.ru/edu/plans/01.03.02_01/2018/ucheb_plan_01.03.02_01_o_2018.pdf?t=1561220022",
                    "График учебного процесса (.pdf)" to "https://www.spbstu.ru/edu/plans/01.03.02_01/2018/calendar_01.03.02_01_o_2018.pdf?t=1561220022",
                    "Аннотации к рабочим программам (.zip)" to "https://www.spbstu.ru/edu/plans/01.03.02_01/2018/annotations_01.03.02_01_o_2018.zip?t=1561220022",
                    "Программа учебных практик (.zip)" to "https://www.spbstu.ru/edu/plans/01.03.02_01/2018/practices_01.03.02_01_o_2018.zip?t=1561220022"
                )
            ),
            StudyProgram(
                "13.03.02", null,
                "Электроэнергетика и электротехника",
                EducationForms(0, 0, 0),
                EducationForms(0, 0, 0),
                0,
                0,
                arrayListOf(
                    StudyProgram(
                        null, null,
                        "«Электроэнергетические системы и сети»",
                        EducationForms(20, 10, 0),
                        EducationForms(30, 5, 0),
                        70000,
                        100000,
                        null,
                        1,
                        hashMapOf(
                            "Основная образовательная программа (.pdf)" to "https://www.spbstu.ru/edu/plans/01.03.02_01/oop_01.03.02_01.pdf?t=1561220022",
                            "Учебный план (.pdf)" to "https://www.spbstu.ru/edu/plans/01.03.02_01/2018/ucheb_plan_01.03.02_01_o_2018.pdf?t=1561220022",
                            "График учебного процесса (.pdf)" to "https://www.spbstu.ru/edu/plans/01.03.02_01/2018/calendar_01.03.02_01_o_2018.pdf?t=1561220022",
                            "Аннотации к рабочим программам (.zip)" to "https://www.spbstu.ru/edu/plans/01.03.02_01/2018/annotations_01.03.02_01_o_2018.zip?t=1561220022",
                            "Программа учебных практик (.zip)" to "https://www.spbstu.ru/edu/plans/01.03.02_01/2018/practices_01.03.02_01_o_2018.zip?t=1561220022"
                        )
                    ),
                    StudyProgram(
                        null, null,
                        "«Электростанции и розетки»",
                        EducationForms(40, 0, 10),
                        EducationForms(0, 0, 5),
                        185000,
                        115000,
                        null,
                        2,
                        hashMapOf(
                            "Основная образовательная программа (.pdf)" to "https://www.spbstu.ru/edu/plans/01.03.02_01/oop_01.03.02_01.pdf?t=1561220022",
                            "Учебный план (.pdf)" to "https://www.spbstu.ru/edu/plans/01.03.02_01/2018/ucheb_plan_01.03.02_01_o_2018.pdf?t=1561220022",
                            "График учебного процесса (.pdf)" to "https://www.spbstu.ru/edu/plans/01.03.02_01/2018/calendar_01.03.02_01_o_2018.pdf?t=1561220022",
                            "Аннотации к рабочим программам (.zip)" to "https://www.spbstu.ru/edu/plans/01.03.02_01/2018/annotations_01.03.02_01_o_2018.zip?t=1561220022",
                            "Программа учебных практик (.zip)" to "https://www.spbstu.ru/edu/plans/01.03.02_01/2018/practices_01.03.02_01_o_2018.zip?t=1561220022"
                        )
                    ),
                    StudyProgram(
                        null, null,
                        "«Электроэнергетические системы и сети»",
                        EducationForms(40, 10, 4),
                        EducationForms(0, 0, 0),
                        65000,
                        100000,
                        null,
                        3,
                        hashMapOf(
                            "Основная образовательная программа (.pdf)" to "https://www.spbstu.ru/edu/plans/01.03.02_01/oop_01.03.02_01.pdf?t=1561220022",
                            "Учебный план (.pdf)" to "https://www.spbstu.ru/edu/plans/01.03.02_01/2018/ucheb_plan_01.03.02_01_o_2018.pdf?t=1561220022",
                            "График учебного процесса (.pdf)" to "https://www.spbstu.ru/edu/plans/01.03.02_01/2018/calendar_01.03.02_01_o_2018.pdf?t=1561220022",
                            "Аннотации к рабочим программам (.zip)" to "https://www.spbstu.ru/edu/plans/01.03.02_01/2018/annotations_01.03.02_01_o_2018.zip?t=1561220022",
                            "Программа учебных практик (.zip)" to "https://www.spbstu.ru/edu/plans/01.03.02_01/2018/practices_01.03.02_01_o_2018.zip?t=1561220022"
                        )
                    ),
                    StudyProgram(
                        null, null,
                        "«Электростанции и розетки»",
                        EducationForms(0, 0, 0),
                        EducationForms(0, 10, 8),
                        85000,
                        115000,
                        null,
                        4,
                        hashMapOf(
                            "Основная образовательная программа (.pdf)" to "https://www.spbstu.ru/edu/plans/01.03.02_01/oop_01.03.02_01.pdf?t=1561220022",
                            "Учебный план (.pdf)" to "https://www.spbstu.ru/edu/plans/01.03.02_01/2018/ucheb_plan_01.03.02_01_o_2018.pdf?t=1561220022",
                            "График учебного процесса (.pdf)" to "https://www.spbstu.ru/edu/plans/01.03.02_01/2018/calendar_01.03.02_01_o_2018.pdf?t=1561220022",
                            "Аннотации к рабочим программам (.zip)" to "https://www.spbstu.ru/edu/plans/01.03.02_01/2018/annotations_01.03.02_01_o_2018.zip?t=1561220022",
                            "Программа учебных практик (.zip)" to "https://www.spbstu.ru/edu/plans/01.03.02_01/2018/practices_01.03.02_01_o_2018.zip?t=1561220022"
                        )
                    )
                ),
                null,
                null
            )
        )

    // Departments
    val defaultDepartmentsList: ArrayList<Department>
        get() = arrayListOf(
            Department(
                title = "Кафедра «Лингвистика и межкультурная коммуникация»",
                url = "http://lmcc.spbstu.ru",
                email = "lingua@mail.spbstu.ru",
                phone = "+7 (812) 297-03-18",
                location = Location(
                    "9-й учебный корпус, ул. Политехническая, дом 21, Санкт-Петербург, 194021",
                    Building.EDU_9_BUILDING
                ),
                subDepartments = null,
                directorate = defaultPersonsList,
                logoUrl = null,
                info = "Высшая школа программной инженерии создана в 2017 году решением ученого совета Политехнического университета на базе кафедр «Информационные и управляющие системы» и «Распределенные вычисления и компьютерные сети», которые обладают 40 летним опытом работы в этой области.",
                descriptionMdFileUrl = "file:///android_asset/icst_department_0_description.md"
            ),
            Department(
                title = "Высшая школа инженерной педагогики, психологии и прикладной лингвистики",
                url = "http://lip.spbstu.ru",
                email = "lingua@mail.spbstu.ru",
                phone = "+7 (812) 297-03-18",
                location = Location(
                    "9-й учебный корпус, ул. Политехническая, дом 21, Санкт-Петербург, 194021",
                    Building.EDU_9_BUILDING
                ),
                subDepartments = arrayListOf(
                    Department(
                        "Кафедра «Международные отношения»",
                        "http://ir.spbstu.ru",
                        "kmo@spbstu.ru",
                        "+7 (812) 606-62-42",
                        null,
                        null
                    ),
                    Department(
                        "Кафедра «Реклама и связи с общественностью»",
                        "http://rso.spbstu.ru",
                        "rso@spbstu.ru",
                        "+7 (812) 606-62-37",
                        null,
                        null
                    ),
                    Department(
                        "Высшая школа юриспруденции и судебно-технической экспертизы",
                        "http://lex.spbstu.ru",
                        "lex@spbstu.ru",
                        "+7 (812) 552-87-01",
                        null,
                        null
                    )
                ),
                directorate = defaultPersonsList,
                logoUrl = null,
                info = "Высшая школа программной инженерии создана в 2017 году решением ученого совета Политехнического университета на базе кафедр «Информационные и управляющие системы» и «Распределенные вычисления и компьютерные сети», которые обладают 40 летним опытом работы в этой области.",
                descriptionMdFileUrl = "file:///android_asset/icst_department_0_description.md"
            ),
            Department(
                title = "Кафедра «Международные отношения»",
                url = "http://ir.spbstu.ru",
                email = "kmo@spbstu.ru",
                phone = "+7 (812) 606-62-42",
                location = Location(
                    "9-й учебный корпус, ул. Политехническая, дом 21, Санкт-Петербург, 194021",
                    Building.EDU_9_BUILDING
                ),
                subDepartments = null,
                directorate = defaultPersonsList,
                logoUrl = null,
                info = "Высшая школа программной инженерии создана в 2017 году решением ученого совета Политехнического университета на базе кафедр «Информационные и управляющие системы» и «Распределенные вычисления и компьютерные сети», которые обладают 40 летним опытом работы в этой области.",
                descriptionMdFileUrl = "file:///android_asset/icst_department_0_description.md"
            ),
            Department(
                title = "Кафедра «Реклама и связи с общественностью»",
                url = "http://rso.spbstu.ru",
                email = "rso@spbstu.ru",
                phone = "+7 (812) 606-62-37",
                location = Location(
                    "9-й учебный корпус, ул. Политехническая, дом 21, Санкт-Петербург, 194021",
                    Building.EDU_9_BUILDING
                ),
                subDepartments = null,
                directorate = null,
                logoUrl = null,
                info = "Высшая школа программной инженерии создана в 2017 году решением ученого совета Политехнического университета на базе кафедр «Информационные и управляющие системы» и «Распределенные вычисления и компьютерные сети», которые обладают 40 летним опытом работы в этой области.",
                descriptionMdFileUrl = "file:///android_asset/icst_department_0_description.md"
            ),
            Department(
                title = "Высшая школа юриспруденции и судебно-технической экспертизы",
                url = "http://lex.spbstu.ru",
                email = "lex@spbstu.ru",
                phone = "+7 (812) 552-87-01",
                location = Location(
                    "9-й учебный корпус, ул. Политехническая, дом 21, Санкт-Петербург, 194021",
                    Building.EDU_9_BUILDING
                ),
                subDepartments = null,
                directorate = null,
                logoUrl = null,
                info = "Высшая школа программной инженерии создана в 2017 году решением ученого совета Политехнического университета на базе кафедр «Информационные и управляющие системы» и «Распределенные вычисления и компьютерные сети», которые обладают 40 летним опытом работы в этой области.",
                descriptionMdFileUrl = null
            ),
            Department(
                title = "Высшая школа общественных наук",
                url = "http://lex.spbstu.ru",
                email = "lex@spbstu.ru",
                phone = "+7 (812) 534-75-21",
                location = Location(
                    "9-й учебный корпус, ул. Политехническая, дом 21, Санкт-Петербург, 194021",
                    Building.EDU_9_BUILDING
                ),
                subDepartments = null,
                directorate = null,
                logoUrl = null,
                info = "Высшая школа программной инженерии создана в 2017 году решением ученого совета Политехнического университета на базе кафедр «Информационные и управляющие системы» и «Распределенные вычисления и компьютерные сети», которые обладают 40 летним опытом работы в этой области.",
                descriptionMdFileUrl = null
            ),
            Department(
                title = "Высшая школа иностранных языков",
                url = "https://gsfl.spbstu.ru",
                email = "gsforlang@spbstu.ru",
                phone = "+7 (812) 297-98-17",
                location = Location(
                    "9-й учебный корпус, ул. Политехническая, дом 21, Санкт-Петербург, 194021",
                    Building.EDU_9_BUILDING
                ),
                subDepartments = null,
                directorate = null,
                logoUrl = null,
                info = "Высшая школа программной инженерии создана в 2017 году решением ученого совета Политехнического университета на базе кафедр «Информационные и управляющие системы» и «Распределенные вычисления и компьютерные сети», которые обладают 40 летним опытом работы в этой области.",
                descriptionMdFileUrl = "file:///android_asset/icst_department_0_description.md"
            )
        )

    // Positions
    private val defaultPositionsList: ArrayList<Position>
        get() = arrayListOf(
            Position("Директор института"),
            Position("И.о. директора института"),
            Position("Заведующий кафедрой")
        )

    // Persons
    val defaultPersonsList: ArrayList<Person>
        get() = arrayListOf(
            Person(
                url = "https://icst.spbstu.ru/person/drobincev_pavel_dmitrievich/",
                fullName = "Дробинцев Павел Дмитриевич",
                responsibilities = arrayListOf(
                    Position(
                        positionName = App.instance.getString(R.string.position_acting_director),
                        institute = InstituteEnum.ICST
                    )
                ),
                positions = arrayListOf(
                    Position(
                        positionName = App.instance.getString(R.string.position_director),
                        institute = null,
                        department = Department(
                            title = "Высшая школа программной инженерии"
                        )
                    )
                ),
                email = "drobintsev_pd@spbstu.ru",
                phone = "+7 (812) 297-16-28",
                location = Location(
                    "Ауд. 229, 9-й уч.корпус",
                    Building.EDU_9_BUILDING
                ),
                degreeStringResId = R.string.academic_degree_assistant,
                photoUrl = "https://icst.spbstu.ru/userfiles/images/people/drobintsev.jpg",
                scopusUrl = "https://www.scopus.com/authid/detail.uri?authorId=56049610600",
                rsciUrl = "https://elibrary.ru/author_items.asp?authorid=657671",
                description = null
            ),
            Person(
                url = "https://icst.spbstu.ru/person/bogach_natalya_vladimirovna/",
                fullName = "Богач Наталья Владимировна",
                responsibilities = arrayListOf(
                    Position(
                        positionName = App.instance.getString(R.string.position_resp_international),
                        institute = InstituteEnum.ICST
                    )
                ),
                positions = arrayListOf(
                    Position(
                        positionName = App.instance.getString(R.string.position_assistant),
                        institute = null,
                        department = Department(
                            title = "Кафедра «Компьютерные системы и программные технологии»"
                        )
                    )
                ),
                email = "bogach_nv@spbstu.ru",
                phone = "+7 (812) 297-60-01",
                location = null,
                degreeStringResId = null,
                photoUrl = "https://icst.spbstu.ru/userfiles/images/people/bogach.jpg",
                scopusUrl = "https://www.scopus.com/authid/detail.uri?authorId=57190026890",
                rsciUrl = "http://elibrary.ru/author_items.asp?authorid=276362",
                description = null

            ),
            Person(
                url = "https://icst.spbstu.ru/person/vasilev_aleksey_evgenevich/",
                fullName = "Васильев Алексей Евгеньевич",
                responsibilities = null,
                positions = arrayListOf(
                    Position(
                        positionName = App.instance.getString(R.string.position_head_of_department),
                        institute = null,
                        department = Department(
                            title = "Кафедра «Измерительные информационные технологии»"
                        )
                    )
                ),
                email = "vasiliev_alex@spbstu.ru",
                phone = "+7 (812) 297-60-01",
                location = null,
                degreeStringResId = null,
                photoUrl = "https://icst.spbstu.ru/userfiles/images/people/vasiliev.jpg",
                scopusUrl = "https://www.scopus.com/authid/detail.uri?authorId=56440039000",
                rsciUrl = "https://elibrary.ru/author_items.asp?authorid=617350",
                description = "часы приема - вторник с 12 до 13, четверг с 16 до 17, а. 225, 9-й уч.корпус"
            ),
            Person(
                url = "https://icst.spbstu.ru/person/redko_sergey_georgievich/",
                fullName = "Редько Сергей Георгиевич",
                responsibilities = null,
                positions = arrayListOf(
                    Position(
                        positionName = App.instance.getString(R.string.position_professor),
                        institute = null,
                        department = Department(
                            title = "Высшая школа киберфизических систем и управления"
                        )
                    )
                ),
                email = "redko_sg@spbstu.ru",
                phone = null,
                location = null,
                degreeStringResId = null,
                photoUrl = "https://icst.spbstu.ru/userfiles/images/people/redko.jpg",
                scopusUrl = null,
                rsciUrl = "http://elibrary.ru/author_items.asp?authorid=420022",
                description = null
            ),
            Person(
                url = "https://icst.spbstu.ru/person/its_tatyana_aleksandrovna/",
                fullName = "Итс Татьяна Александровна",
                responsibilities = arrayListOf(
                    Position(
                        positionName = App.instance.getString(R.string.position_resp_full_time_students),
                        institute = InstituteEnum.ICST
                    )
                ),
                positions = arrayListOf(
                    Position(
                        positionName = App.instance.getString(R.string.position_assistant),
                        institute = null,
                        department = Department(
                            title = "Высшая школа киберфизических систем и управления"
                        )
                    )
                ),
                email = "its_ta@spbstu.ru",
                phone = null,
                location = null,
                degreeStringResId = null,
                photoUrl = "https://icst.spbstu.ru/userfiles/images/people/its.jpg",
                scopusUrl = null,
                rsciUrl = "http://elibrary.ru/author_items.asp?authorid=333274",
                description = "ул. Политехническая 29, Главный учебный корпус, к. 238, домофон 5"
            )
        )

    val ALL_BUILDINGS: ArrayList<Building> = arrayListOf(
        Building.MAIN_BUILDING,
        Building.CHEMICAL_BUILDING,
        Building.MECHANICAL_BUILDING,
        Building.HYDRO_1_BUILDING,
        Building.HYDRO_2_BUILDING,
        Building.HYDROTOWER_BUILDING,
        Building.LAB_BUILDING,
        Building.RNE_CENTER_BUILDING,
        Building.EDU_1_BUILDING,
        Building.EDU_2_BUILDING,
        Building.EDU_3_BUILDING,
        Building.EDU_4_BUILDING,
        Building.EDU_5_BUILDING,
        Building.EDU_6_BUILDING,
        Building.EDU_9_BUILDING,
        Building.EDU_10_BUILDING,
        Building.EDU_11_BUILDING,
        Building.EDU_15_BUILDING,
        Building.EDU_16_BUILDING,
        Building.IIMET_BUILDING,
        Building.RESEARCH_BUILDING,
        Building.PRE_STUDY_CENTER_BUILDING,
        Building.PROFESSORIAL_1_BUILDING,
        Building.PROFESSORIAL_2_BUILDING,
        Building.SCIENTISTS_BUILDING,
        Building.SPORT_BUILDING,
        Building.CAMPUS_MANAGEMENT_BUILDING,
        Building.DORM_1_BUILDING,
        Building.DORM_3_BUILDING,
        Building.DORM_4_BUILDING,
        Building.DORM_5_BUILDING,
        Building.DORM_6_BUILDING,
        Building.DORM_7_BUILDING,
        Building.DORM_8_BUILDING,
        Building.DORM_10_BUILDING,
        Building.DORM_11_BUILDING,
        Building.DORM_12_BUILDING,
        Building.DORM_13_BUILDING,
        Building.DORM_14_BUILDING,
        Building.DORM_15_BUILDING,
        Building.DORM_16_BUILDING,
        Building.DORM_17_BUILDING,
        Building.DORM_18_BUILDING,
        Building.DORM_19_BUILDING,
        Building.DORM_20_BUILDING
    )

    // Timeline
    fun getDefaultEventsList(context: Context): ArrayList<TimelineEvent> {
        val fullTime = context.getString(R.string.educations_form_full_time)
        val extramural = context.getString(R.string.educations_form_extramural)
        val partTime = context.getString(R.string.educations_form_part_time)

        val gvnFunded = context.getString(R.string.funding_forms_government)
        val selfFunded = context.getString(R.string.funding_forms_self)

        return arrayListOf(
            TimelineEvent(
                "$fullTime \u00B7 $extramural \u00B7 $partTime",
                "$gvnFunded \u00B7 $selfFunded",
                "Начало приёма документов",
                Calendar.getInstance().apply {
                    set(Calendar.YEAR, 2019)
                    set(Calendar.MONTH, Calendar.JUNE)
                    set(Calendar.DAY_OF_MONTH, 20)
                }.time,
                false
            ),
            TimelineEvent(
                "$fullTime \u00B7 $partTime",
                "$gvnFunded \u00B7 $selfFunded",
                "Окончание приёма документов для поступающих на «Дизайн»",
                Calendar.getInstance().apply {
                    set(Calendar.YEAR, 2019)
                    set(Calendar.MONTH, Calendar.JULY)
                    set(Calendar.DAY_OF_MONTH, 7)
                }.time,
                false
            ),
            TimelineEvent(
                "$fullTime \u00B7 $partTime",
                "$gvnFunded \u00B7 $selfFunded",
                "Начало вступительных испытаний для направления «Дизайн»",
                Calendar.getInstance().apply {
                    set(Calendar.YEAR, 2019)
                    set(Calendar.MONTH, Calendar.JULY)
                    set(Calendar.DAY_OF_MONTH, 8)
                }.time,
                false
            ),
            TimelineEvent(
                "$fullTime \u00B7 $partTime",
                "$gvnFunded \u00B7 $selfFunded",
                "Окончание приёма документов для поступающих по вступительным испытаниям СПбПУ",
                Calendar.getInstance().apply {
                    set(Calendar.YEAR, 2019)
                    set(Calendar.MONTH, Calendar.JULY)
                    set(Calendar.DAY_OF_MONTH, 10)
                }.time,
                false
            ),
            TimelineEvent(
                "$fullTime \u00B7 $extramural \u00B7 $partTime",
                "$gvnFunded \u00B7 $selfFunded",
                "Начало вступительных испытаний для всех направлений, кроме «Дизайн»",
                Calendar.getInstance().apply {
                    set(Calendar.YEAR, 2019)
                    set(Calendar.MONTH, Calendar.JULY)
                    set(Calendar.DAY_OF_MONTH, 12)
                }.time,
                false
            ),
            TimelineEvent(
                "$fullTime \u00B7 $partTime",
                "$gvnFunded \u00B7 $selfFunded",
                "Окончание вступительных испытаний для всех направлений",
                Calendar.getInstance().apply {
                    set(Calendar.YEAR, 2019)
                    set(Calendar.MONTH, Calendar.JULY)
                    set(Calendar.DAY_OF_MONTH, 26)
                }.time,
                false
            ),
            TimelineEvent(
                "$fullTime \u00B7 $partTime",
                gvnFunded,
                "Окончание приёма документов для поступающих по ЕГЭ (Бюджет)",
                Calendar.getInstance().apply {
                    set(Calendar.YEAR, 2019)
                    set(Calendar.MONTH, Calendar.JULY)
                    set(Calendar.DAY_OF_MONTH, 26)
                }.time,
                false
            ),
            TimelineEvent(
                "$fullTime \u00B7 $partTime",
                gvnFunded,
                "Даты завершения приема заявлений о согласии на зачисление (по квотам и БВИ)",
                Calendar.getInstance().apply {
                    set(Calendar.YEAR, 2019)
                    set(Calendar.MONTH, Calendar.JULY)
                    set(Calendar.DAY_OF_MONTH, 28)
                }.time,
                false
            ),
            TimelineEvent(
                "$fullTime \u00B7 $partTime",
                gvnFunded,
                "Приказы о зачислении лиц, подавших заявление о согласии на зачисление (по квотам)",
                Calendar.getInstance().apply {
                    set(Calendar.YEAR, 2019)
                    set(Calendar.MONTH, Calendar.JULY)
                    set(Calendar.DAY_OF_MONTH, 29)
                }.time,
                false
            ),
            TimelineEvent(
                "$fullTime \u00B7 $partTime",
                gvnFunded,
                "Даты завершения приема заявлений о согласии на зачисление (Первый этап)",
                Calendar.getInstance().apply {
                    set(Calendar.YEAR, 2019)
                    set(Calendar.MONTH, Calendar.AUGUST)
                    set(Calendar.DAY_OF_MONTH, 1)
                }.time,
                false
            ),
            TimelineEvent(
                "$fullTime \u00B7 $partTime",
                gvnFunded,
                "Приказы о зачислении лиц, подавших заявление о согласии на зачисление (Первый этап)",
                Calendar.getInstance().apply {
                    set(Calendar.YEAR, 2019)
                    set(Calendar.MONTH, Calendar.AUGUST)
                    set(Calendar.DAY_OF_MONTH, 3)
                }.time,
                false
            ),
            TimelineEvent(
                "$fullTime \u00B7 $partTime",
                gvnFunded,
                "Даты завершения приема заявлений о согласии на зачисление (Второй этап)",
                Calendar.getInstance().apply {
                    set(Calendar.YEAR, 2019)
                    set(Calendar.MONTH, Calendar.AUGUST)
                    set(Calendar.DAY_OF_MONTH, 6)
                }.time,
                false
            ),
            TimelineEvent(
                "$fullTime \u00B7 $partTime",
                gvnFunded,
                "Приказы о зачислении лиц, подавших заявление о согласии на зачисление (Второй этап)",
                Calendar.getInstance().apply {
                    set(Calendar.YEAR, 2019)
                    set(Calendar.MONTH, Calendar.AUGUST)
                    set(Calendar.DAY_OF_MONTH, 8)
                }.time,
                false
            ),
            TimelineEvent(
                "$fullTime \u00B7 $partTime",
                selfFunded,
                "Окончание приёма документов для поступающих по ЕГЭ",
                Calendar.getInstance().apply {
                    set(Calendar.YEAR, 2019)
                    set(Calendar.MONTH, Calendar.AUGUST)
                    set(Calendar.DAY_OF_MONTH, 9)
                }.time,
                false
            ),
            TimelineEvent(
                "$fullTime \u00B7 $extramural \u00B7 $partTime",
                selfFunded,
                "Завершение заключения договоров об оказании платных образовательных услуг",
                Calendar.getInstance().apply {
                    set(Calendar.YEAR, 2019)
                    set(Calendar.MONTH, Calendar.AUGUST)
                    set(Calendar.DAY_OF_MONTH, 12)
                }.time,
                false
            ),
            TimelineEvent(
                "$fullTime \u00B7 $extramural \u00B7 $partTime",
                selfFunded,
                "Приказы о зачислении лиц, подавших заявление о согласии на зачисление (для заключивших договоры до 12 августа, выполнивших условия конкурса и оплативших обучение)",
                Calendar.getInstance().apply {
                    set(Calendar.YEAR, 2019)
                    set(Calendar.MONTH, Calendar.AUGUST)
                    set(Calendar.DAY_OF_MONTH, 15)
                }.time,
                false
            ),
            TimelineEvent(
                extramural,
                selfFunded,
                "Окончание приёма документов",
                Calendar.getInstance().apply {
                    set(Calendar.YEAR, 2019)
                    set(Calendar.MONTH, Calendar.SEPTEMBER)
                    set(Calendar.DAY_OF_MONTH, 4)
                }.time,
                false
            ),
            TimelineEvent(
                extramural,
                selfFunded,
                "Окончание вступительных испытаний",
                Calendar.getInstance().apply {
                    set(Calendar.YEAR, 2019)
                    set(Calendar.MONTH, Calendar.SEPTEMBER)
                    set(Calendar.DAY_OF_MONTH, 12)
                }.time,
                false
            ),
            TimelineEvent(
                "$extramural \u00B7 $partTime",
                selfFunded,
                "Завершение заключения договоров об оказании платных образовательных услуг",
                Calendar.getInstance().apply {
                    set(Calendar.YEAR, 2019)
                    set(Calendar.MONTH, Calendar.SEPTEMBER)
                    set(Calendar.DAY_OF_MONTH, 17)
                }.time,
                false
            ),
            TimelineEvent(
                "$fullTime \u00B7 $extramural \u00B7 $partTime",
                selfFunded,
                "Приказы о зачислении лиц, подавших заявление о согласии на зачисление (для заключивших договоры до 17 сентября, выполнивших условия конкурса и оплативших обучение)",
                Calendar.getInstance().apply {
                    set(Calendar.YEAR, 2019)
                    set(Calendar.MONTH, Calendar.SEPTEMBER)
                    set(Calendar.DAY_OF_MONTH, 20)
                }.time,
                false
            )
        )
    }

    // Menu
    fun getDefaultMenuEntranceList(context: Context): ArrayList<MenuListItem<Parcelable>> {
        return object : ArrayList<MenuListItem<Parcelable>>() {
            init {
                add(
                    MenuListItem(
                        context.getString(R.string.menu_entrance_array_item_01),
                        R.drawable.ic_assignment,
                        R.color.colorPrimary,
                        R.color.colorLightSalad,
                        Constants.ACTION_SHOW_FAQ_ANSWER_DOCUMENTS, null
                    )
                )
                add(
                    MenuListItem(
                        context.getString(R.string.menu_entrance_array_item_02),
                        R.drawable.ic_rules,
                        R.color.colorPrimary,
                        R.color.colorLightSalad,
                        Constants.ACTION_SHOW_FAQ_ANSWER_EMPTY, null
                    )
                )
                add(
                    MenuListItem(
                        context.getString(R.string.menu_entrance_array_item_03),
                        R.drawable.ic_why_polytech,
                        R.color.colorPrimary,
                        R.color.colorLightSalad,
                        Constants.ACTION_SHOW_FAQ_ANSWER_EMPTY, null
                    )
                )
                add(
                    MenuListItem(
                        context.getString(R.string.menu_entrance_array_item_04),
                        R.drawable.ic_contacts,
                        R.color.colorPrimary,
                        R.color.colorLightSalad,
                        Constants.ACTION_SHOW_FAQ_ANSWER_CONTACTS, null
                    )
                )
                add(
                    MenuListItem(
                        context.getString(R.string.menu_entrance_array_item_05),
                        R.drawable.ic_people,
                        R.color.colorPrimary,
                        R.color.colorLightSalad,
                        Constants.ACTION_SHOW_FAQ_ANSWER_EMPTY, null
                    )
                )
                add(
                    MenuListItem(
                        context.getString(R.string.menu_entrance_array_item_06),
                        R.drawable.ic_planet,
                        R.color.colorPrimary,
                        R.color.colorLightSalad,
                        Constants.ACTION_SHOW_FAQ_ANSWER_EMPTY, null
                    )
                )
                add(
                    MenuListItem(
                        context.getString(R.string.menu_entrance_array_item_07),
                        R.drawable.ic_home,
                        R.color.colorPrimary,
                        R.color.colorLightSalad,
                        Constants.ACTION_SHOW_FAQ_ANSWER_DORMS, null
                    )
                )
            }
        }
    }

    // Structure Institutes
    fun getDefaultStructureInstitutesList(context: Context): ArrayList<Institute> {
        return arrayListOf(
            Institute(
                name = context.getString(R.string.institute_ih),
                abbreviation = context.getString(R.string.abbreviation_ih),
                studyPrograms = defaultStudyProgramsList,
                departments = defaultDepartmentsList,
                directorate = defaultPersonsList,
                logoDrawableId = R.drawable.ih_logo,
                backgroundDrawableId = R.drawable.ih_texture,
                colorId = R.color.colorIhPrimary,
                colorLightId = R.color.colorIhLight,
                url = "https://hum.spbstu.ru/",
                location = Location(
                    "9-й учебный корпус, ул. Политехническая, дом 21, Санкт-Петербург, 194021",
                    Building.EDU_9_BUILDING
                ),
                emails = arrayListOf(
                    Email("office@icc.spbstu.ru")
                ),
                phones = arrayListOf(
                    Phone(
                        number = "+7 (812) 297 16 28",
                        description = "Приёмная директора"
                    ),
                    Phone(
                        number = "+7 (812) 297 16 47",
                        description = "Деканат"
                    )
                ),
                descriptionMdFileUrl = "file:///android_asset/ih_description.md"
            ),
            Institute(
                name = context.getString(R.string.institute_icst),
                abbreviation = context.getString(R.string.abbreviation_icst),
                studyPrograms = defaultStudyProgramsList,
                departments = defaultDepartmentsList,
                directorate = defaultPersonsList,
                logoDrawableId = R.drawable.icst_logo,
                backgroundDrawableId = R.drawable.icst_texture,
                colorId = R.color.colorIcstPrimary,
                colorLightId = R.color.colorIcstLight,
                url = "https://icst.spbstu.ru/",
                location = Location(
                    "9-й учебный корпус, ул. Политехническая, дом 21, Санкт-Петербург, 194021",
                    Building.EDU_9_BUILDING
                ),
                emails = arrayListOf(
                    Email("office@icc.spbstu.ru")
                ),
                phones = arrayListOf(
                    Phone(
                        number = "+7 (812) 297 16 28",
                        description = "Приёмная директора"
                    ),
                    Phone(
                        number = "+7 (812) 297 16 47",
                        description = "Деканат"
                    )
                ),
                descriptionMdFileUrl = "file:///android_asset/icst_description.md"
            ),
            Institute(
                name = context.getString(R.string.institute_iamm),
                abbreviation = context.getString(R.string.abbreviation_iamm),
                studyPrograms = defaultStudyProgramsList,
                departments = defaultDepartmentsList,
                directorate = defaultPersonsList,
                logoDrawableId = R.drawable.iamm_logo,
                backgroundDrawableId = R.drawable.iamm_texture,
                colorId = R.color.colorIammPrimary,
                colorLightId = R.color.colorIammLight,
                url = "https://iamm.spbstu.ru/",
                location = Location(
                    "9-й учебный корпус, ул. Политехническая, дом 21, Санкт-Петербург, 194021",
                    Building.EDU_9_BUILDING
                ),
                emails = arrayListOf(
                    Email("office@icc.spbstu.ru")
                ),
                phones = arrayListOf(
                    Phone(
                        number = "+7 (812) 297 16 28",
                        description = "Приёмная директора"
                    ),
                    Phone(
                        number = "+7 (812) 297 16 47",
                        description = "Деканат"
                    )
                ),
                descriptionMdFileUrl = "file:///android_asset/iimet_description.md"
            ),
            Institute(
                name = context.getString(R.string.institute_iets),
                abbreviation = context.getString(R.string.abbreviation_iets),
                studyPrograms = defaultStudyProgramsList,
                departments = defaultDepartmentsList,
                directorate = defaultPersonsList,
                logoDrawableId = R.drawable.iets_logo,
                backgroundDrawableId = R.drawable.iets_texture,
                colorId = R.color.colorIetsPrimary,
                colorLightId = R.color.colorIetsLight,
                url = "https://ice.spbstu.ru/",
                location = Location(
                    "9-й учебный корпус, ул. Политехническая, дом 21, Санкт-Петербург, 194021",
                    Building.EDU_9_BUILDING
                ),
                emails = arrayListOf(
                    Email("office@icc.spbstu.ru")
                ),
                phones = arrayListOf(
                    Phone(
                        number = "+7 (812) 297 16 28",
                        description = "Приёмная директора"
                    ),
                    Phone(
                        number = "+7 (812) 297 16 47",
                        description = "Деканат"
                    )
                ),
                descriptionMdFileUrl = "file:///android_asset/iets_description.md"
            ),
            Institute(
                name = context.getString(R.string.institute_ice),
                abbreviation = context.getString(R.string.abbreviation_ice),
                studyPrograms = defaultStudyProgramsList,
                departments = defaultDepartmentsList,
                directorate = defaultPersonsList,
                logoDrawableId = R.drawable.ice_logo,
                backgroundDrawableId = R.drawable.ice_texture,
                colorId = R.color.colorIcePrimary,
                colorLightId = R.color.colorIceLight,
                url = "https://ice.spbstu.ru/",
                location = Location(
                    "9-й учебный корпус, ул. Политехническая, дом 21, Санкт-Петербург, 194021",
                    Building.EDU_9_BUILDING
                ),
                emails = arrayListOf(
                    Email("office@icc.spbstu.ru")
                ),
                phones = arrayListOf(
                    Phone(
                        number = "+7 (812) 297 16 28",
                        description = "Приёмная директора"
                    ),
                    Phone(
                        number = "+7 (812) 297 16 47",
                        description = "Деканат"
                    )
                ),
                descriptionMdFileUrl = "file:///android_asset/iimet_description.md"
            ),
            Institute(
                name = context.getString(R.string.institute_immet),
                abbreviation = context.getString(R.string.abbreviation_immet),
                studyPrograms = defaultStudyProgramsList,
                departments = defaultDepartmentsList,
                directorate = defaultPersonsList,
                logoDrawableId = R.drawable.immet_logo,
                backgroundDrawableId = R.drawable.immet_texture,
                colorId = R.color.colorImmetPrimary,
                colorLightId = R.color.colorImmetLight,
                url = "https://iamm.spbstu.ru/",
                location = Location(
                    "9-й учебный корпус, ул. Политехническая, дом 21, Санкт-Петербург, 194021",
                    Building.EDU_9_BUILDING
                ),
                emails = arrayListOf(
                    Email("office@icc.spbstu.ru")
                ),
                phones = arrayListOf(
                    Phone(
                        number = "+7 (812) 297 16 28",
                        description = "Приёмная директора"
                    ),
                    Phone(
                        number = "+7 (812) 297 16 47",
                        description = "Деканат"
                    )
                ),
                descriptionMdFileUrl = "file:///android_asset/immet_description.md"
            ),
            Institute(
                name = context.getString(R.string.institute_ipnt),
                abbreviation = context.getString(R.string.abbreviation_ipnt),
                studyPrograms = defaultStudyProgramsList,
                departments = defaultDepartmentsList,
                directorate = defaultPersonsList,
                logoDrawableId = R.drawable.ipnt_logo,
                backgroundDrawableId = R.drawable.ipnt_texture,
                colorId = R.color.colorIpntPrimary,
                colorLightId = R.color.colorIpntLight,
                url = "https://ice.spbstu.ru/",
                location = Location(
                    "9-й учебный корпус, ул. Политехническая, дом 21, Санкт-Петербург, 194021",
                    Building.EDU_9_BUILDING
                ),
                emails = arrayListOf(
                    Email("office@icc.spbstu.ru")
                ),
                phones = arrayListOf(
                    Phone(
                        number = "+7 (812) 297 16 28",
                        description = "Приёмная директора"
                    ),
                    Phone(
                        number = "+7 (812) 297 16 47",
                        description = "Деканат"
                    )
                ),
                descriptionMdFileUrl = "file:///android_asset/iimet_description.md"
            ),
            Institute(
                name = context.getString(R.string.institute_iimet),
                abbreviation = context.getString(R.string.abbreviation_iimet),
                studyPrograms = defaultStudyProgramsList,
                departments = defaultDepartmentsList,
                directorate = defaultPersonsList,
                logoDrawableId = R.drawable.iiem_logo,
                backgroundDrawableId = R.drawable.iiem_texture,
                colorId = R.color.colorIiemtPrimary,
                colorLightId = R.color.colorIiemtLight,
                url = "https://immit.spbstu.ru/",
                location = Location(
                    "9-й учебный корпус, ул. Политехническая, дом 21, Санкт-Петербург, 194021",
                    Building.EDU_9_BUILDING
                ),
                emails = arrayListOf(
                    Email("office@icc.spbstu.ru")
                ),
                phones = arrayListOf(
                    Phone(
                        number = "+7 (812) 297 16 28",
                        description = "Приёмная директора"
                    ),
                    Phone(
                        number = "+7 (812) 297 16 47",
                        description = "Деканат"
                    )
                ),
                descriptionMdFileUrl = "file:///android_asset/iimet_description.md"
            ),
            Institute(
                name = context.getString(R.string.institute_iiep),
                abbreviation = context.getString(R.string.abbreviation_iiep),
                studyPrograms = defaultStudyProgramsList,
                departments = defaultDepartmentsList,
                directorate = defaultPersonsList,
                logoDrawableId = R.drawable.iiep_logo,
                backgroundDrawableId = R.drawable.iiep_texture,
                colorId = R.color.colorIiepPrimary,
                colorLightId = R.color.colorIiepLight,
                url = "https://iamm.spbstu.ru/",
                location = Location(
                    "9-й учебный корпус, ул. Политехническая, дом 21, Санкт-Петербург, 194021",
                    Building.EDU_9_BUILDING
                ),
                emails = arrayListOf(
                    Email("office@icc.spbstu.ru")
                ),
                phones = arrayListOf(
                    Phone(
                        number = "+7 (812) 297 16 28",
                        description = "Приёмная директора"
                    ),
                    Phone(
                        number = "+7 (812) 297 16 47",
                        description = "Деканат"
                    )
                ),
                descriptionMdFileUrl = "file:///android_asset/iimet_description.md"
            ),
            Institute(
                name = context.getString(R.string.institute_iamt),
                abbreviation = context.getString(R.string.abbreviation_iamt),
                studyPrograms = defaultStudyProgramsList,
                departments = defaultDepartmentsList,
                directorate = defaultPersonsList,
                logoDrawableId = R.drawable.iamt_logo,
                backgroundDrawableId = R.drawable.iamt_texture,
                colorId = R.color.colorIamtPrimary,
                colorLightId = R.color.colorIamtLight,
                url = "https://ice.spbstu.ru/",
                location = Location(
                    "9-й учебный корпус, ул. Политехническая, дом 21, Санкт-Петербург, 194021",
                    Building.EDU_9_BUILDING
                ),
                emails = arrayListOf(
                    Email("office@icc.spbstu.ru")
                ),
                phones = arrayListOf(
                    Phone(
                        number = "+7 (812) 297 16 28",
                        description = "Приёмная директора"
                    ),
                    Phone(
                        number = "+7 (812) 297 16 47",
                        description = "Деканат"
                    )
                ),
                descriptionMdFileUrl = "file:///android_asset/iimet_description.md"
            )
        )
    }

    // Institute Menu
    fun getDefaultInstituteMenuEntranceList(
        context: Context,
        institute: Institute
    ): ArrayList<MenuListItem<Institute>> {
        return object : ArrayList<MenuListItem<Institute>>() {
            init {
                add(
                    MenuListItem(
                        context.getString(R.string.institute_content_about),
                        R.drawable.ic_institute,
                        institute.colorId,
                        institute.colorLightId,
                        Constants.ACTION_ABOUT_INSTITUTE,
                        institute
                    )
                )
                add(
                    MenuListItem(
                        context.getString(R.string.institute_content_study_programs),
                        R.drawable.ic_study_programs,
                        institute.colorId,
                        institute.colorLightId,
                        Constants.ACTION_STUDY_PROGRAMS,
                        institute
                    )
                )
                add(
                    MenuListItem(
                        context.getString(R.string.institute_content_departments),
                        R.drawable.ic_structure,
                        institute.colorId,
                        institute.colorLightId,
                        Constants.ACTION_DEPARTMENTS,
                        institute
                    )
                )
                add(
                    MenuListItem(
                        context.getString(R.string.institute_content_directorate),
                        R.drawable.ic_personalities,
                        institute.colorId,
                        institute.colorLightId,
                        Constants.ACTION_PERSONALITIES,
                        institute
                    )
                )
                add(
                    MenuListItem(
                        context.getString(R.string.institute_content_contacts),
                        R.drawable.ic_contacts,
                        institute.colorId,
                        institute.colorLightId,
                        Constants.ACTION_CONTACTS,
                        institute
                    )
                )
                add(
                    MenuListItem(
                        context.getString(R.string.institute_content_show_on_map),
                        R.drawable.ic_map,
                        institute.colorId,
                        institute.colorLightId,
                        Constants.ACTION_SHOW_ON_MAP,
                        institute
                    )
                )
            }
        }
    }
}
