package ru.spbstu.abit

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.spbstu.abit.base.BaseActivity
import ru.spbstu.abit.base.BaseFragment
import ru.spbstu.abit.base.BaseFragmentWithScrollableAppBar
import ru.spbstu.abit.base.BaseFragmentWithScrollableAppBar.Companion.ARGUMENT_COLOR
import ru.spbstu.abit.base.BaseFragmentWithScrollableAppBar.Companion.ARGUMENT_COLOR_LIGHT
import ru.spbstu.abit.base.BaseFragmentWithScrollableAppBar.Companion.ARGUMENT_DRAWABLE
import ru.spbstu.abit.base.BaseFragmentWithScrollableAppBar.Companion.ARGUMENT_LIST
import ru.spbstu.abit.base.BaseFragmentWithScrollableAppBar.Companion.ARGUMENT_TITLE
import ru.spbstu.abit.data.DefaultRepository
import ru.spbstu.abit.institutes.Institute
import ru.spbstu.abit.institutes.StructureFragment
import ru.spbstu.abit.institutes.departments.Department
import ru.spbstu.abit.institutes.departments.DepartmentsFragment
import ru.spbstu.abit.institutes.departments.detailed.DepartmentDetailedFragment
import ru.spbstu.abit.institutes.departments.detailed.DepartmentDetailedFragment.Companion.ARGUMENT_DEPARTMENT
import ru.spbstu.abit.institutes.persons.PersonsFragment
import ru.spbstu.abit.institutes.persons.detailed.PersonDetailedFragment
import ru.spbstu.abit.institutes.persons.detailed.PersonDetailedFragment.Companion.ARGUMENT_PERSON
import ru.spbstu.abit.institutes.persons.model.Person
import ru.spbstu.abit.institutes.studyprograms.StudyProgramsFragment
import ru.spbstu.abit.locations.LocationsFragment
import ru.spbstu.abit.locations.model.Building
import ru.spbstu.abit.menu.MenuFragment
import ru.spbstu.abit.menu.MenuListItem
import ru.spbstu.abit.timeline.TimelineFragment
import ru.spbstu.abit.util.DebugUtils.logd
import ru.spbstu.abit.util.DebugUtils.loge
import ru.spbstu.abit.util.DebugUtils.logw

