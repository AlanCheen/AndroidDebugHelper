# Android Debug Helper（ADH）


老是写一些重复的关于 debug 的内容，烦了，想做点事：

- [x] 监控并打印 Activity 的生命周期
- [x] 监控并打印 Fragment 的生命周期
- [x] Debug 日志
- [x] 监控事件点击

## 使用教程

[![LatestVersion](https://api.bintray.com/packages/alancheen/maven/debughelper/images/download.svg)](https://bintray.com/alancheen/maven/debughelper/_latestVersion)

添加依赖：
```groovy
implementation 'me.yifeiyuan:debughelper:latest_version'
```

前置依赖：
```groovy
implementation "org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"
implementation 'com.android.support:support-v4:28.0.0'
implementation 'com.android.support:recyclerview-v7:28.0.0'
implementation 'com.android.support.constraint:constraint-layout:2.0.4'
implementation 'com.android.support:appcompat-v7:28.0.0'
```

因为自己参与的一些项目不能使用 AndroidX，所以还没使用 AndroidX。

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

### showcase 页面

有时候我想写一个页面，上面放几个按钮，点击后分别调用一些方法来测试一些功能，写多了就不想每次在 XML 中加个Button 那样去实现。

所以在 ADH 我提供了快速实现这种页面的能力，通过继承 AdhShowcaseActivity 并重写 provideShowcaseItems() 方法来快速实现一个 showcase 的页面。

举个例子：

```kotlin
class MainActivity : AdhShowcaseActivity() {

    override fun provideShowcaseItems(): List<AdhShowcaseItem> {

        return mutableListOf(

            createShowcaseItem("测试项 1") {
                Toast.makeText(this@MainActivity, "点击了测试项 1", Toast.LENGTH_SHORT).show()
            },

            createShowcaseItem("测试项 2") {
                Toast.makeText(this@MainActivity, "点击了测试项 2", Toast.LENGTH_SHORT).show()
            },

            createShowcaseItem("测试 AdhLogger 异步线程弹 Toast 的能力") {
                Thread { AdhLogger.log("测试异步线程 Toast", true) }.start()
            },
            )
    }
}
```

这样启动后就是一个包含三个 Item 的列表页面，并且每个 Item 点击处理也可以自己定义。


## 变更日志

### 1.2.3

- 新增 增加对 View.OnClick 事件的监听并打印，目前已经支持 Activity 和 Fragment，可以通过设置 detectViewOnClick 开启或关闭；
- 更新 AdhLogger 更新，新增了设置 tagSuffix 的能力，方便过滤日志，另外简化了打印的日志；

### 1.2.2

- 更新 修改部分类的可见性为 internal
- 更新 修改 Foundation.setup() 方法内部调用逻辑，并增加防止多次初始化逻辑
- 新增 增加 AdhLogger.logAndCopy() 方法，支持打印日志的同时把日志拷贝到粘贴板

### 1.2.1

- 更新 修改点击处理逻辑，由设置 View.OnClickListener 变为 Runnable
- 新增 在 AdhShowcaseActivity 新增 createShowcaseItem 快速创建 AdhShowcaseItem

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