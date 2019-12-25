package ru.spbstu.abit.locations

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.view.*
import android.widget.TextView
import androidx.appcompat.widget.PopupMenu
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.model.*
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.bottom_sheet_location_marker.view.*
import kotlinx.android.synthetic.main.bottom_sheet_location_only_title.view.*
import kotlinx.android.synthetic.main.fragment_locations.view.*
import ru.spbstu.abit.MainActivity.Companion.ACTIVE_TOOLBAR_BACK_BUTTON
import ru.spbstu.abit.MainActivity.Companion.INACTIVE_TOOLBAR_BACK_BUTTON
import ru.spbstu.abit.R
import ru.spbstu.abit.base.BaseFragment
import ru.spbstu.abit.data.DefaultRepository.ALL_BUILDINGS
import ru.spbstu.abit.locations.model.Building
import ru.spbstu.abit.util.getColorId
import ru.spbstu.abit.util.setPtSansTypeface
import us.feras.mdv.MarkdownView

class LocationsFragment : BaseFragment() {

    private lateinit var mainView: View
    private lateinit var toolBar: Toolbar
    private lateinit var mapView: MapView
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<*>
    private lateinit var bottomSheetOnlyTitleBehavior: BottomSheetBehavior<*>
    private lateinit var bottomSheet: View
    private lateinit var bottomSheetOnlyTitle: View
    private lateinit var bottomSheetText: MarkdownView

    private lateinit var googleMap: GoogleMap
    private lateinit var defaultBuilding: Building
    private lateinit var bmpSelected: BitmapDescriptor
    private lateinit var bmpUnselected: BitmapDescriptor

    private lateinit var lastMarker: Marker

    private var markersWithEnrollment: MutableMap<Marker, String> = mutableMapOf()
    private var markersOthers: MutableList<Marker> = mutableListOf()

    private var filterState: Int = FILTER_BOTH

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainView = inflater.inflate(
            R.layout.fragment_locations,
            container,
            false
        )

        setHasOptionsMenu(true)

        parseArguments()
        initViews()
        initToolbar()
        mapView.onCreate(savedInstanceState)
        setContent()

