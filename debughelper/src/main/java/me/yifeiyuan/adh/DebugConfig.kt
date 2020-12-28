package me.yifeiyuan.adh

import android.app.Application
import me.yifeiyuan.adh.DebugHelper.LogLevel


/**
 * Created by 程序亦非猿 on 2020/12/7.
 */
class DebugConfig {

    var enableStrictMode: Boolean = true

    var detectActivityLifecycle: Boolean = true
    var detectFragmentLifecycle: Boolean = true

    var logLevel: LogLevel = LogLevel.D

    lateinit var application: Application


}