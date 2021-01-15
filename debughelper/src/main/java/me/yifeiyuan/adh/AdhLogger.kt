package me.yifeiyuan.adh

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import me.yifeiyuan.adh.DebugHelper.LogLevel

/**
 * Created by 程序亦非猿 on 2020/12/8.
 */
object AdhLogger {

    private const val TAG = "ADH"

    var logLevelConfig: LogLevel = LogLevel.D

    private val mainHandler = Handler(Looper.getMainLooper())

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

            LogLevel.ST -> {
                e(msg, NullPointerException("ADH Default Exception"))
            }
        }

        if (toast) {
            val context = DebugHelper.config.application
            if (isMainThread()) {
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
            } else {
                mainHandler.post {
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    @JvmStatic
    fun e(msg: String, tr: Throwable? = null) {
        Log.e(TAG, "e: $msg", tr)
    }

    @JvmStatic
    fun logAndToast(msg: String) {
        log(msg, true)
    }

    private fun isMainThread(): Boolean = Looper.myLooper() == Looper.getMainLooper()
}