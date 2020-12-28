package me.yifeiyuan.adh

/**
 * Created by 程序亦非猿 on 2020/12/28.
 */
interface Logger {

    var logLevel: DebugHelper.LogLevel

    fun log(msg: String, toast: Boolean = false)

}