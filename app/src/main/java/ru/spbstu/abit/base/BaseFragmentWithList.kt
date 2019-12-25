package ru.spbstu.abit.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.base_fragment_with_list.view.*
import kotlinx.android.synthetic.main.bottom_sheet_menu_item.view.*
import ru.spbstu.abit.MainActivity
import ru.spbstu.abit.R
import ru.spbstu.abit.institutes.ICardActions
import ru.spbstu.abit.locations.model.Building

open class BaseFragmentWithList : BaseFragmentWithScrollableAppBar(), ICardActions {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainView = inflater.inflate(
            R.layout.base_fragment_with_list,
            container,
            false
        )

        parseArguments()
        initViews()
        initToolbar()
        setContent()

        return mainView
    }

    override fun initViews() {
        super.initViews()
        with(mainView) {
            recyclerView = list
            bottomSheet = bottom_sheet_main_menu
            bottomSheetCloseIcon = bottom_sheet_main_menu_close_icon
        }
    }

    override fun initToolbar() {
        activity.setSupportActionBar(toolbar)
        setToolbarSpannableTitle(
            subtractColorId,
            subtractDrawableId
        )
        activity.toggleToolbarBackButton(MainActivity.ACTIVE_TOOLBAR_BACK_BUTTON)

        scaleAppbarHeight()
    }

    override fun setContent() {
        super.setContent()

        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        bottomSheetCloseIcon.setOnClickListener { closeBottomSheet() }

        recyclerView.layoutManager = LinearLayoutManager(activity)
    }

    override fun onBackPressed(): Boolean {
        return closeBottomSheet()
    }

    override fun onUrlClick(url: String?) {
        url?.let { openWebPage(it) }
    }

    override fun onUrlLongClick(url: String?) {
        url?.let {
            MaterialAlertDialogBuilder(activity)
                .setTitle(getString(R.string.dialog_action_open_site))
                .setMessage(getString(R.string.dialog_site_url, it))
                .setPositiveButton(getString(R.string.yes)) { dialog, _ ->
                    onUrlClick(it)
                    dialog.dismiss()
                }
                .setNegativeButton(getString(R.string.no)) { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }

    override fun onPhoneClick(phone: String?) {
        phone?.let {
            dialPhoneNumber(it)
        }
    }

    override fun onPhoneLongClick(phone: String?) {
        phone?.let {
            MaterialAlertDialogBuilder(activity)
                .setTitle(getString(R.string.dialog_action_dial))
                .setMessage(getString(R.string.dialog_phone_number, it))
                .setPositiveButton(getString(R.string.yes)) { dialog, _ ->
                    onPhoneClick(it)
                    dialog.dismiss()
                }
                .setNegativeButton(getString(R.string.no)) { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }

    override fun onEmailClick(email: String?) {
        email?.let {
            composeEmail(it, "")
        }
    }

    override fun onEmailLongClick(email: String?) {
        email?.let {
            MaterialAlertDialogBuilder(activity)
                .setTitle(getString(R.string.dialog_action_write_email))
                .setMessage(getString(R.string.dialog_email, it))
                .setPositiveButton(getString(R.string.yes)) { dialog, _ ->
                    onEmailClick(it)
                    dialog.dismiss()
                }
                .setNegativeButton(getString(R.string.no)) { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }

    override fun onShowOnMapClick(building: Building?) {
        building?.let {
            activity.showOnMap(it)
        }
    }
}