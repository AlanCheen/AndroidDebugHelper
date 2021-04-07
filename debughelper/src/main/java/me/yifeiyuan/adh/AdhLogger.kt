package me.yifeiyuan.adh

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import me.yifeiyuan.adh.DebugConfig.LogLevel

/**
 * Created by 程序亦非猿 on 2020/12/8.
 */
object AdhLogger {

    private const val TAG = "ADH-"

    internal var logLevelConfig: LogLevel = LogLevel.D

    private val mainHandler = Handler(Looper.getMainLooper())

    /**
     * @param msg 日志消息
     * @param tagSuffix 日志 Tag 后缀
     * @param logLevel 日志等级
     * @param showToast 是否弹 toast
     */
    @JvmOverloads
    @JvmStatic
    fun log(
        msg: String,
        tagSuffix: String = "",
        showToast: Boolean = false,
        logLevel: LogLevel = logLevelConfig
    ) {

        when (logLevel) {
            LogLevel.V -> {
                Log.v(TAG + tagSuffix, msg)
            }

            LogLevel.I -> {
                Log.i(TAG + tagSuffix, msg)
            }

            LogLevel.D -> {
                Log.d(TAG + tagSuffix, msg)
            }

            LogLevel.W -> {
                Log.w(TAG + tagSuffix, msg)
            }

            LogLevel.WTF -> {
                Log.wtf(TAG + tagSuffix, msg)
            }

            LogLevel.E -> {
                e(msg, tagSuffix)
            }

            LogLevel.ST -> {
                e(msg, tagSuffix, tr = NullPointerException("ADH Default Exception"))
            }
        }

        if (showToast) {
            //java.lang.IllegalAccessException: Tried to access visual service WindowManager from a non-visual Context:me.yifeiyuan.adh.dev.App@438cc05
            //似乎是开启了 StrictMode 在 Android 11 上使用 Application 访问 WindowManager 会报这个错，所以加了 try catch。
            //https://stackoverflow.com/questions/65542592/java-lang-illegalaccessexception-tried-to-access-visual-service-windowmanager-f
            val context = DebugHelper.config.application
            try {
                if (isMainThread()) {
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                } else {
                    mainHandler.post {
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    @JvmOverloads
    @JvmStatic
    fun e(msg: String, tagSuffix: String = "", tr: Throwable? = null) {
        Log.e(TAG + tagSuffix, "e: $msg", tr)
    }

    @JvmStatic
    fun logAndToast(msg: String, tagSuffix: String = "") {
        log(msg, tagSuffix, showToast = true)
    }

    @JvmStatic
    fun logAndCopy(msg: String, tagSuffix: String = "") {
        log(msg, tagSuffix)

        val clipboardManager =
            DebugHelper.config.application.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

        val clip: ClipData = ClipData.newPlainText("ADH Log", msg)
        clipboardManager.setPrimaryClip(clip)
    }

    private fun isMainThread(): Boolean = Looper.myLooper() == Looper.getMainLooper()
}