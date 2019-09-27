# CLog

([English](README_EN.md) | 中文)

    CLog 是一个简洁/轻量的Android 日志打印库

**提供满足各种业务使用的功能同时提供各种拓展性**

#### 1.Gradle中引入依赖:
dependencies {
    implementation 'com.cvte.maxhub:clog:{last-version}'  //now is R.1.1.6   
}

[获取最新版本](http://mhci.gz.cvte.cn/job/screen_share/job/Android_App/job/AndroidClog/job/master/)

#### 2.初始化
    
Clog.init()  
参数说明:请见API方法说明

#### 3.调用Log方法并说明
-    1. 打印一条普通消息 CLog.v("我是自定义TAG","一条普通的消息")
    
    TAG是可选参数,如果不传则会使用全局TAG,如全局TAG不设置则默认为本类名作为TAG
    呈现效果:
    2019-09-26 22:42:04.973 11076-11076/com.cvt.clog E/全局TAG: [ (MainActivity.kt:22)#onCreate ] 一条普通的消息

    2019-09-26 22:42:04.973 11076-11076/com.cvt.clog D/我是自定义TAG: [ (MainActivity.kt:22)#onCreate ] 这是一条带自定  义TAG的消息
    
-    2. 优雅呈现出一条打印信息: CLog.pretty(toString)

    呈现效果:
    2019-09-26 22:42:04.973 11076-11076/com.cvt.clog D/全局TAG: [ (MainActivity.kt:22)#onCreate ] 
    ╔═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
    ║ 这是一条很长的消息0这是一条很长的消息1这是一条很长的消息2这是一条很长的消息3这是一条很长的消息4这是一条很长的消息5这是一条很长的消息6这是一条很长的消息7这是一条很长的消息8这是一条很长的消息9这是一条很长的消息10这是一条很长的消息
    ║ 11这是一条很长的消息12这是一条很长的消息13这是一条很长的消息14这是一条很长的消息15这是一条很长的消息16这是一条很长的消息17这是一条很长的消息18这是一条很长的消息19这是一条很长的消息20这是一条很长的消息21这是一条很长的消
    ║ 息22这是一条很长的消息23这是一条很长的消息24这是一条很长的消息25这是一条很长的消息26这是一条很长的消息27这是一条很长的消息28这是一条很长的消息29这是一条很长的消息30这是一条很长的消息31这是一条很长的消息32这是一条很长的
    ║ 消息33这是一条很长的消息34这是一条很长的消息35这是一条很长的消息36这是一条很长的消息37这是一条很长的消息38这是一条很长的消息39这是一条很长的消息40这是一条很长的消息41这是一条很长的消息42这是一条很长的消息43这是一条很长
    ║ 的消息44这是一条很长的消息45这是一条很长的消息46这是一条很长的消息47这是一条很长的消息48这是一条很长的消息49这是一条很长的消息50这是一条很长的消息51这是一条很长的消息52这是一条很长的消息53这是一条很长的消息54这是一条很
    ║ 长的消息55这是一条很长的消息56这是一条很长的消息57这是一条很长的消息58这是一条很长的消息59这是一条很长的消息60这是一条很长的消息61这是一条很长的消息62这是一条很长的消息63这是一条很长的消息64这是一条很长的消息65这是一条
    ║ 很长的消息66这是一条很长的消息67这是一条很长的消息68这是一条很长的消息69这是一条很长的消息70这是一条很长的消息71这是一条很长的消息72这是一条很长的消息73这是一条很长的消息74这是一条很长的消息75这是一条很长的消息76这是一
    ║ 条很长的消息77这是一条很长的消息78这是一条很长的消息79这是一条很长的消息80这是一条很长的消息81这是一条很长的消息82这是一条很长的消息83这是一条很长的消息84这是一条很长的消息85这是一条很长的消息86这是一条很长的消息87这是
    ║ 一条很长的消息88这是一条很长的消息89这是一条很长的消息90这是一条很长的消息91这是一条很长的消息92这是一条很长的消息93这是一条很长的消息94这是一条很长的消息95这是一条很长的消息96这是一条很长的消息97这是一条很长的消息98这
    ║ 是一条很长的消息99这是一条很长的消息100
    ╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
    
-    3. CLog.json("""{"key":"value"}""") //kotlin demo

    2019-09-26 22:42:04.973 11076-11076/com.cvt.clog D/全局TAG: [ (MainActivity.kt:22)#onCreate ] 
    {
        "key": "value"
    }
    
-    4. CLog.stackTrace("看看调用栈") 

    2019-09-26 22:42:04.974 11076-11076/com.cvt.clog D/全局TAG: [ (MainActivity.kt:22)#onCreate ] 
    ╔═══════════════════════════════════════════════════════════════════════════════════════
    ║ 看看调用栈
    ╚═══════════════════════════════════════════════════════════════════════════════════════
    ╔═══════════════════════════════════════════════════════════════════════════════════════
    java.lang.Throwable: 当前调用栈如下:
        at com.cvt.library.clog.StackLogDecoration.process(StackLogDecoration.kt:21)
        at com.cvt.library.clog.CLog.wrapperContent(CLog.kt:358)
        at com.cvt.library.clog.CLog.printLog(CLog.kt:321)
        at com.cvt.library.clog.CLog.stackTrace(CLog.kt:314)
        at com.cvt.library.clog.CLog.stackTrace(CLog.kt:299)
        at com.cvt.clog.JavaLogTest.testLog(JavaLogTest.java:45)
        at com.cvt.clog.MainActivity.onCreate(MainActivity.kt:22)
        at android.app.Activity.performCreate(Activity.java:7458)
        at android.app.Activity.performCreate(Activity.java:7448)
        at android.app.Instrumentation.callActivityOnCreate(Instrumentation.java:1286)
        at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:3409)
        at android.app.ActivityThread.handleLaunchActivity(ActivityThread.java:3614)
        at android.app.servertransaction.LaunchActivityItem.execute(LaunchActivityItem.java:86)
        at android.app.servertransaction.TransactionExecutor.executeCallbacks(TransactionExecutor.java:108)
        at android.app.servertransaction.TransactionExecutor.execute(TransactionExecutor.java:68)
        at android.app.ActivityThread$H.handleMessage(ActivityThread.java:2199)
        at android.os.Handler.dispatchMessage(Handler.java:112)
        at android.os.Looper.loop(Looper.java:216)
        at android.app.ActivityThread.main(ActivityThread.java:7625)
        at java.lang.reflect.Method.invoke(Native Method)
        at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:524)
        at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:987)
    ╚═══════════════════════════════════════════════════════════════════════════════════════   

-    5. 打印日志到文件

        1.默认使用java IO作为日志写入工具
  
          CLog.file("打印到日志到文件") 
        

        2.使用[Log4a](https://github.com/pqpo/Log4a)高性能引擎打印到文件
        ```
        val logOptions = LogOptions()
        logOptions.logDir = logDir //日志存放目录
        logOptions.logFileNamePrefix = "ccy" //日志文件前缀
        logOptions.fileLogEngine = Log4aFileLogEngine(applicationContext)
        CLog.init(true, logOptions)
        CLog.file("使用Log4a打印到文件")
        ```
    2019-09-26 22:42:04.980 11076-11076/com.cvt.clog D/全局TAG: [ (MainActivity.kt:22)#onCreate ] 打印到日志到文件
  
         补充说明:如果日志文件夹下个数超过100个,则会按照旧顺序删除50个,剩余50个
        
-    6. 如你想要更多呈现方式,当然可以自定义自己的呈现方式
```
        CLog.init(true, "全局TAG", absolutePath, "ccy", MyLogDecoration())
        CLog.i("一条带装饰的日志") //全局
        CLog.i("TAG", "临时的装饰日志", MyLogDecoration())    

        class MyLogDecoration : BaseLogDecoration() {
            override fun process(tag: String, msg: String): String {
                return "[我是Log修饰] 内容:$msg"
            }
        }
```
     
    2019-09-26 22:42:04.980 11076-11076/com.cvt.clog I/全局TAG: [ (MainActivity.kt:22)#onCreate ] [我是Log修饰] 内容:一条带装饰的日志
    2019-09-26 22:42:04.981 11076-11076/com.cvt.clog I/TAG: [ (MainActivity.kt:22)#onCreate ] [我是Log修饰] 内容:临时的装饰日志
    
-    7. 除此外,使用一段时间后,你想使用其他的日志打印库,还可以更加快捷的切换过去,只需要
 ```
        CLog.init(true, "全局TAG", absolutePath, "ccy", null, UseOtherLogEngine()) 如果使用LogOptions,则赋值给customLogDecoration
        CLog.d("TAG", "一条使用其他日志引擎打印的消息")    

        class UseOtherLogEngine :LogEngine{
            override fun deliver(tag: String?, msg: Any) {
                Log.i(tag, "来自其他打印库的日志:${msg.toString()}")
            }
        }
 ```    
    2019-09-26 22:42:04.987 11076-11076/com.cvt.clog I/TAG: 来自其他打印库的日志:一条使用其他日志引擎打印的消息
 
-    8. 如果你不喜欢直接使用CLog来打印日志,想要再封装一层如LogUtil的工具类,需要将LogOptions的customWrapper设置为true即可.  
    否则会出现和其他库同样出现的一个问题:日志不能定位到当前类名称   
    问题描述:如在MainActivity打印一条消息   
    则会呈现出:   

    2019-09-26 22:42:04.972 11076-11076/com.cvt.clog V/全局TAG: [ (LogUtil.kt:22)#onCreate ] 一条普通的消息
    如将customWrapper设置为true,则是
        2019-09-26 22:42:04.972 11076-11076/com.cvt.clog V/全局TAG: [ (MainActivity.kt:22)#onCreate ] 一条普通的消息
    
补充说明:  
   1.目前其他库的打印方式好看,但占用logcat很多行数,这里提供更简化灵活的日志呈现  
   2.可以自由切换日志库,降低切换日志打印库的切换带来的修改大量代码的成本  
   3.支持Log4a高性能的日志输入到文件  
   4.轻量级,库代码量少  

## 感谢

- [Log4a](https://github.com/pqpo/Log4a)

## License

    Copyright 2019 ccy
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
       http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

