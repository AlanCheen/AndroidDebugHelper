package me.yifeiyuan.adh

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.fragment.app.FragmentActivity

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
        AdhLogger.d("onActivityCreated() called with: activity = $activity, savedInstanceState = $savedInstanceState")

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
        AdhLogger.d("onActivityStarted() called with: activity = $activity")
    }

    override fun onActivityResumed(activity: Activity) {
        AdhLogger.d("onActivityResumed() called with: activity = $activity")
    }

    override fun onActivityPaused(activity: Activity) {
        AdhLogger.d("onActivityPaused() called with: activity = $activity")
    }

    override fun onActivityStopped(activity: Activity) {
        AdhLogger.d("onActivityStopped() called with: activity = $activity")
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        AdhLogger.d("onActivitySaveInstanceState() called with: activity = $activity, outState = $outState")
    }

    override fun onActivityDestroyed(activity: Activity) {
        AdhLogger.d("onActivityDestroyed() called with: activity = $activity")

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