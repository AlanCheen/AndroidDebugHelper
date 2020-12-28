package me.yifeiyuan.adh

import android.util.Log
import android.widget.Toast
import me.yifeiyuan.adh.DebugHelper.LogLevel

/**
 * Created by 程序亦非猿 on 2020/12/8.
 */
object AdhLogger {

    private const val TAG = "AdhLogger"

    var logLevelConfig: LogLevel = LogLevel.D


    /**
     * @param msg 日志消息
     * @param logLevel 日志等级
     * @param toast 是否弹 toast
     */
    @JvmOverloads
    @JvmStatic
    fun log(msg: String, toast: Boolean = false, logLevel: LogLevel = logLevelConfig) {

        when (logLevel) {
            LogLevel.V -> {
                Log.v(TAG, "log() called with: msg = $msg ")
            }

            LogLevel.I -> {
                Log.i(TAG, "log() called with: msg = $msg ")
            }

            LogLevel.D -> {
                Log.d(TAG, "log() called with: msg = $msg ")
            }

            LogLevel.W -> {
                Log.w(TAG, "log() called with: msg = $msg ")
            }

            LogLevel.WTF -> {
                Log.wtf(TAG, "log() called with: msg = $msg ")
            }
            LogLevel.E -> {
                e(msg)
            }
        }

        if (toast) {
            val context = DebugHelper.config.application
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }
    }

    @JvmStatic
    fun d(msg: String) {
        Log.d(TAG, msg)
    }

    @JvmStatic
    @JvmOverloads
    fun e(msg: String, tr: Throwable = NullPointerException("Adh Default Exception")) {
        Log.e(TAG, "e: $msg", tr)
    }

    @JvmStatic
    fun logAndToast(msg: String) {
        log(msg, true, LogLevel.D)
    }

}