package me.yifeiyuan.adh

import android.os.StrictMode

/**
 * Created by 程序亦非猿 on 2020/12/7.
 */
object DebugHelper {

    lateinit var config :DebugConfig

    @JvmStatic
    fun setup(config :DebugConfig) {

        this.config = config

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

        if (config.detectActivityLifecycle) {
            config.application.registerActivityLifecycleCallbacks(AdhActivityLifecycleCallbacks())
        }
    }

}