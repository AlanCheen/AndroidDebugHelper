package me.yifeiyuan.adh.dev

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button


private const val TAG = "ViewOnClickTestActivity"

class ViewOnClickTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        Log.d(TAG, "before onCreate: ")
        super.onCreate(savedInstanceState)
        Log.d(TAG, "after onCreate: ")
        setContentView(R.layout.activity_view_on_click_test)
        Log.d(TAG, "onCreate: setContentView")

        findViewById<Button>(R.id.button).setOnClickListener {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                Log.d(TAG, "R.id.button clicked : ${it.accessibilityDelegate}")
            }
        }

        findViewById<View>(R.id.view).setOnClickListener {
            Log.d(TAG, "R.id.view clicked")
        }

    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
    }
}