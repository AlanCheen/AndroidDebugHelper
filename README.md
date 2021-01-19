# Android Debug Helper（ADH）


老是写一些重复的关于 debug 的内容，烦了，想做点事：

- [x] 监控并打印 Activity 的生命周期
- [x] 监控并打印 Fragment 的生命周期
- [x] Debug 日志
- [ ] 监控事件点击，未完成

## 使用教程

添加依赖：

```
implementation 'me.yifeiyuan:debughelper:1.1.2'
```

初始化：

```kotlin
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        DebugConfig().apply {
            debuggable = BuildConfig.DEBUG
            application = this@App
            enableStrictMode = true
            detectActivityLifecycle = true
            detectFragmentLifecycle = true
            logLevel = DebugConfig.LogLevel.I
        }.also {
            DebugHelper.setup(it)
        }

    }
}
```

## 功能

### detectActivityLifecycle

检测并打印应用所有的 Activity 的生命周期以及相关方法执行情况。

打印的日志和 Activity 本身的方法名字略有差异，例如 onCreate 打印的是 onActivityCreated。

下面举例几个常见的 Activity 流程日志：

启动 Activity :
```
onActivityCreated()
onActivityStarted()
onActivityResumed()
```

应用切后台：
```
onActivityPaused()
onActivityStopped()
onActivitySaveInstanceState()
```

切后台后切回 App:
```
onActivityStarted()
onActivityResumed()
```

按返回键退出 Activity：
```
onActivityPaused()
onActivityStopped()
onActivityDestroyed()
```

### detectFragmentLifecycle

检测并打印 Fragment 的生命周期以及相关方法执行情况。

和 Activity 类似，这里打印的也有点差异，例如 onCreated 对应的是 onFragmentCreated 。

配合`detectActivityLifecycle` 可以打印出更全面的日志。


举个例子：静态加载启动一个 Fragment:

```
onActivityCreated()
onFragmentPreAttached()
onFragmentAttached()
onFragmentPreCreated()
onFragmentCreated()
onFragmentViewCreated()
onActivityStarted()
onFragmentActivityCreated()
onFragmentStarted()
onActivityResumed()
onFragmentResumed()
```

### tracingBinder

打开 Binder 的 trace 功能。

方法是 @hide 标记的，通过反射去开启，在 O 以及以上的系统需要配合开发者选项使用。

按系统版本执行 shell 命令即可打开。

```shell
# Android 10 api 29
adb shell settings put global hidden_api_policy 1
# Android 9 api 28
adb shell settings put global hidden_api_policy_pre_p_apps  1
adb shell settings put global hidden_api_policy_p_apps 1
```

## 变更日志


### 1.2.0

- 新增 增加 showcase 能力，可以快速实现一个 List，每个 Item 展示文案并自定义处理点击事件，适用于测试功;

### 1.1.2

- 更新 移动 LogLevel 相关类到 DebugConfig，统一配置项路径，方便管理；
- 新增 DebugConfig.debuggable 作为总开关，默认为 true;
- 删除 Logger 接口删除;

### 1.1.1

- 新增 tracingBinder 配置；
- 新增 LogLevel.ST 支持打印堆栈；
- 更新 Activity 和 Fragment 检测逻辑分离，可以单独配置；
- 更新 修改 AdhLogger 日志 TAG 为 "ADH"；

### 1.1.0

- 删除 AndroidX 修改为 Support 库，方便集成；
- 新增 支持配置日志的等级，见 DebugHelper.LogLevel；
- 新增 AdhLogger.log 方法，并支持打印日志的同时弹 toast 提示；

### 1.0.1

- 更新 strictModeEnable 为 enableStrictMode；

### 1.0.0

- 新增 支持开启 StrictMode；
- 新增 支持监听 Activity 的生命周期并打印；
- 新增 支持监听 Fragment 的生命周期并打印；