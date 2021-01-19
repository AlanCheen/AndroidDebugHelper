package me.yifeiyuan.adh.dev

import android.view.View
import android.widget.Toast
import me.yifeiyuan.adh.AdhLogger
import me.yifeiyuan.adh.showcase.AdhShowcaseActivity
import me.yifeiyuan.adh.showcase.AdhShowcaseItem

class MainActivity : AdhShowcaseActivity() {

    override fun onResume() {
        super.onResume()
    }

    override fun provideShowcaseItems(): List<AdhShowcaseItem> {

        return mutableListOf(
            AdhShowcaseItem().apply {
                content = "测试项 1"
                clickListener = View.OnClickListener {
                    Toast.makeText(this@MainActivity, "点击了测试项 1", Toast.LENGTH_SHORT).show()
                }
            },
            AdhShowcaseItem().apply {
                content = "测试项 2"
                clickListener = View.OnClickListener {
                    Toast.makeText(this@MainActivity, "点击了测试项 2", Toast.LENGTH_SHORT).show()
                }
            },
            AdhShowcaseItem().apply {
                content = "测试 AdhLogger 异步线程弹 Toast 的能力"
                clickListener = View.OnClickListener {
                    Thread { AdhLogger.log("测试异步线程 Toast", true) }.start()
                }
            },
        )
    }
}