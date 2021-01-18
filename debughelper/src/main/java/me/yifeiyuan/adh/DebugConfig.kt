package me.yifeiyuan.adh

import android.app.Application


/**
 * Created by 程序亦非猿 on 2020/12/7.
 */
class DebugConfig {

    /**
     * 是否可调试，总开关，默认开启
     */
    var debuggable = true

    lateinit var application: Application

    /**
     * 日志等级
     */
    var logLevel: LogLevel = LogLevel.D

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
     * 是否打开 Binder.enableTracing()
     */
    var tracingBinder: Boolean = false

    /**
     * 日志等级
     */
    sealed class LogLevel {
        object W : LogLevel()
        object V : LogLevel()
        object D : LogLevel()
        object E : LogLevel()
        object I : LogLevel()
        object WTF : LogLevel()

        /**
         * StackTrace 打印堆栈信息
         * 和 E 一样使用 Log.e
         */
        object ST : LogLevel()
    }
}