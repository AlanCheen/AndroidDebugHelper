package me.yifeiyuan.adh.showcase

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import me.yifeiyuan.adh.R

abstract class AdhShowcaseActivity : AppCompatActivity() , AdhShowcaseProvider{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.adh_activity_showcase)
    }
}