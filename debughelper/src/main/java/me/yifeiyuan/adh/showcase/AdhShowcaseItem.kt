package me.yifeiyuan.adh.showcase

import android.content.Intent
import android.view.View

/**
 * Created by 程序亦非猿 on 2021/1/19.
 */
class AdhShowcaseItem {

    var content: String = "测试项"

    var clickListener: View.OnClickListener? = null

    var targetIntent : Intent?=null
}