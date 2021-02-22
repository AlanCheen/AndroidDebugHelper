package me.yifeiyuan.adh

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.View
import android.view.ViewGroup

/**
 * Created by 程序亦非猿 on 2020/12/8.
 */
private const val TAG_SUFFIX = "FragmentLifecycle"

internal class AdhSupportFragmentLifecycleCallbacks : FragmentManager.FragmentLifecycleCallbacks() {

    override fun onFragmentPreAttached(fm: FragmentManager, f: Fragment, context: Context) {
        super.onFragmentPreAttached(fm, f, context)
        AdhLogger.log("onFragmentPreAttached() called with: fm = $fm, f = $f, context = $context",tagSuffix = TAG_SUFFIX)
    }

    override fun onFragmentAttached(fm: FragmentManager, f: Fragment, context: Context) {
        super.onFragmentAttached(fm, f, context)
        AdhLogger.log("onFragmentAttached() called with: fm = $fm, f = $f, context = $context",tagSuffix = TAG_SUFFIX)
    }

    override fun onFragmentPreCreated(
        fm: FragmentManager,
        f: Fragment,
        savedInstanceState: Bundle?
    ) {
        super.onFragmentPreCreated(fm, f, savedInstanceState)
        AdhLogger.log(
            "onFragmentPreCreated() called with: fm = $fm, f = $f, savedInstanceState = $savedInstanceState",tagSuffix = TAG_SUFFIX
        )
    }

    override fun onFragmentCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) {
        super.onFragmentCreated(fm, f, savedInstanceState)
        AdhLogger.log(
            "onFragmentCreated() called with: fm = $fm, f = $f, savedInstanceState = $savedInstanceState",tagSuffix = TAG_SUFFIX
        )
    }

    override fun onFragmentActivityCreated(
        fm: FragmentManager,
        f: Fragment,
        savedInstanceState: Bundle?
    ) {
        super.onFragmentActivityCreated(fm, f, savedInstanceState)
        AdhLogger.log(
            "onFragmentActivityCreated() called with: fm = $fm, f = $f, savedInstanceState = $savedInstanceState",tagSuffix = TAG_SUFFIX
        )
    }

    override fun onFragmentViewCreated(
        fm: FragmentManager,
        f: Fragment,
        v: View,
        savedInstanceState: Bundle?
    ) {
        super.onFragmentViewCreated(fm, f, v, savedInstanceState)
        AdhLogger.log(
            "onFragmentViewCreated() called with: fm = $fm, f = $f, v = $v, savedInstanceState = $savedInstanceState",tagSuffix = TAG_SUFFIX
        )
        if (DebugHelper.config.detectViewOnClick) {
            if (v is ViewGroup) {
                AdhAccessibilityDelegate.setupAccessibilityDelegate(v)
            }
        }
    }

    override fun onFragmentStarted(fm: FragmentManager, f: Fragment) {
        super.onFragmentStarted(fm, f)
        AdhLogger.log("onFragmentStarted() called with: fm = $fm, f = $f",tagSuffix = TAG_SUFFIX)
    }

    override fun onFragmentResumed(fm: FragmentManager, f: Fragment) {
        super.onFragmentResumed(fm, f)
        AdhLogger.log("onFragmentResumed() called with: fm = $fm, f = $f",tagSuffix = TAG_SUFFIX)
    }

    override fun onFragmentPaused(fm: FragmentManager, f: Fragment) {
        super.onFragmentPaused(fm, f)
        AdhLogger.log("onFragmentPaused() called with: fm = $fm, f = $f",tagSuffix = TAG_SUFFIX)
    }

    override fun onFragmentStopped(fm: FragmentManager, f: Fragment) {
        super.onFragmentStopped(fm, f)
        AdhLogger.log("onFragmentStopped() called with: fm = $fm, f = $f",tagSuffix = TAG_SUFFIX)
    }

    override fun onFragmentSaveInstanceState(fm: FragmentManager, f: Fragment, outState: Bundle) {
        super.onFragmentSaveInstanceState(fm, f, outState)
        AdhLogger.log(
            "onFragmentSaveInstanceState() called with: fm = $fm, f = $f, outState = $outState",tagSuffix = TAG_SUFFIX
        )
    }

    override fun onFragmentViewDestroyed(fm: FragmentManager, f: Fragment) {
        super.onFragmentViewDestroyed(fm, f)
        AdhLogger.log("onFragmentViewDestroyed() called with: fm = $fm, f = $f",tagSuffix = TAG_SUFFIX)
    }

    override fun onFragmentDestroyed(fm: FragmentManager, f: Fragment) {
        super.onFragmentDestroyed(fm, f)
        AdhLogger.log("onFragmentDestroyed: ",tagSuffix = TAG_SUFFIX)
    }

    override fun onFragmentDetached(fm: FragmentManager, f: Fragment) {
        super.onFragmentDetached(fm, f)
        AdhLogger.log("onFragmentDetached() called with: fm = $fm, f = $f",tagSuffix = TAG_SUFFIX)
    }
}