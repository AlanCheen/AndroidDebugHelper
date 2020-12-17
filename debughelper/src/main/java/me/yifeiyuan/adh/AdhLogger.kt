package me.yifeiyuan.adh

import android.util.Log

/**
 * Created by 程序亦非猿 on 2020/12/8.
 */
object AdhLogger {

    private const val TAG = "AdhLogger"

    fun d(msg: String) {
        Log.d(TAG, msg)
    }

    fun log(msg: String) {

    }

    fun e(msg: String, tr: Throwable = NullPointerException("Adh Default Exception")) {
        Log.e(TAG, "e: $msg", tr)
    }

}