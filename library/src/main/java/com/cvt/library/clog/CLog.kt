@file:JvmName("CLog")

package com.cvt.library.clog

import android.util.Log
import java.io.File


/**
 * Date: 2019-09-03 10:54
 * Author: chenchengyin on
 * Email: itmarshon@163.com
 *
 */
object CLog {
    private var useLogDecoration: LogDecoration? = null
    private var customLogDecoration: LogDecoration? = null
    private var logWrapperFlag: Int = 0
    private var fileLogEngine: LogEngine? = null
    private var useOtherLogEngine: LogEngine? = null
    private var stackTraceIndex: Int = 5
    private var isShowLog = true
    private var globalTag: String? = null
    private var logDir: String? = null
    private var logFilePrefixName: String? = null
    private var jsonLogDecoration = JsonLogDecoration()
    private var prettyLogDecoration = PrettyLogDecoration()
    private var stackLogDecoration = StackLogDecoration()
    private var fileLogLevel = Log.INFO

    /**
     * @isShowLog Switch to display logs , true or false
     * @logOptions : Initialization parameter options
     */
    @JvmStatic
    fun init(
        isShowLog: Boolean = true,
        logOptions: LogOptions = LogOptions()
    ) {
        this.isShowLog = isShowLog
        this.globalTag = logOptions.globalTag
        this.logDir = logOptions.logDir
        this.logFilePrefixName = logOptions.logFileNamePrefix
        this.customLogDecoration = logOptions.customLogDecoration ?: LogDecoration()
        this.useOtherLogEngine = logOptions.globalLogEngine
        this.fileLogLevel = logOptions.fileLogLevel
        if (logOptions.customWrapper) {
            this.logWrapperFlag = 1
        } else {
            this.logWrapperFlag = 0
        }

        if (logDir != null && logFilePrefixName != null) {
            fileLogEngine = fileLogEngine ?: DefaultFleLogEngine()
            if (fileLogEngine is Log4aFileLogEngine) {
                (fileLogEngine as Log4aFileLogEngine).logDir = logDir!!
                (fileLogEngine as Log4aFileLogEngine).logFileNamePrefix = logFilePrefixName!!
                (fileLogEngine as Log4aFileLogEngine).init()
            } else if (fileLogEngine is DefaultFleLogEngine) {
                (fileLogEngine as DefaultFleLogEngine).logDir = logDir
                (fileLogEngine as DefaultFleLogEngine).logFileNamePrefix = logFilePrefixName
                (fileLogEngine as DefaultFleLogEngine).init()
            } else {
                (fileLogEngine as DefaultFleLogEngine).logDir = logDir
                (fileLogEngine as DefaultFleLogEngine).logFileNamePrefix = logFilePrefixName
                (fileLogEngine as DefaultFleLogEngine).init()
            }
        }

    }

    /**
     * @isShowLog Switch for Displaying Logs
     * @globalTag Configure Global Tag
     * @logDir Log Storage Folder Path
     * @logFileNamePrefix Log Naming Prefix
     * @customLogDecoration  Custom Presentation
     * @useOtherLogEngine uses other log printing engines
     */
    @JvmStatic
    @JvmOverloads
    fun init(
        isShowLog: Boolean = true,
        globalTag: String? = null,
        logDir: String? = null,
        logFileNamePrefix: String? = null,
        customLogDecoration: LogDecoration? = null,
        useOtherLogEngine: LogEngine? = null
    ) {
        this.isShowLog = isShowLog
        this.globalTag = globalTag
        this.logDir = logDir
        this.logFilePrefixName = logFileNamePrefix
        this.customLogDecoration = customLogDecoration ?: LogDecoration()
        this.useOtherLogEngine = useOtherLogEngine
        if (logDir != null && logFilePrefixName != null) {
            fileLogEngine = DefaultFleLogEngine()
            (fileLogEngine as DefaultFleLogEngine).logDir = CLog.logDir
            (fileLogEngine as DefaultFleLogEngine).logFileNamePrefix = logFilePrefixName
            (fileLogEngine as DefaultFleLogEngine).init()
        }
    }

    @JvmStatic
    fun v(msg: Any) {
        if (useOtherLogEngine != null) {
            useOtherLogEngine?.deliver(null, msg)
            return
        }
        checkIfDecoration(null)
        printLog(Log.VERBOSE, null, msg.toString())
    }

    @JvmStatic
    fun v(tag: String, msg: Any) {
        if (useOtherLogEngine != null) {
            useOtherLogEngine?.deliver(null, msg)
            return
        }
        checkIfDecoration(null)
        printLog(Log.VERBOSE, tag, msg.toString())
    }

    @JvmStatic
    fun v(tag: String?, msg: Any, decoration: LogDecoration? = null) {
        if (useOtherLogEngine != null) {
            useOtherLogEngine?.deliver(tag, msg)
            return
        }
        checkIfDecoration(decoration)
        printLog(Log.VERBOSE, tag, msg.toString())
    }

