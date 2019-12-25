package ru.spbstu.abit.util

import android.util.Log
import ru.spbstu.abit.BuildConfig

/**
 * Functions only for debug builds
 */
object DebugUtils {
    /**
     * Types "debug" logs without exceptions desc only in debug version
     */
    fun logd(tag: String, string: String) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, string)
        }
    }

    /**
     * Types "debug" logs with exceptions desc only in debug version
     */
    fun logd(tag: String, string: String, exception: Exception) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, string, exception)
        }
    }

    /**
     * Types "error" logs without exceptions desc only in debug version
     */
    fun loge(tag: String, string: String) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, string)
        }
    }

    /**
     * Types "error" logs with exceptions desc only in debug version
     */
    fun loge(tag: String, string: String, exception: Exception) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, string, exception)
        }
    }

    /**
     * Types "verbose" logs without exceptions desc only in debug version
     */
    fun logv(tag: String, string: String) {
        if (BuildConfig.DEBUG) {
            Log.v(tag, string)
        }
    }

    /**
     * Types "verbose" logs with exceptions desc only in debug version
     */
    fun logv(tag: String, string: String, exception: Exception) {
        if (BuildConfig.DEBUG) {
            Log.v(tag, string, exception)
        }
    }

    /**
     * Types "info" logs without exceptions desc only in debug version
     */
    fun logi(tag: String, string: String) {
        if (BuildConfig.DEBUG) {
            Log.i(tag, string)
        }
    }

    /**
     * Types "info" logs with exceptions desc only in debug version
     */
    fun logi(tag: String, string: String, exception: Exception) {
        if (BuildConfig.DEBUG) {
            Log.i(tag, string, exception)
        }
    }

    /**
     * Types "warning" logs without exceptions desc only in debug version
     */
    fun logw(tag: String, string: String) {
        if (BuildConfig.DEBUG) {
            Log.w(tag, string)
        }
    }

    /**
     * Types "warning" logs with exceptions desc only in debug version
     */
    fun logw(tag: String, string: String, exception: Exception) {
        if (BuildConfig.DEBUG) {
            Log.w(tag, string, exception)
        }
    }

    /**
     * Types "assert" logs without exceptions desc only in debug version
     */
    fun logwtf(tag: String, string: String) {
        if (BuildConfig.DEBUG) {
            Log.wtf(tag, string)
        }
    }

    /**
     * Types "assert" logs with exceptions desc only in debug version
     */
    fun logwtf(tag: String, string: String, exception: Exception) {
        if (BuildConfig.DEBUG) {
            Log.wtf(tag, string, exception)
        }
    }
}