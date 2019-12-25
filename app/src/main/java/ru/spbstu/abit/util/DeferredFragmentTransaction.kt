package ru.spbstu.abit.util

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment

abstract class DeferredFragmentTransaction {
    /**
     * View's id that will hold the [fragment]
     */
    @IdRes
    var containerId: Int = 0
    var fragment: Fragment? = null

    abstract fun commit()
}