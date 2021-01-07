# AndroidDebugHelper


老是写一些重复的关于 debug 的内容，烦了，想做点事：

- 监控 Activity 的生命周期-->完成
- 监控 Fragment 的生命周期-->完成
- Debug 日志-->完成
- 监控事件点击，未完成

## Usage

添加依赖：

```
implementation 'me.yifeiyuan:debughelper:1.1.0'
```

初始化：

```kotlin
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            DebugConfig().apply {
                application = this@App
                enableStrictMode = true
                detectActivityLifecycle = true
                detectFragmentLifecycle = true
                logLevel = DebugHelper.LogLevel.I
            }.also {
                DebugHelper.setup(it)
        }
    }
}
```

## 功能



### trace binder

打开 Binder 的 trace 功能。

方法是@hide 标记的，通过反射去开启，在 O 以及以上的系统需要配合开发者选项使用。

按系统版本执行 shell 命令即可打开。

```shell
# Android 10 api 29
adb shell settings put global hidden_api_policy 1
# Android 9 api 28
adb shell settings put global hidden_api_policy_pre_p_apps  1
adb shell settings put global hidden_api_policy_p_apps 1
```



## 变更日志

### 1.1.0

- 移除 AndroidX 修改为 Support 库，方便集成；
- 支持配置日志的等级，见 DebugHelper.LogLevel；
- 新增 AdhLogger.log 方法，并支持打印日志的同时弹 toast 提示；

### 1.0.1

- 修改 strictModeEnable 为 enableStrictMode；

### 1.0.0

- 支持开启 StrictMode；
- 支持监听 Activity 的生命周期并打印；
- 支持监听 Fragment 的生命周期并打印；