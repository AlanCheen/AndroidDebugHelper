package me.yifeiyuan.adh

import android.app.Activity
import android.support.annotation.RequiresApi
import android.view.*
import android.view.accessibility.AccessibilityEvent

/**
 * Created by 程序亦非猿 on 2021/2/20.
 *
 * 监听点击事件并打印。
 *
 * 遇到几个问题
 * 1.找不到非常准确的时机去设置 Activity 里的 View 的 delegate。
 * 因为在 onCreate 里其实不一定会调用 setContentView，那这样就找不到 View 去设置 delegate，尝试设置 window.callback 监听 onContentChanged 回调再设置，发现也有问题。
 * 最后改成在 window.callback.onAttachedToWindow 回调中设置才解决。
 *
 * 2.Fragment 的怎么办？
 * Fragment 需要单独处理，在 {@link AdhFragmentLifecycleCallbacks#onFragmentViewCreated()}  回调中处理。
 */

private const val TAG_SUFFIX = "OnClick"

class AdhAccessibilityDelegate : View.AccessibilityDelegate() {

    companion object {

        fun install(activity: Activity) {
            val oriCallback = activity.window.callback
            activity.window.callback = WindowCallbackWrapper(oriCallback, activity)
//            val decorView = activity.window.decorView
//            setupAccessibilityDelegate(decorView as ViewGroup)
        }

        fun setupAccessibilityDelegate(viewGroup: ViewGroup) {

            val childCount = viewGroup.childCount

            for (i in 0 until childCount) {

                val child = viewGroup.getChildAt(i)

                child.setAccessibilityDelegate(AdhAccessibilityDelegate())

                if (child is ViewGroup) {

                    child.setOnHierarchyChangeListener(object :
                        ViewGroup.OnHierarchyChangeListener {
                        override fun onChildViewAdded(parent: View?, child: View?) {
                            child?.setAccessibilityDelegate(AdhAccessibilityDelegate())
                            if (child is ViewGroup) {
                                setupAccessibilityDelegate(child)
                            }
                        }

                        override fun onChildViewRemoved(parent: View?, child: View?) {
                        }
                    })

                    setupAccessibilityDelegate(child)
                }
            }

        }

        private fun hasOnClickListener(view: View): Boolean {

            return true
        }
    }

    override fun sendAccessibilityEvent(host: View?, eventType: Int) {
        super.sendAccessibilityEvent(host, eventType)
        host?.let {
            printLog(host, eventType)
        }
    }

    private fun printLog(host: View, eventType: Int) {
        if (AccessibilityEvent.TYPE_VIEW_CLICKED == eventType) {
            //点击事件
            val sb = StringBuilder()
                .append("\n")
                .append("发生了点击事件:")
                .append("\n")
                .append("Context: ${host.context} ")
                .append("\n")
                .append("View: ${host}")
                .append("\n")
                .append("View.ContentDescription: ${host.contentDescription}")

            AdhLogger.log(sb.toString(),tagSuffix = TAG_SUFFIX)
        }
    }

    class WindowCallbackWrapper(
        var mWrapped: Window.Callback,
        var mWrappedActivity: Activity
    ) : Window.Callback {

        override fun dispatchKeyEvent(event: KeyEvent): Boolean {
            return mWrapped.dispatchKeyEvent(event)
        }

        override fun dispatchKeyShortcutEvent(event: KeyEvent): Boolean {
            return mWrapped.dispatchKeyShortcutEvent(event)
        }

        override fun dispatchTouchEvent(event: MotionEvent): Boolean {
            return mWrapped.dispatchTouchEvent(event)
        }

        override fun dispatchTrackballEvent(event: MotionEvent): Boolean {
            return mWrapped.dispatchTrackballEvent(event)
        }

        override fun dispatchGenericMotionEvent(event: MotionEvent): Boolean {
            return mWrapped.dispatchGenericMotionEvent(event)
        }

        override fun dispatchPopulateAccessibilityEvent(event: AccessibilityEvent): Boolean {
            return mWrapped.dispatchPopulateAccessibilityEvent(event)
        }

        override fun onCreatePanelView(featureId: Int): View? {
            return mWrapped.onCreatePanelView(featureId)
        }

        override fun onCreatePanelMenu(featureId: Int, menu: Menu): Boolean {
            return mWrapped.onCreatePanelMenu(featureId, menu)
        }

        override fun onPreparePanel(featureId: Int, view: View?, menu: Menu): Boolean {
            return mWrapped.onPreparePanel(featureId, view, menu)
        }

        override fun onMenuOpened(featureId: Int, menu: Menu): Boolean {
            return mWrapped.onMenuOpened(featureId, menu)
        }

        override fun onMenuItemSelected(featureId: Int, item: MenuItem): Boolean {
            return mWrapped.onMenuItemSelected(featureId, item)
        }

        override fun onWindowAttributesChanged(attrs: WindowManager.LayoutParams) {
            mWrapped.onWindowAttributesChanged(attrs)
        }

        //虽然回调了 但是实际上我们的 XML 里写的 View 还找不到， getChildAt(0)==null
        override fun onContentChanged() {
            mWrapped.onContentChanged()

//            val contentView = mWrappedActivity.window.decorView.findViewById<ViewGroup>(android.R.id.content)
//            setupAccessibilityDelegate(mWrappedActivity.window.decorView as ViewGroup)
//            setupAccessibilityDelegate(contentView)
        }

        override fun onWindowFocusChanged(hasFocus: Boolean) {
            mWrapped.onWindowFocusChanged(hasFocus)
        }

        //这个回调里 getChildAt(0) 就能获取我们自己写的布局 View 了，此时设置是可以的了。
        override fun onAttachedToWindow() {
            mWrapped.onAttachedToWindow()
            setupAccessibilityDelegate(mWrappedActivity.window.decorView as ViewGroup)
        }

        override fun onDetachedFromWindow() {
            mWrapped.onDetachedFromWindow()
        }

        override fun onPanelClosed(featureId: Int, menu: Menu) {
            mWrapped.onPanelClosed(featureId, menu)
        }

        @RequiresApi(23)
        override fun onSearchRequested(searchEvent: SearchEvent): Boolean {
            return mWrapped.onSearchRequested(searchEvent)
        }

        override fun onSearchRequested(): Boolean {
            return mWrapped.onSearchRequested()
        }

        override fun onWindowStartingActionMode(callback: ActionMode.Callback): ActionMode? {
            return mWrapped.onWindowStartingActionMode(callback)
        }

        @RequiresApi(23)
        override fun onWindowStartingActionMode(
            callback: ActionMode.Callback,
            type: Int
        ): ActionMode? {
            return mWrapped.onWindowStartingActionMode(callback, type)
        }

        override fun onActionModeStarted(mode: ActionMode) {
            mWrapped.onActionModeStarted(mode)
        }

        override fun onActionModeFinished(mode: ActionMode) {
            mWrapped.onActionModeFinished(mode)
        }

        @RequiresApi(24)
        override fun onProvideKeyboardShortcuts(
            data: List<KeyboardShortcutGroup>,
            menu: Menu?,
            deviceId: Int
        ) {
            mWrapped.onProvideKeyboardShortcuts(data, menu, deviceId)
        }

        @RequiresApi(26)
        override fun onPointerCaptureChanged(hasCapture: Boolean) {
            mWrapped.onPointerCaptureChanged(hasCapture)
        }

    }
}