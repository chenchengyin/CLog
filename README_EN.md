# CLog

(English| [中文](README.md))

    CLog Is a simple/lightweight Android log print library

**提供满足各种业务使用的功能同时提供各种拓展性**

#### 1.Introducing dependencies in Gradle:
dependencies {
    implementation 'com.github.chenchengyin:CLog:{last-version}'  //now is 1.0.5
}



#### 2.initialization
    
Clog.init()  
Parameter description: Please refer to the API doc method description

| Parameter name | Description | Type |
| :------| ------: | :------: |
| isShowLog | Log switch | Boolean |
| logOptions | Log initialization parameters | LogOptions |
| globalTag | Global Tag | String |
| logDir | Log Folder | String |
| logFileNamePrefix | Log file prefix | String |
| customLogDecoration | Custom presentation | LogDecoration |
| otherLogEngine | Custom Log Engine | LogEngine |
| fileLogEngine | File log engine, optional:DefaultFleLogEngine,Log4aFileLogEngine | LogEngine |
| customWrapper | Is it encapsulated twice or not | Boolean |



#### 3.Call the Log method and explain
-    1. Print a normal message CLog.v("I am custom TAG","An ordinary message")
    
    TAG is an optional parameter. If it is not passed, the global TAG will be used. If the global TAG is not set, the default is the class name as TAG.
    Rendering effect:
    2019-09-26 22:42:04.973 11076-11076/com.cvt.clog E/全局TAG: [ (MainActivity.kt:22)#onCreate ] 一条普通的消息

    2019-09-26 22:42:04.973 11076-11076/com.cvt.clog D/我是自定义TAG: [ (MainActivity.kt:22)#onCreate ] 这是一条带自定  义TAG的消息
    
-    2. Elegance presents a printed message: CLog.pretty(toString)

    Rendering effect:
    2019-09-26 22:42:04.973 11076-11076/com.cvt.clog D/全局TAG: [ (MainActivity.kt:22)#onCreate ] 
    ╔═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
    ║ Elegance presents a printed message...0Elegance presents a printed message...1Elegance presents a printed message...2Elegance presents a printed message...3Elegance presents a printed message...4Elegance presents a printed message...5Elegance presents a printed message...6Elegance presents a printed message...7Elegance presents a printed message...8Elegance presents a printed message...9Elegance presents a printed message...
    ║ 11Elegance presents a printed message...12Elegance presents a printed message...13Elegance presents a printed message...14Elegance presents a printed message...15Elegance presents a printed message...16Elegance presents a printed message...17Elegance presents a printed message...18Elegance presents a printed message...19Elegance presents a printed message...20Elegance presents a printed message...21Elegance presents a printed message...
    ║ 22Elegance presents a printed message...23Elegance presents a printed message...24Elegance presents a printed message...25Elegance presents a printed message...26Elegance presents a printed message...27Elegance presents a printed message...28Elegance presents a printed message...29Elegance presents a printed message...30Elegance presents a printed message...31Elegance presents a printed message...32Elegance presents a printed message...
    ║ 33Elegance presents a printed message...34Elegance presents a printed message...35Elegance presents a printed message...36Elegance presents a printed message...37Elegance presents a printed message...38Elegance presents a printed message...39Elegance presents a printed message...40Elegance presents a printed message...41Elegance presents a printed message...42Elegance presents a printed message...43Elegance presents a printed message...
    ║ 44Elegance presents a printed message...45Elegance presents a printed message...46Elegance presents a printed message...47Elegance presents a printed message...48Elegance presents a printed message...49Elegance presents a printed message...50Elegance presents a printed message...51Elegance presents a printed message...52Elegance presents a printed message...53Elegance presents a printed message...54Elegance presents a printed message...
    ║ 55Elegance presents a printed message...56Elegance presents a printed message...57Elegance presents a printed message...58Elegance presents a printed message...59Elegance presents a printed message...60Elegance presents a printed message...61Elegance presents a printed message...62Elegance presents a printed message...63Elegance presents a printed message...64Elegance presents a printed message...65Elegance presents a printed message...
    ║ 66Elegance presents a printed message...67Elegance presents a printed message...68Elegance presents a printed message...69Elegance presents a printed message...70Elegance presents a printed message...71Elegance presents a printed message...72Elegance presents a printed message...73Elegance presents a printed message...74Elegance presents a printed message...75Elegance presents a printed message...76Elegance presents a printed message...
    ║ 77Elegance presents a printed message...78Elegance presents a printed message...79Elegance presents a printed message...80Elegance presents a printed message...81Elegance presents a printed message...82Elegance presents a printed message...83Elegance presents a printed message...84Elegance presents a printed message...85Elegance presents a printed message...86Elegance presents a printed message...87Elegance presents a printed message...
    ║ 88Elegance presents a printed message...89Elegance presents a printed message...90Elegance presents a printed message...91Elegance presents a printed message...92Elegance presents a printed message...93Elegance presents a printed message...94Elegance presents a printed message...95Elegance presents a printed message...96Elegance presents a printed message...97Elegance presents a printed message...98Elegance presents a printed message...
    ║ 99Elegance presents a printed message...100
    ╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
    
-    3. CLog.json("""{"key":"value"}""") //kotlin demo

    2019-09-26 22:42:04.973 11076-11076/com.cvt.clog D/全局TAG: [ (MainActivity.kt:22)#onCreate ] 
    {
        "key": "value"
    }
    
-    4. CLog.stackTrace("Look Look the call stack") 

    2019-09-26 22:42:04.974 11076-11076/com.cvt.clog D/全局TAG: [ (MainActivity.kt:22)#onCreate ] 
    ╔═══════════════════════════════════════════════════════════════════════════════════════
    ║ Look Look the call stack
    ╚═══════════════════════════════════════════════════════════════════════════════════════
    ╔═══════════════════════════════════════════════════════════════════════════════════════
    java.lang.Throwable: call stack:
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

-    5. Print log to file

        1.By default, java IO is used as the log writing tool.
  
          CLog.file("Print to log to file") 
        

        2.Also,You can use [Log4a](https://github.com/pqpo/Log4a), a high performance engine prints to file
        ```
        val logOptions = LogOptions()
        logOptions.logDir = logDir //Log storage directory
        logOptions.logFileNamePrefix = "ccy" //Log file prefix
        logOptions.fileLogEngine = Log4aFileLogEngine(applicationContext)
        CLog.init(true, logOptions)
        CLog.file("Print to file using Log4a")
        ```
    2019-09-26 22:42:04.980 11076-11076/com.cvt.clog D/Global TAG: [ (MainActivity.kt:22)#onCreate ] Print  log to file
  
         Additional explanation: If the number of the next log folder exceeds 100, 50 will be deleted in the old order, and the remaining 50
        
-    6. If you want more presentations, of course you can customize your presentation.
```
        CLog.init(true, "Global TAG", absolutePath, "ccy", MyLogDecoration())
        CLog.i("a decorated log") //Global
        CLog.i("TAG", "Temporary decorative log", MyLogDecoration())  //Temporary  

        class MyLogDecoration : BaseLogDecoration() {
            override fun process(tag: String, msg: String): String {
                return "[I am a Log decoration] content:$msg"
            }
        }
```
     
    2019-09-26 22:42:04.980 11076-11076/com.cvt.clog I/Global TAG: [ (MainActivity.kt:22)#onCreate ] [I am a Log decoration] content:a decorated log
    2019-09-26 22:42:04.981 11076-11076/com.cvt.clog I/TAG: [ (MainActivity.kt:22)#onCreate ] [I am a Log decoration] Content: Temporary decorative log
    
-    7. In addition, after using for a while, you want to use other log print libraries, you can switch back more quickly, only need
 ```
        CLog.init(true, "TAG", absolutePath, "ccy", null, UseOtherLogEngine()) Assign to customLogDecoration if you use LogOptions
        CLog.d("TAG", "a message printed using another log engine")    

        class UseOtherLogEngine :LogEngine{
            override fun deliver(tag: String?, msg: Any) {
                Log.i(tag, "Logs from other print libraries:${msg.toString()}")
            }
        }
 ```    
    2019-09-26 22:42:04.987 11076-11076/com.cvt.clog I/TAG: Logs from other print libraries: one message printed using another log engine
 
-    8. If you don't like to use CLog to print logs directly, and you want to encapsulate a tool class like LogUtil, you need to set the customWrapper of LogOptions to true.
     Otherwise there will be a problem with other libraries: the log cannot be located to the current class name.
     

    Description of the problem: such as printing a message in MainActivity  
    Will present:
    2019-09-26 22:42:04.972 11076-11076/com.cvt.clog V/TAG: [ (LogUtil.kt:22)#onCreate ] An ordinary message
    If you set customWrapper to true, then
    2019-09-26 22:42:04.972 11076-11076/com.cvt.clog V/TAG: [ (MainActivity.kt:22)#onCreate ] An ordinary message
    
Additional instructions:  
    1. At present, the printing method of other libraries is good, but it takes up a lot of logcat lines, which provides more simplified and flexible log rendering.  
    2. You can freely switch the log library to reduce the cost of modifying a large amount of code caused by switching the switch log print library.  
    3. Support Log4a high performance log input to file  
    4. Lightweight, less library code   

## Thanks

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

