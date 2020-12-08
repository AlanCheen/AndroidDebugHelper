package me.yifeiyuan.adh

import android.app.Application

/**
 * Created by 程序亦非猿 on 2020/12/7.
 */
class DebugConfig {

    var strictModeEnable: Boolean = true

    var detectActivityLifecycle: Boolean = true
    var detectFragmentLifecycle: Boolean = true

    lateinit var application: Application


}