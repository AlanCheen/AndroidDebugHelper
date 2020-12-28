package me.yifeiyuan.adh

import android.app.Fragment
import android.app.FragmentManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.view.View

/**
 * Created by 程序亦非猿 on 2020/12/8.
 */
@RequiresApi(Build.VERSION_CODES.O)
class AdhFragmentLifecycleCallbacks : FragmentManager.FragmentLifecycleCallbacks() {

    override fun onFragmentPreAttached(fm: FragmentManager, f: Fragment, context: Context) {
        super.onFragmentPreAttached(fm, f, context)
        AdhLogger.log("onFragmentPreAttached() called with: fm = $fm, f = $f, context = $context")
    }

    override fun onFragmentAttached(fm: FragmentManager, f: Fragment, context: Context) {
        super.onFragmentAttached(fm, f, context)
        AdhLogger.log("onFragmentAttached() called with: fm = $fm, f = $f, context = $context")
    }

    override fun onFragmentPreCreated(
        fm: FragmentManager,
        f: Fragment,
        savedInstanceState: Bundle?
    ) {
        super.onFragmentPreCreated(fm, f, savedInstanceState)
        AdhLogger.log(
            "onFragmentPreCreated() called with: fm = $fm, f = $f, savedInstanceState = $savedInstanceState"
        )
    }

    override fun onFragmentCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) {
        super.onFragmentCreated(fm, f, savedInstanceState)
        AdhLogger.log(
            "onFragmentCreated() called with: fm = $fm, f = $f, savedInstanceState = $savedInstanceState"
        )
    }

    override fun onFragmentActivityCreated(
        fm: FragmentManager,
        f: Fragment,
        savedInstanceState: Bundle?
    ) {
        super.onFragmentActivityCreated(fm, f, savedInstanceState)
        AdhLogger.log(
            "onFragmentActivityCreated() called with: fm = $fm, f = $f, savedInstanceState = $savedInstanceState"
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
            "onFragmentViewCreated() called with: fm = $fm, f = $f, v = $v, savedInstanceState = $savedInstanceState"
        )
    }

    override fun onFragmentStarted(fm: FragmentManager, f: Fragment) {
        super.onFragmentStarted(fm, f)
        AdhLogger.log("onFragmentStarted() called with: fm = $fm, f = $f")
    }

    override fun onFragmentResumed(fm: FragmentManager, f: Fragment) {
        super.onFragmentResumed(fm, f)
        AdhLogger.log("onFragmentResumed() called with: fm = $fm, f = $f")
    }

    override fun onFragmentPaused(fm: FragmentManager, f: Fragment) {
        super.onFragmentPaused(fm, f)
        AdhLogger.log("onFragmentPaused() called with: fm = $fm, f = $f")
    }

    override fun onFragmentStopped(fm: FragmentManager, f: Fragment) {
        super.onFragmentStopped(fm, f)
        AdhLogger.log("onFragmentStopped() called with: fm = $fm, f = $f")
    }

    override fun onFragmentSaveInstanceState(fm: FragmentManager, f: Fragment, outState: Bundle) {
        super.onFragmentSaveInstanceState(fm, f, outState)
        AdhLogger.log(
            "onFragmentSaveInstanceState() called with: fm = $fm, f = $f, outState = $outState"
        )
    }

    override fun onFragmentViewDestroyed(fm: FragmentManager, f: Fragment) {
        super.onFragmentViewDestroyed(fm, f)
        AdhLogger.log("onFragmentViewDestroyed() called with: fm = $fm, f = $f")
    }

    override fun onFragmentDestroyed(fm: FragmentManager, f: Fragment) {
        super.onFragmentDestroyed(fm, f)
        AdhLogger.log("onFragmentDestroyed: ")
    }

    override fun onFragmentDetached(fm: FragmentManager, f: Fragment) {
        super.onFragmentDetached(fm, f)
        AdhLogger.log("onFragmentDetached() called with: fm = $fm, f = $f")
    }
}