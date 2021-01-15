package me.yifeiyuan.adh

import android.app.Application
import me.yifeiyuan.adh.DebugHelper.LogLevel


/**
 * Created by 程序亦非猿 on 2020/12/7.
 */
class DebugConfig {

    /**
     * 是否打开 Binder.enableTracing()
     */
    var tracingBinder: Boolean = false

    /**
     * 是否启用 StrictMode
     */
    var enableStrictMode: Boolean = true

    /**
     * 是否检测 Activity 生命周期并打印
     */
    var detectActivityLifecycle: Boolean = true

    /**
     * 是否检测 Fragment 生命周期并打印
     */
    var detectFragmentLifecycle: Boolean = true

    /**
     * 日志等级
     */
    var logLevel: LogLevel = LogLevel.D

    lateinit var application: Application


}