class MainActivity : BaseActivity(),
    BaseFragmentWithScrollableAppBar.IClosingListener,
    StructureFragment.OnStructureInstituteInteractionListener,
    MenuFragment.OnMenuItemInteractionListener,
    DepartmentsFragment.OnDepartmentInteractionListener,
    PersonsFragment.OnPersonInteractionListener {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initViews()
        setContent()

        if (savedInstanceState == null) {
            switchToFragment(TIMELINE_FRAGMENT)
        }
    }

    private fun initViews() {
        bottomNavigationView = findViewById(R.id.navigation)
    }

    private fun setContent() {
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_timeline -> {
                    return@setOnNavigationItemSelectedListener switchToFragment(TIMELINE_FRAGMENT)
                }
                R.id.navigation_structure -> {
                    return@setOnNavigationItemSelectedListener switchToFragment(STRUCTURE_FRAGMENT)
                }
                R.id.navigation_locations -> {
                    return@setOnNavigationItemSelectedListener switchToFragment(LOCATIONS_FRAGMENT)
                }
                R.id.navigation_menu -> {
                    return@setOnNavigationItemSelectedListener switchToFragment(MENU_FRAGMENT)
                }
            }
            false
        }
    }

    private fun switchToFragment(id: Int): Boolean {
        clearFragments()

        var nextFragment: Fragment = TimelineFragment.newInstance()
        var nextFragmentTag = TIMELINE_FRAGMENT_TAG

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_holder)
        if (currentFragment == null) {
            nextFragment.arguments =
                Bundle().apply { putString(ARGUMENT_TITLE, getString(R.string.title_timeline)) }
            replaceFragment(nextFragment, nextFragmentTag)
            return false
        }
        logd(TAG, "Current fragment = $currentFragment")

        if (currentFragment is BaseFragment) {
            currentFragment.onBackPressed()
        }

        val bundle = Bundle()
        when (id) {
            TIMELINE_FRAGMENT -> {
                if (currentFragment is TimelineFragment) {
                    return false
                }
                bundle.putString(ARGUMENT_TITLE, getString(R.string.title_timeline))
                // timeline is selected by default
            }

            STRUCTURE_FRAGMENT -> {
                if (currentFragment is StructureFragment) {
                    return false
                }
                bundle.putString(ARGUMENT_TITLE, getString(R.string.title_structure))
                nextFragment = StructureFragment.newInstance()
                nextFragmentTag = STRUCTURE_FRAGMENT_TAG
            }

            LOCATIONS_FRAGMENT -> {
                /*if (currentFragment is LocationsFragment) {
                    return false
                }*/
                bundle.putString(ARGUMENT_TITLE, getString(R.string.title_locations))
                nextFragment = LocationsFragment.newInstance()
                nextFragmentTag = LOCATIONS_FRAGMENT_TAG
            }

            MENU_FRAGMENT -> {
                bundle.putString(ARGUMENT_TITLE, getString(R.string.title_questions))
                nextFragment = MenuFragment.newInstance()
                nextFragmentTag = MENU_FRAGMENT_TAG
            }
        }
        nextFragment.arguments = bundle

        replaceFragment(nextFragment, nextFragmentTag)

        return true
    }

    private fun addFragment(id: Int, bundle: Bundle) {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_holder)
        if (currentFragment == null) {
            loge(TAG, getString(R.string.error_fragment_is_null))
            Toast.makeText(
                this,
                getString(R.string.error_fragment_is_null),
                Toast.LENGTH_LONG
            ).show()

            return
        }

        var nextFragmentTag = MENU_FRAGMENT_TAG
        var nextFragment: Fragment = MenuFragment.newInstance()

        when (id) {
            STUDY_PROGRAMS_FRAGMENT -> {
                nextFragment = StudyProgramsFragment.newInstance()
                nextFragmentTag = STUDY_PROGRAMS_FRAGMENT_TAG
            }
            PERSONS_FRAGMENT -> {
                nextFragment = PersonsFragment.newInstance()
                nextFragmentTag = PERSONS_FRAGMENT_TAG
            }
            DEPARTMENTS_FRAGMENT -> {
                nextFragment = DepartmentsFragment.newInstance()
                nextFragmentTag = DEPARTMENTS_FRAGMENT_TAG
            }
            LOCATIONS_FRAGMENT -> {
                nextFragment = LocationsFragment.newInstance()
                nextFragmentTag = LOCATIONS_FRAGMENT_TAG
            }
            DEPARTMENT_DETAILED_FRAGMENT -> {
                nextFragment = DepartmentDetailedFragment.newInstance()
                nextFragmentTag = DEPARTMENT_DETAILED_FRAGMENT_TAG
            }
            PERSON_DETAILED_FRAGMENT -> {
                nextFragment = PersonDetailedFragment.newInstance()
                nextFragmentTag = PERSON_DETAILED_FRAGMENT_TAG
            }
        }
        nextFragment.arguments = bundle

        addFragment(nextFragment, nextFragmentTag)
    }

    private fun clearFragments() {
        logd(TAG, "Clearing fragments")
        for (i in 0 until supportFragmentManager.backStackEntryCount) {
            supportFragmentManager.popBackStack()
            logd(TAG, "Popped back stack")
        }
    }

    private fun replaceFragment(nextFragment: Fragment, nextFragmentTag: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_holder, nextFragment, nextFragmentTag)
            .commit()
    }

    private fun addFragment(nextFragment: Fragment, nextFragmentTag: String) {
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragment_holder, nextFragment, nextFragmentTag)
            .commit()
    }

    override fun onBackPressed() {
        val currentFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_holder) as? BaseFragment
        currentFragment?.let {
            if (!it.onBackPressed()) {
                if (supportFragmentManager.backStackEntryCount == 0) {
                    bottomNavigationView.selectedItemId = R.id.navigation_timeline
                } else {
                    super.onBackPressed()
                }
            }
            fragmentClosed()
        } ?: super.onBackPressed()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun fragmentClosed() {
        val fragment =
            supportFragmentManager.findFragmentById(R.id.fragment_holder) as? BaseFragmentWithScrollableAppBar
        logw(TAG, "Found fragment $fragment")
        fragment?.collapseAppBar()
    }

    override fun onStructureInstituteSelected(institute: Institute) {
        val instituteMenu = DefaultRepository.getDefaultInstituteMenuEntranceList(
            this, institute
        )
        val bundle = Bundle().apply {
            putParcelableArrayList(ARGUMENT_LIST, instituteMenu)
            putString(ARGUMENT_TITLE, institute.name)
            putInt(ARGUMENT_COLOR, institute.colorId)
            putInt(ARGUMENT_DRAWABLE, institute.backgroundDrawableId)
        }

        addFragment(MENU_FRAGMENT, bundle)
    }

    override fun onStudyProgramsSelected(menuListItem: MenuListItem<*>) {
        val institute = menuListItem.obj as Institute
        val bundle = Bundle().apply {
            putParcelableArrayList(ARGUMENT_LIST, institute.studyPrograms)
            putString(
                ARGUMENT_TITLE,
                "${institute.abbreviation} — ${getString(R.string.institute_content_study_programs)}"
            )
            putInt(ARGUMENT_COLOR, institute.colorId)
            putInt(ARGUMENT_COLOR_LIGHT, institute.colorLightId)
            putInt(ARGUMENT_DRAWABLE, institute.backgroundDrawableId)
        }

        addFragment(STUDY_PROGRAMS_FRAGMENT, bundle)
    }

    override fun onDepartmentsSelected(menuListItem: MenuListItem<*>) {
        val institute = menuListItem.obj as Institute
        val bundle = Bundle().apply {
            putParcelableArrayList(ARGUMENT_LIST, institute.departments)
            putString(
                ARGUMENT_TITLE,
                "${institute.abbreviation} — ${getString(R.string.institute_content_departments)}"
            )
            putInt(ARGUMENT_COLOR, institute.colorId)
            putInt(ARGUMENT_COLOR_LIGHT, institute.colorLightId)
            putInt(ARGUMENT_DRAWABLE, institute.backgroundDrawableId)
        }

        addFragment(DEPARTMENTS_FRAGMENT, bundle)
    }

    override fun onPersonalitiesSelected(menuListItem: MenuListItem<*>) {
        val institute = menuListItem.obj as Institute
        val bundle = Bundle().apply {
            putParcelableArrayList(ARGUMENT_LIST, institute.directorate)
            putString(
                ARGUMENT_TITLE,
                "${institute.abbreviation} — ${getString(R.string.institute_content_directorate)}"
            )
            putInt(ARGUMENT_COLOR, institute.colorId)
            putInt(ARGUMENT_COLOR_LIGHT, institute.colorLightId)
            putInt(ARGUMENT_DRAWABLE, institute.backgroundDrawableId)
        }

        addFragment(PERSONS_FRAGMENT, bundle)
    }

    override fun onShowOnMapSelected(menuListItem: MenuListItem<*>) {
        val institute = menuListItem.obj as Institute
        institute.location?.building?.let { showOnMap(it) }
    }

    fun showOnMap(building: Building) {
        val bundle = Bundle().apply {
            putParcelable(LocationsFragment.ARGUMENT_LOCATION, building)
        }

        addFragment(LOCATIONS_FRAGMENT, bundle)
    }

    override fun onDepartmentSelected(
        department: Department,
        colorId: Int,
        backgroundDrawableId: Int
    ) {
        val bundle = Bundle().apply {
            putString(ARGUMENT_TITLE, department.title)
            putParcelable(ARGUMENT_DEPARTMENT, department)
            putInt(ARGUMENT_COLOR, colorId)
            putInt(ARGUMENT_DRAWABLE, backgroundDrawableId)
        }

        addFragment(DEPARTMENT_DETAILED_FRAGMENT, bundle)
    }

    override fun onPersonSelected(
        person: Person,
        colorId: Int,
        backgroundDrawableId: Int
    ) {
        val bundle = Bundle().apply {
            putString(ARGUMENT_TITLE, person.fullName)
            putParcelable(ARGUMENT_PERSON, person)
            putInt(ARGUMENT_COLOR, colorId)
            putInt(ARGUMENT_DRAWABLE, backgroundDrawableId)
        }

        addFragment(PERSON_DETAILED_FRAGMENT, bundle)
    }

    fun toggleToolbarBackButton(active: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(active)
        supportActionBar?.setDisplayShowHomeEnabled(active)
    }

    companion object {
        private const val TAG = "MainActivity"

        private const val TIMELINE_FRAGMENT = 0
        private const val STRUCTURE_FRAGMENT = 1
        private const val LOCATIONS_FRAGMENT = 2
        private const val MENU_FRAGMENT = 3
        private const val STUDY_PROGRAMS_FRAGMENT = 4
        private const val DEPARTMENTS_FRAGMENT = 5
        private const val PERSONS_FRAGMENT = 6
        private const val DEPARTMENT_DETAILED_FRAGMENT = 7
        private const val PERSON_DETAILED_FRAGMENT = 8

        const val ACTIVE_TOOLBAR_BACK_BUTTON: Boolean = true
        const val INACTIVE_TOOLBAR_BACK_BUTTON: Boolean = false

        private const val TIMELINE_FRAGMENT_TAG = "TimelineFragment"
        private const val STRUCTURE_FRAGMENT_TAG = "StructureFragment"
        private const val LOCATIONS_FRAGMENT_TAG = "LocationsFragment"
        private const val MENU_FRAGMENT_TAG = "MenuFragment"
        private const val STUDY_PROGRAMS_FRAGMENT_TAG = "StudyProgramsFragment"
        private const val DEPARTMENTS_FRAGMENT_TAG = "DepartmentsFragment"
        private const val PERSONS_FRAGMENT_TAG = "PersonsFragment"
        private const val DEPARTMENT_DETAILED_FRAGMENT_TAG = "DepartmentDetailedFragment"
        private const val PERSON_DETAILED_FRAGMENT_TAG = "PersonsDetailedFragment"
    }
}