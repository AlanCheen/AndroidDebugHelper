package me.yifeiyuan.adh

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.support.v4.app.FragmentActivity

/**
 * Created by 程序亦非猿 on 2020/12/8.
 */
class AdhActivityLifecycleCallbacks : Application.ActivityLifecycleCallbacks {

    var adhSupportFragmentLifecycleCallbacks: AdhSupportFragmentLifecycleCallbacks =
        AdhSupportFragmentLifecycleCallbacks()

    var adhFragmentLifecycleCallbacks: AdhFragmentLifecycleCallbacks? = null

    init {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            adhFragmentLifecycleCallbacks = AdhFragmentLifecycleCallbacks()
        }
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        AdhLogger.log("onActivityCreated() called with: activity = $activity, savedInstanceState = $savedInstanceState")

        if (DebugHelper.config.detectFragmentLifecycle) {
            if (activity is FragmentActivity) {
                activity.supportFragmentManager.registerFragmentLifecycleCallbacks(
                    adhSupportFragmentLifecycleCallbacks,
                    true
                )
            } else {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    adhFragmentLifecycleCallbacks?.let {
                        activity.fragmentManager.registerFragmentLifecycleCallbacks(
                            adhFragmentLifecycleCallbacks,
                            true
                        )
                    }
                }
            }
        }
    }

    override fun onActivityStarted(activity: Activity) {
        AdhLogger.log("onActivityStarted() called with: activity = $activity")
    }

    override fun onActivityResumed(activity: Activity) {
        AdhLogger.log("onActivityResumed() called with: activity = $activity")
    }

    override fun onActivityPaused(activity: Activity) {
        AdhLogger.log("onActivityPaused() called with: activity = $activity")
    }

    override fun onActivityStopped(activity: Activity) {
        AdhLogger.log("onActivityStopped() called with: activity = $activity")
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        AdhLogger.log("onActivitySaveInstanceState() called with: activity = $activity, outState = $outState")
    }

    override fun onActivityDestroyed(activity: Activity) {
        AdhLogger.log("onActivityDestroyed() called with: activity = $activity")

        if (DebugHelper.config.detectFragmentLifecycle) {
            if (activity is FragmentActivity) {
                activity.supportFragmentManager.unregisterFragmentLifecycleCallbacks(
                    adhSupportFragmentLifecycleCallbacks
                )
            } else {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    adhFragmentLifecycleCallbacks?.let {
                        activity.fragmentManager.unregisterFragmentLifecycleCallbacks(
                            adhFragmentLifecycleCallbacks
                        )
                    }
                }
            }
        }
    }
}