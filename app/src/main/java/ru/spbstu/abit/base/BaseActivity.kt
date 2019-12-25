package ru.spbstu.abit.base

import android.content.Intent
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.transition.AutoTransition
import ru.spbstu.abit.R
import ru.spbstu.abit.util.DeferredFragmentTransaction
import java.util.*

open class BaseActivity : AppCompatActivity() {
    private val queueDeferredFragmentTransactions = ArrayDeque<DeferredFragmentTransaction>()

    var isActivityVisible: Boolean = false

    private lateinit var fm: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fm = supportFragmentManager
    }

    override fun onResume() {
        super.onResume()

        isActivityVisible = true

        // Perform all deferred fragment transitions
        while (queueDeferredFragmentTransactions.isNotEmpty()) {
            queueDeferredFragmentTransactions.remove().commit()
        }
    }

    public override fun onPause() {
        super.onPause()

        isActivityVisible = false
    }

    override fun startActivity(intent: Intent) {
        super.startActivity(intent)
        overridePendingTransitionEnter()
    }

    override fun finish() {
        super.finish()
        overridePendingTransitionExit()
    }

    /**
     * Overrides the pending Activity transition by performing the "Enter" animation.
     */
    private fun overridePendingTransitionEnter() {
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
    }

    /**
     * Overrides the pending Activity transition by performing the "Exit" animation.
     */
    private fun overridePendingTransitionExit() {
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
    }

    fun setFragment(@IdRes id: Int, fragment: Fragment, tag: String) {
        if (isActivityVisible) {
            setExitAnimation(id)

            fm.beginTransaction()
                .replace(id, fragment, tag)
                .commit()

            postTransaction(true)
        } else {
            // In case the activity is not running anymore,
            // add the fragment transaction to the deferred transaction
            val deferredFragmentTransaction = object : DeferredFragmentTransaction() {
                override fun commit() {
                    setFragment(id, fragment, tag)
                }
            }

            deferredFragmentTransaction.containerId = id
            deferredFragmentTransaction.fragment = fragment

            queueDeferredFragmentTransactions.add(deferredFragmentTransaction)
        }
    }

    open fun addFragment(@IdRes id: Int, fragment: Fragment, tag: String) {
        if (isActivityVisible) {
            val transaction = fm.beginTransaction()
            setExitAnimation(id)
            removeFragment()
            transaction.replace(id, fragment)
                .addToBackStack(tag).commit()

            fm.beginTransaction().runOnCommit {
                postTransaction(false)
            }.commit()
        } else {
            // In case the activity is not running anymore, add the fragment transaction to the deferred transaction
            val deferredFragmentTransaction = object : DeferredFragmentTransaction() {
                override fun commit() {
                    addFragment(id, fragment, tag)
                }
            }

            deferredFragmentTransaction.containerId = id
            deferredFragmentTransaction.fragment = fragment

            queueDeferredFragmentTransactions.add(deferredFragmentTransaction)
        }
    }

    fun removeFragment(immediate: Boolean = false) {
        if (isActivityVisible) {
            if (immediate) {
                fm.popBackStackImmediate()
            } else {
                fm.popBackStack()
            }
        } else {
            // In case the activity is not running anymore,
            // add the fragment transaction to the deferred transaction
            val deferredFragmentTransaction = object : DeferredFragmentTransaction() {
                override fun commit() {
                    removeFragment(immediate)
                }
            }
            queueDeferredFragmentTransactions.add(deferredFragmentTransaction)
        }
    }

    // set Exit transition for the current visible fragment
    private fun setExitAnimation(@IdRes id: Int) {
        // set Exit transition for the current visible fragment
        val exitFragment = fm.findFragmentById(id)
        if (exitFragment != null && exitFragment.isVisible) {
            exitFragment.exitTransition = AutoTransition()
        }
    }

    open fun postTransaction(enabled: Boolean) {}

    companion object {
        private const val TAG = "BaseActivity"
    }
}