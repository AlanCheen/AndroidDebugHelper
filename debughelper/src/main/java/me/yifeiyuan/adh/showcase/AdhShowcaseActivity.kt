package me.yifeiyuan.adh.showcase

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import me.yifeiyuan.adh.R

abstract class AdhShowcaseActivity : AppCompatActivity(), AdhShowcaseProvider {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.adh_activity_showcase)
    }

    fun createShowcaseItem(
        contentStr: String,
        intent: Intent? = null,
        run: Runnable?,
    ): AdhShowcaseItem {
        return AdhShowcaseItem().apply {
            content = contentStr
            runnable = run
            targetIntent = intent
        }
    }
}