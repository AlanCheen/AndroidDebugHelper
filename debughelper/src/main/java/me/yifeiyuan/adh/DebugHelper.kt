package me.yifeiyuan.adh

import android.os.StrictMode

/**
 * Created by 程序亦非猿 on 2020/12/7.
 */
object DebugHelper {

    lateinit var config: DebugConfig

    sealed class LogLevel {
        object W : LogLevel()
        object V : LogLevel()
        object D : LogLevel()
        object E : LogLevel()
        object I : LogLevel()
        object WTF : LogLevel()
    }

    @JvmStatic
    fun setup(config: DebugConfig) {

        this.config = config

        AdhLogger.logLevelConfig = config.logLevel

        setupStrictMode(config)

        if (config.detectActivityLifecycle) {
            config.application.registerActivityLifecycleCallbacks(AdhActivityLifecycleCallbacks())
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

}