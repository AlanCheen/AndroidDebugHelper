package me.yifeiyuan.adh

import android.annotation.SuppressLint
import android.os.Binder
import android.os.StrictMode
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Created by 程序亦非猿 on 2020/12/7.
 */
object DebugHelper {

    internal lateinit var config: DebugConfig
    private var initialized = AtomicBoolean(false)

    @JvmStatic
    fun setup(debugConfig: DebugConfig) {

        if (initialized.getAndSet(true)) {
            return
        }

        if (!debugConfig.debuggable) {
            return
        }

        this.config = debugConfig

        setupLogger()

        setupStrictMode()

        config.application.registerActivityLifecycleCallbacks(AdhActivityLifecycleCallbacks())

        if (debugConfig.tracingBinder) {
            traceBinder()
        }
    }

    /**
     * 配置 Logger 涉及到日志，要优先调用。
     */
    private fun setupLogger() {
        AdhLogger.logLevelConfig = config.logLevel
    }

    private fun setupStrictMode() {
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
    private fun traceBinder() {
        try {
            val method = Binder::class.java.getDeclaredMethod("enableTracing")
            method.isAccessible = true
            method.invoke(null)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}