        return mainView
    }

    override fun parseArguments() {
        arguments?.let {
            if (it.containsKey(ARGUMENT_LOCATION)) {
                defaultBuilding = it.getParcelable(ARGUMENT_LOCATION) ?: Building.MAIN_BUILDING
            }
        }
    }

    override fun initViews() {
        with(mainView) {
            toolBar = toolbar
            mapView = fragment_locations_map_view
            bottomSheet = bottom_sheet_map
            bottomSheetOnlyTitle = bottom_sheet_map_only_title
            bottomSheetText = bottom_sheet_map_description
        }
    }

    override fun initToolbar() {
        setPtSansTypeface(toolBar)
        activity.setSupportActionBar(toolBar)
        if (!::defaultBuilding.isInitialized) {
            activity.toggleToolbarBackButton(INACTIVE_TOOLBAR_BACK_BUTTON)
        } else {
            activity.toggleToolbarBackButton(ACTIVE_TOOLBAR_BACK_BUTTON)
        }
        paintStatusBar(getColorId(activity, R.color.colorWhite))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.locations, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.filter_locations -> {
                showPopup(activity.findViewById(R.id.filter_locations))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun setContent() {
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetOnlyTitleBehavior = BottomSheetBehavior.from(bottomSheetOnlyTitle)

        mapView.onResume() // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(activity)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // For showing a move to my location button
        if (
            ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                activity,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ),
                REQUEST_LOCATION
            )
        } else {
            initMapView()
        }

        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        bottomSheetOnlyTitleBehavior.state = BottomSheetBehavior.STATE_HIDDEN
    }

    override fun onBackPressed(): Boolean {
        return closeBottomSheet()
    }

    private fun initMapView() {
        bmpUnselected = BitmapDescriptorFactory.fromResource(R.drawable.map_marker_unselected)
        bmpSelected = BitmapDescriptorFactory.fromResource(R.drawable.map_marker_selected)

        mapView.getMapAsync { mMap ->
            googleMap = mMap

            if (ActivityCompat.checkSelfPermission(
                    activity,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    activity,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return@getMapAsync
            }
            googleMap.isMyLocationEnabled = true
            for (building in ALL_BUILDINGS) {
                val marker = googleMap.addMarker(
                    MarkerOptions()
                        .position(building.latLng)
                        .title(building.addressName)
                        .icon(bmpUnselected)
                        .flat(true)
                )
                building.markdownDescription?.let {
                    markersWithEnrollment[marker] = it
                } ?: markersOthers.add(marker)
            }

            val zoom = if (!::defaultBuilding.isInitialized) {
                defaultBuilding = Building.MAIN_BUILDING
                16.0F
            } else {
                for (marker in markersWithEnrollment.keys) {
                    if (marker.position == defaultBuilding.latLng) {
                        showBottomSheet(marker)
                        break
                    }
                }
                for (marker in markersOthers) {
                    if (marker.position == defaultBuilding.latLng) {
                        showBottomSheet(marker)
                        break
                    }
                }
                18.0F
            }

            val defaultLocationLatLng = defaultBuilding.latLng
            val cameraPosition =
                CameraPosition.Builder().target(defaultLocationLatLng).zoom(zoom).build()
            googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))

            googleMap.setOnMarkerClickListener { marker ->
                run {
                    deselectLastMarker()
                    marker.setIcon(bmpSelected)
                    lastMarker = marker
                    showBottomSheet(marker)
                }
            }
            googleMap.setOnMapClickListener {
                deselectLastMarker()
                closeBottomSheet()
            }
        }
    }

    private fun deselectLastMarker() {
        if (::lastMarker.isInitialized && ::bmpUnselected.isInitialized) {
            lastMarker.setIcon(bmpUnselected)
        }
    }

    private fun showBottomSheet(marker: Marker): Boolean {
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        bottomSheetOnlyTitleBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        val position = CameraPosition.Builder().target(marker.position).zoom(16.0F).build()
        val update = CameraUpdateFactory.newCameraPosition(position)
        googleMap.animateCamera(update, 300, null)

        var title: TextView = bottomSheet.findViewById(R.id.bottom_sheet_map_title_text)
        title.text = marker.title
        title = bottomSheetOnlyTitle.findViewById(R.id.bottom_sheet_map_title_text)
        title.text = marker.title

        if (markersWithEnrollment.containsKey(marker)) {
            bottomSheetText.loadMarkdown(markersWithEnrollment[marker])
            Handler().postDelayed(
                { bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED) },
                300
            )
        } else {
            Handler().postDelayed(
                { bottomSheetOnlyTitleBehavior.setState(BottomSheetBehavior.STATE_EXPANDED) },
                300
            )
        }

        return true
    }

    private fun closeBottomSheet(): Boolean {
        deselectLastMarker()

        if (bottomSheetBehavior.state != BottomSheetBehavior.STATE_HIDDEN) {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
            return true
        }

        if (bottomSheetOnlyTitleBehavior.state != BottomSheetBehavior.STATE_HIDDEN) {
            bottomSheetOnlyTitleBehavior.state = BottomSheetBehavior.STATE_HIDDEN
            return true
        }

        return false
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    private fun showPopup(view: View) {
        val popup: PopupMenu = PopupMenu(activity, view)
        popup.inflate(R.menu.locations_filter_options)

        when (filterState) {
            FILTER_NONE -> {
                popup.menu.getItem(0).isChecked = false
                popup.menu.getItem(1).isChecked = false
            }
            FILTER_ENROLLMENT -> {
                popup.menu.getItem(0).isChecked = true
                popup.menu.getItem(1).isChecked = false
            }
            FILTER_OTHERS -> {
                popup.menu.getItem(0).isChecked = false
                popup.menu.getItem(1).isChecked = true
            }
            FILTER_BOTH -> {
                popup.menu.getItem(0).isChecked = true
                popup.menu.getItem(1).isChecked = true
            }
        }

        popup.setOnMenuItemClickListener { item: MenuItem ->
            when (item.itemId) {
                R.id.filter_locations_with_enroll -> {
                    if (item.isChecked) {
                        item.isChecked = false
                        filterState = if (filterState == FILTER_BOTH) {
                            FILTER_OTHERS
                        } else {
                            // should be FILTER_ENROLLMENT
                            FILTER_NONE
                        }
                        for (marker in markersWithEnrollment.keys) {
                            marker.isVisible = false
                        }
                    } else {
                        item.isChecked = true
                        filterState = if (filterState == FILTER_OTHERS) {
                            FILTER_BOTH
                        } else {
                            // should be FILTER_NONE
                            FILTER_ENROLLMENT
                        }
                        for (marker in markersWithEnrollment.keys) {
                            marker.isVisible = true
                        }
                    }
                }
                R.id.filter_locations_others -> {
                    if (item.isChecked) {
                        item.isChecked = false
                        filterState = if (filterState == FILTER_BOTH) {
                            FILTER_ENROLLMENT
                        } else {
                            // should be FILTER_OTHERS
                            FILTER_NONE
                        }
                        for (marker in markersOthers) {
                            marker.isVisible = false
                        }
                    } else {
                        item.isChecked = true
                        filterState = if (filterState == FILTER_ENROLLMENT) {
                            FILTER_BOTH
                        } else {
                            // should be FILTER_NONE
                            FILTER_OTHERS
                        }
                        for (marker in markersOthers) {
                            marker.isVisible = true
                        }
                    }
                }
            }

            true
        }

        popup.show()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        for (res in grantResults) {
            if (res != PackageManager.PERMISSION_GRANTED) {
                return
            }
        }
        initMapView()
    }

    companion object {
        private const val TAG = "LocationsFragment"

        const val ARGUMENT_LOCATION = "building"

        private const val REQUEST_LOCATION = 1

        private const val FILTER_NONE = 0
        private const val FILTER_ENROLLMENT = 1
        private const val FILTER_OTHERS = 2
        private const val FILTER_BOTH = 3

        @JvmStatic
        fun newInstance(): LocationsFragment {
            return LocationsFragment()
        }
    }
}