# AndroidDebugHelper


老是写一些重复的关于 debug 的内容，烦了，想做点事：

- 监控 Activity 的生命周期-->完成
- 监控 Fragment 的生命周期-->完成
- Debug 日志-->完成
- 监控事件点击，未完成


## Usage

添加依赖：

```
implementation 'me.yifeiyuan:debughelper:1.0.0'
```

初始化：

```kotlin
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            DebugConfig().apply {
                application = this@App
                strictModeEnable = true
                detectActivityLifecycle = true
                detectFragmentLifecycle = true
            }.also {
                DebugHelper.setup(it)
            }
        }
    }
}
```