    @JvmStatic
    fun d(msg: Any) {
        if (useOtherLogEngine != null) {
            useOtherLogEngine?.deliver(null, msg)
            return
        }
        checkIfDecoration(null)
        printLog(Log.DEBUG, null, msg.toString())
    }

    @JvmStatic
    fun d(tag: String, msg: Any) {
        if (useOtherLogEngine != null) {
            useOtherLogEngine?.deliver(tag, msg)
            return
        }
        checkIfDecoration(null)
        printLog(Log.DEBUG, tag, msg.toString())
    }

    @JvmStatic
    fun d(tag: String?, msg: Any, decoration: LogDecoration? = null) {
        if (useOtherLogEngine != null) {
            useOtherLogEngine?.deliver(tag, msg)
            return
        }
        checkIfDecoration(decoration)
        printLog(Log.DEBUG, tag, msg.toString())
    }

    @JvmStatic
    fun i(msg: Any) {
        if (useOtherLogEngine != null) {
            useOtherLogEngine?.deliver(null, msg)
            return
        }
        checkIfDecoration(null)
        printLog(Log.INFO, null, msg.toString())
    }

    @JvmStatic
    fun i(tag: String, msg: Any) {
        if (useOtherLogEngine != null) {
            useOtherLogEngine?.deliver(tag, msg)
            return
        }
        checkIfDecoration(null)
        printLog(Log.INFO, tag, msg.toString())
    }

    @JvmStatic
    fun i(tag: String?, msg: Any, decoration: LogDecoration? = null) {
        if (useOtherLogEngine != null) {
            useOtherLogEngine?.deliver(tag, msg)
            return
        }
        checkIfDecoration(decoration)
        printLog(Log.INFO, tag, msg.toString())
    }

    @JvmStatic
    fun w(msg: Any) {
        if (useOtherLogEngine != null) {
            useOtherLogEngine?.deliver(null, msg)
            return
        }
        checkIfDecoration(null)
        printLog(Log.WARN, null, msg.toString())
    }

    @JvmStatic
    fun w(tag: String, msg: Any) {
        if (useOtherLogEngine != null) {
            useOtherLogEngine?.deliver(tag, msg)
            return
        }
        checkIfDecoration(null)
        printLog(Log.WARN, tag, msg.toString())
    }

    @JvmStatic
    fun w(tag: String?, msg: Any, decoration: LogDecoration? = null) {
        if (useOtherLogEngine != null) {
            useOtherLogEngine?.deliver(tag, msg)
            return
        }
        checkIfDecoration(decoration)
        printLog(Log.WARN, tag, msg.toString())
    }


    @JvmStatic
    fun e(msg: String) {
        if (useOtherLogEngine != null) {
            useOtherLogEngine?.deliver(null, msg)
            return
        }
        checkIfDecoration(null)
        stackTraceIndex = 5
        printLog(Log.ERROR, null, msg, null)
    }

    @JvmStatic
    fun e(msg: String, e: Throwable) {
        if (useOtherLogEngine != null) {
            useOtherLogEngine?.deliver(null, msg)
            return
        }
        checkIfDecoration(null)
        stackTraceIndex = 5
        printLog(Log.ERROR, null, msg, e)
    }

    @JvmStatic
    fun e(tag: String, msg: String) {
        if (useOtherLogEngine != null) {
            useOtherLogEngine?.deliver(tag, msg)
            return
        }
        checkIfDecoration(null)
        stackTraceIndex = 5
        printLog(Log.ERROR, tag, msg, null)
    }

    @JvmStatic
    fun e(tag: String, msg: String, e: Throwable) {
        if (useOtherLogEngine != null) {
            useOtherLogEngine?.deliver(tag, msg)
            return
        }
        checkIfDecoration(null)
        stackTraceIndex = 5
        printLog(Log.ERROR, tag, msg, e)
    }

    @JvmStatic
    fun e(tag: String?, msg: Any, decoration: LogDecoration? = null, e: Throwable? = null) {
        if (useOtherLogEngine != null) {
            useOtherLogEngine?.deliver(tag, msg)
            return
        }
        checkIfDecoration(decoration)
        stackTraceIndex = 5
        printLog(Log.ERROR, tag, msg.toString(), e)
    }

    @JvmStatic
    fun pretty(msg: Any) {
        if (useOtherLogEngine != null) {
            useOtherLogEngine?.deliver(null, msg)
            return
        }
        checkIfDecoration(prettyLogDecoration)
        printLog(Log.DEBUG, null, msg.toString())
    }

    @JvmStatic
    fun pretty(tag: String? = null, msg: Any) {
        if (useOtherLogEngine != null) {
            useOtherLogEngine?.deliver(tag, msg)
            return
        }
        checkIfDecoration(prettyLogDecoration)
        printLog(Log.DEBUG, tag, msg.toString())
    }

