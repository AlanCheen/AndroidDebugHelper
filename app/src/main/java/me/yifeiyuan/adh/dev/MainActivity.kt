package me.yifeiyuan.adh.dev

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import me.yifeiyuan.adh.AdhLogger
import me.yifeiyuan.adh.DebugHelper

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        Thread { AdhLogger.log("asdfa", true) }.start()
    }
}