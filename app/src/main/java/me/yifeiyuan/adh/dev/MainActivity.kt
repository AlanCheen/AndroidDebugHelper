package me.yifeiyuan.adh.dev

import android.os.Bundle
import android.widget.Toast
import me.yifeiyuan.adh.AdhLogger
import me.yifeiyuan.adh.showcase.AdhShowcaseActivity
import me.yifeiyuan.adh.showcase.AdhShowcaseItem

class MainActivity : AdhShowcaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun provideShowcaseItems(): List<AdhShowcaseItem> {

        return mutableListOf(
            createShowcaseItem("测试 logAndCopy") {
                AdhLogger.logAndCopy("测试一下 logAndCopy")
            },

            createShowcaseItem("测试 logAndToast") {
                AdhLogger.logAndToast("测试 logAndToast")
            },

            createShowcaseItem("测试 AdhLogger 异步线程弹 Toast 的能力") {
                Thread { AdhLogger.log("测试异步线程 Toast", true) }.start()
            },
            )
    }
}