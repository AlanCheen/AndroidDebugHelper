package me.yifeiyuan.adh

import android.annotation.SuppressLint
import android.os.Binder
import android.os.StrictMode
import java.lang.Exception

/**
 * Created by 程序亦非猿 on 2020/12/7.
 */
object DebugHelper {

    lateinit var config: DebugConfig

    @JvmStatic
    fun setup(config: DebugConfig) {

        this.config = config

        AdhLogger.logLevelConfig = config.logLevel

        if (!config.debuggable) {
            return
        }

        setupStrictMode(config)

        config.application.registerActivityLifecycleCallbacks(AdhActivityLifecycleCallbacks())

        if (config.tracingBinder) {
            traceBinder()
        }
    }

    private fun setupStrictMode(config: DebugConfig) {
        if (config.enableStrictMode) {
            //adb logcat | grep StrictMode
            StrictMode.setThreadPolicy(
                StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build()
            )

            StrictMode.setVmPolicy(
                StrictMode.VmPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build()
            )
        }
    }

    /**
     * adb shell settings put global hidden_api_policy_pre_p_apps  1
     */
    @SuppressLint("SoonBlockedPrivateApi")
    private fun traceBinder(){
        try {
            val method = Binder::class.java.getDeclaredMethod("enableTracing")
            method.isAccessible = true
            method.invoke(null)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}