package ru.spbstu.abit.menu

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.bottom_sheet_menu_item.view.*
import kotlinx.android.synthetic.main.fragment_menu.view.*
import ru.spbstu.abit.MainActivity.Companion.ACTIVE_TOOLBAR_BACK_BUTTON
import ru.spbstu.abit.MainActivity.Companion.INACTIVE_TOOLBAR_BACK_BUTTON
import ru.spbstu.abit.R
import ru.spbstu.abit.base.BaseFragmentWithScrollableAppBar
import ru.spbstu.abit.data.DefaultRepository
import ru.spbstu.abit.institutes.Institute
import ru.spbstu.abit.util.Constants.ACTION_ABOUT_INSTITUTE
import ru.spbstu.abit.util.Constants.ACTION_CONTACTS
import ru.spbstu.abit.util.Constants.ACTION_DEPARTMENTS
import ru.spbstu.abit.util.Constants.ACTION_PERSONALITIES
import ru.spbstu.abit.util.Constants.ACTION_SHOW_FAQ_ANSWER_CONTACTS
import ru.spbstu.abit.util.Constants.ACTION_SHOW_FAQ_ANSWER_DOCUMENTS
import ru.spbstu.abit.util.Constants.ACTION_SHOW_FAQ_ANSWER_DORMS
import ru.spbstu.abit.util.Constants.ACTION_SHOW_ON_MAP
import ru.spbstu.abit.util.Constants.ACTION_STUDY_PROGRAMS
import us.feras.mdv.MarkdownView

class MenuFragment: BaseFragmentWithScrollableAppBar(),
    MenuItemsRecyclerAdapter.OnMenuAdapterInteractionListener {

    private var listener: OnMenuItemInteractionListener? = null

    private var usingAsMainMenu = true
    private lateinit var menuListItems: List<MenuListItem<Parcelable>>

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is OnMenuItemInteractionListener) {
            listener = context
        } else {
            Log.e(TAG, "[onAttach] $context not implementing OnMenuItemInteractionListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainView = inflater.inflate(
            R.layout.fragment_menu,
            container,
            false
        )

        parseArguments()
        initViews()
        initToolbar()
        setContent()

        return mainView
    }

    override fun parseArguments() {
        super.parseArguments()
        arguments?.let {
            if (it.containsKey(ARGUMENT_LIST)) {
                menuListItems = it.getParcelableArrayList(ARGUMENT_LIST) ?: DefaultRepository.getDefaultMenuEntranceList(activity)
                usingAsMainMenu = false
            }
        }
    }

    override fun initViews() {
        super.initViews()
        with(mainView) {
            recyclerView = menu_recycler
            bottomSheet = bottom_sheet_main_menu
            bottomSheetCloseIcon = bottom_sheet_main_menu_close_icon
        }
    }

    override fun initToolbar() {
        activity.setSupportActionBar(toolbar)

        if (!usingAsMainMenu) {
            scaleAppbarHeight()
            activity.toggleToolbarBackButton(ACTIVE_TOOLBAR_BACK_BUTTON)
        }
        setToolbarSpannableTitle(
            subtractColorId,
            subtractDrawableId
        )
    }

    override fun setContent() {
        super.setContent()

        if (!::menuListItems.isInitialized) {
            menuListItems = DefaultRepository.getDefaultMenuEntranceList(activity)
        }

        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN

        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = MenuItemsRecyclerAdapter(
            menuListItems,
            this
        )

        bottomSheetCloseIcon.setOnClickListener { closeBottomSheet() }
    }

    override fun onBackPressed(): Boolean {
        return closeBottomSheet()
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onDestroyView() {
        super.onDestroyView()

        if (!usingAsMainMenu) {
            activity.toggleToolbarBackButton(INACTIVE_TOOLBAR_BACK_BUTTON)
        }
    }

    override fun onMenuItemSelected(menuListItem: MenuListItem<*>) {
        closeBottomSheet()
        val title = bottomSheet.findViewById<TextView>(R.id.bottom_sheet_main_menu_title_text)
        val description = bottomSheet.findViewById<MarkdownView>(R.id.bottom_sheet_main_menu_description)
        listener?.let {
            when (menuListItem.action) {
                ACTION_ABOUT_INSTITUTE -> {
                    title.text = getString(R.string.institute_content_about)
                    description.loadMarkdownFile((menuListItem.obj as Institute).descriptionMdFileUrl)
                    Handler().postDelayed({ bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED) }, 250)
                }
                ACTION_STUDY_PROGRAMS -> it.onStudyProgramsSelected(menuListItem)
                ACTION_DEPARTMENTS -> it.onDepartmentsSelected(menuListItem)
                ACTION_PERSONALITIES -> it.onPersonalitiesSelected(menuListItem)
                ACTION_CONTACTS -> {
                    title.text = getString(R.string.institute_content_contacts)
                    description.loadMarkdownFile("file:///android_asset/faq_contacts.md")
                    Handler().postDelayed({ bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED) }, 250)
                }
                ACTION_SHOW_ON_MAP -> it.onShowOnMapSelected(menuListItem)
                ACTION_SHOW_FAQ_ANSWER_DOCUMENTS -> {
                    title.text = menuListItem.title
                    description.loadMarkdownFile("file:///android_asset/faq_documents.md")
                    Handler().postDelayed({ bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED) }, 150)
                }
                ACTION_SHOW_FAQ_ANSWER_CONTACTS -> {
                    title.text = menuListItem.title
                    description.loadMarkdownFile("file:///android_asset/faq_contacts.md")
                    Handler().postDelayed({ bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED) }, 150)
                }
                ACTION_SHOW_FAQ_ANSWER_DORMS -> {
                    title.text = menuListItem.title
                    description.loadMarkdownFile("file:///android_asset/faq_dorms.md")
                    Handler().postDelayed({ bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED) }, 150)
                }
                else -> {
                    title.text = menuListItem.title
                    description.loadMarkdown("**Work in Progress**")
                    Handler().postDelayed({ bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED) }, 50)
                }
            }
        }
    }

    interface OnMenuItemInteractionListener {
        fun onStudyProgramsSelected(menuListItem: MenuListItem<*>)
        fun onDepartmentsSelected(menuListItem: MenuListItem<*>)
        fun onPersonalitiesSelected(menuListItem: MenuListItem<*>)
        fun onShowOnMapSelected(menuListItem: MenuListItem<*>)
    }

    companion object {
        private const val TAG = "MenuFragment"

        @JvmStatic
        fun newInstance(): MenuFragment {
            return MenuFragment()
        }
    }
}