package me.yifeiyuan.adh.dev

import android.app.Application
import me.yifeiyuan.adh.AdhLogger
import me.yifeiyuan.adh.DebugConfig
import me.yifeiyuan.adh.DebugHelper

/**
 * Created by 程序亦非猿 on 2020/12/8.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            DebugConfig().apply {
                application = this@App
                strictMode = true
                detectActivityLifecycle = true
                detectFragmentLifecycle = true
                logLevel = DebugHelper.LogLevel.I
            }.also {
                DebugHelper.setup(it)
            }
        }

        AdhLogger.log("test",true)
    }
}