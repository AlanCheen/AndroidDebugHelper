package me.yifeiyuan.adh

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.support.v4.app.FragmentActivity

/**
 * Created by 程序亦非猿 on 2020/12/8.
 */

private const val TAG_SUFFIX = "Lifecycle-Activity"

internal class AdhActivityLifecycleCallbacks : Application.ActivityLifecycleCallbacks {

    var adhSupportFragmentLifecycleCallbacks: AdhSupportFragmentLifecycleCallbacks =
        AdhSupportFragmentLifecycleCallbacks()

    var adhFragmentLifecycleCallbacks: AdhFragmentLifecycleCallbacks? = null

    init {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            adhFragmentLifecycleCallbacks = AdhFragmentLifecycleCallbacks()
        }
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        if (DebugHelper.config.detectActivityLifecycle) {
            AdhLogger.log("onActivityCreated() called with: activity = $activity, savedInstanceState = $savedInstanceState",tagSuffix = TAG_SUFFIX)
        }

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

        if (DebugHelper.config.detectViewOnClick) {
            AdhAccessibilityDelegate.install(activity)
        }

    }

    override fun onActivityStarted(activity: Activity) {
        if (DebugHelper.config.detectActivityLifecycle) {
            AdhLogger.log("onActivityStarted() called with: activity = $activity",tagSuffix = TAG_SUFFIX)
        }
    }

    override fun onActivityResumed(activity: Activity) {
        if (DebugHelper.config.detectActivityLifecycle) {
            AdhLogger.log("onActivityResumed() called with: activity = $activity",tagSuffix = TAG_SUFFIX)
        }
    }

    override fun onActivityPaused(activity: Activity) {
        if (DebugHelper.config.detectActivityLifecycle) {
            AdhLogger.log("onActivityPaused() called with: activity = $activity",tagSuffix = TAG_SUFFIX)
        }
    }

    override fun onActivityStopped(activity: Activity) {
        if (DebugHelper.config.detectActivityLifecycle) {
            AdhLogger.log("onActivityStopped() called with: activity = $activity",tagSuffix = TAG_SUFFIX)
        }
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        if (DebugHelper.config.detectActivityLifecycle) {
            AdhLogger.log("onActivitySaveInstanceState() called with: activity = $activity, outState = $outState",tagSuffix = TAG_SUFFIX)
        }
    }

    override fun onActivityDestroyed(activity: Activity) {
        if (DebugHelper.config.detectActivityLifecycle) {
            AdhLogger.log("onActivityDestroyed() called with: activity = $activity",tagSuffix = TAG_SUFFIX)
        }

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