    /**Pri|nt the Log of the file type. Set logDir and logFilePrefixName in the init() method before calling*/
    @Deprecated("Use field in LogOptions.fileLogLevel instead this method,then CLog normal")
    @JvmStatic
    fun file(msg: Any) {
        if (useOtherLogEngine != null) {
            useOtherLogEngine?.deliver(null, msg)
            return
        }
        require(!(logDir == null || logFilePrefixName == null)) { "logDir = null or logFilePrefixName = null, you should set them before use" }
        checkIfDecoration(null)
        printLog(Log.DEBUG, null, msg.toString())
    }

    @Deprecated("Use field in LogOptions.fileLogLevel instead this method,then CLog normal")
    @JvmStatic
    fun file(tag: String? = null, msg: Any) {
        if (useOtherLogEngine != null) {
            useOtherLogEngine?.deliver(tag, msg)
            return
        }
        require(!(logDir == null || logFilePrefixName == null)) { "logDir = null or logFilePrefixName = null, you should set them before use" }
        checkIfDecoration(null)
        printLog(Log.DEBUG, tag, msg.toString())
    }

    @JvmStatic
    fun clearLogFile() {
        if (logDir == null) {
            e("logDir is null")
        }
        val file = File(logDir!!)
        val listFiles = file.listFiles()
        listFiles.forEach {
            it.delete()
        }
    }

    @JvmStatic
    fun getLogFiles(): Array<out File>? {
        if (logDir == null) {
            e("logDir is null")
        }
        val file = File(logDir!!)
        return file.listFiles()
    }


    @JvmStatic
    fun json(msg: Any) {
        if (useOtherLogEngine != null) {
            useOtherLogEngine?.deliver(null, msg)
            return
        }
        checkIfDecoration(jsonLogDecoration)
        printLog(Log.DEBUG, null, msg.toString())
    }

    @JvmStatic
    fun json(tag: String? = null, msg: Any) {
        if (useOtherLogEngine != null) {
            useOtherLogEngine?.deliver(tag, msg)
            return
        }
        checkIfDecoration(jsonLogDecoration)
        printLog(Log.DEBUG, tag, msg.toString())
    }

    @JvmStatic
    fun stackTrace(msg: Any) {
        if (useOtherLogEngine != null) {
            useOtherLogEngine?.deliver(null, msg)
            return
        }
        checkIfDecoration(stackLogDecoration)
        printLog(Log.DEBUG, null, msg.toString())
    }

    @JvmStatic
    fun stackTrace(tag: String? = null, msg: Any) {
        if (useOtherLogEngine != null) {
            useOtherLogEngine?.deliver(tag, msg)
            return
        }
        checkIfDecoration(stackLogDecoration)
        printLog(Log.DEBUG, tag, msg.toString())
    }

    private fun printLog(type: Int, tagStr: String?, objects: String, e: Throwable? = null) {
        if (!isShowLog) {
            return
        }
        val contents = wrapperContent(stackTraceIndex + logWrapperFlag, tagStr, objects)
        val tag = contents[0]
        val msg = contents[1]
        val headString = contents[2]

        if (type >= fileLogLevel && fileLogEngine != null) {
            fileLogEngine!!.deliver(tagStr?:"",objects)
        }
        when (type) {
            Log.VERBOSE, Log.DEBUG, Log.INFO, Log.WARN, Log.ERROR, Log.ASSERT -> Util.printDefault(
                type,
                tag,
                headString + msg,
                e
            )
        }
        reset()
    }

    private fun reset() {
        useLogDecoration = customLogDecoration
    }

    private fun wrapperContent(stackTraceIndex: Int, tagStr: String?, objects: String): Array<String> {
        val stackTrace = Thread.currentThread().stackTrace
        val targetElement = stackTrace[stackTraceIndex]
        val fileName = targetElement.fileName
        var className = targetElement.className
        val suffix = fileName!!.substring(fileName.lastIndexOf("."))
        val classNameInfo = className.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        if (classNameInfo.isNotEmpty()) {
            className = classNameInfo[classNameInfo.size - 1] + suffix
        }
        if (className.contains("$")) {
            className = className.split("\\$".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0] + suffix
        }
        val methodName = targetElement.methodName
        var lineNumber = targetElement.lineNumber
        if (lineNumber < 0) {
            lineNumber = 0
        }
        val tag = tagStr ?: globalTag ?: classNameInfo[classNameInfo.size - 1]
        val msg = useLogDecoration?.process(tag, objects)!!
        val headString = "[ ($className:$lineNumber)#$methodName ] "
        return arrayOf(tag, msg, headString)
    }


    private fun checkIfDecoration(decoration: LogDecoration?) {
        useLogDecoration = decoration ?: customLogDecoration
        stackTraceIndex = 6
    }

    /**
     * This is to release the resource and write the cache log to the hard disk,
     * calling the method before exiting the program As far as possible.
     * */
    @JvmStatic
    fun release() {
        if (fileLogEngine != null && fileLogEngine is Log4aFileLogEngine) {
            (fileLogEngine as Log4aFileLogEngine).release()
        }
    }

}