package me.yifeiyuan.adh

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.View

/**
 * Created by 程序亦非猿 on 2020/12/8.
 */
class AdhSupportFragmentLifecycleCallbacks : FragmentManager.FragmentLifecycleCallbacks() {

    override fun onFragmentPreAttached(fm: FragmentManager, f: Fragment, context: Context) {
        super.onFragmentPreAttached(fm, f, context)
        AdhLogger.d("onFragmentPreAttached() called with: fm = $fm, f = $f, context = $context")
    }

    override fun onFragmentAttached(fm: FragmentManager, f: Fragment, context: Context) {
        super.onFragmentAttached(fm, f, context)
        AdhLogger.d("onFragmentAttached() called with: fm = $fm, f = $f, context = $context")
    }

    override fun onFragmentPreCreated(
        fm: FragmentManager,
        f: Fragment,
        savedInstanceState: Bundle?
    ) {
        super.onFragmentPreCreated(fm, f, savedInstanceState)
        AdhLogger.d(

            "onFragmentPreCreated() called with: fm = $fm, f = $f, savedInstanceState = $savedInstanceState"
        )
    }

    override fun onFragmentCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) {
        super.onFragmentCreated(fm, f, savedInstanceState)
        AdhLogger.d(

            "onFragmentCreated() called with: fm = $fm, f = $f, savedInstanceState = $savedInstanceState"
        )
    }

    override fun onFragmentActivityCreated(
        fm: FragmentManager,
        f: Fragment,
        savedInstanceState: Bundle?
    ) {
        super.onFragmentActivityCreated(fm, f, savedInstanceState)
        AdhLogger.d(

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
        AdhLogger.d(

            "onFragmentViewCreated() called with: fm = $fm, f = $f, v = $v, savedInstanceState = $savedInstanceState"
        )
    }

    override fun onFragmentStarted(fm: FragmentManager, f: Fragment) {
        super.onFragmentStarted(fm, f)
        AdhLogger.d("onFragmentStarted() called with: fm = $fm, f = $f")
    }

    override fun onFragmentResumed(fm: FragmentManager, f: Fragment) {
        super.onFragmentResumed(fm, f)
        AdhLogger.d("onFragmentResumed() called with: fm = $fm, f = $f")
    }

    override fun onFragmentPaused(fm: FragmentManager, f: Fragment) {
        super.onFragmentPaused(fm, f)
        AdhLogger.d("onFragmentPaused() called with: fm = $fm, f = $f")
    }

    override fun onFragmentStopped(fm: FragmentManager, f: Fragment) {
        super.onFragmentStopped(fm, f)
        AdhLogger.d("onFragmentStopped() called with: fm = $fm, f = $f")
    }

    override fun onFragmentSaveInstanceState(fm: FragmentManager, f: Fragment, outState: Bundle) {
        super.onFragmentSaveInstanceState(fm, f, outState)
        AdhLogger.d(
            "onFragmentSaveInstanceState() called with: fm = $fm, f = $f, outState = $outState"
        )
    }

    override fun onFragmentViewDestroyed(fm: FragmentManager, f: Fragment) {
        super.onFragmentViewDestroyed(fm, f)
        AdhLogger.d("onFragmentViewDestroyed() called with: fm = $fm, f = $f")
    }

    override fun onFragmentDestroyed(fm: FragmentManager, f: Fragment) {
        super.onFragmentDestroyed(fm, f)
        AdhLogger.d("onFragmentDestroyed: ")
    }

    override fun onFragmentDetached(fm: FragmentManager, f: Fragment) {
        super.onFragmentDetached(fm, f)
        AdhLogger.d("onFragmentDetached() called with: fm = $fm, f = $f")
    }
}