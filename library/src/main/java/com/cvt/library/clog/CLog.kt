@file:JvmName("CLog")

package com.cvt.library.clog

import com.cvt.library.clog.Constants.A
import com.cvt.library.clog.Constants.D
import com.cvt.library.clog.Constants.E
import com.cvt.library.clog.Constants.I
import com.cvt.library.clog.Constants.V
import com.cvt.library.clog.Constants.W
import java.io.File


/**
 * Date: 2019-09-03 10:54
 * Author: chenchengyin on
 * Email: itmarshon@163.com
 *
 */
object CLog {
    private lateinit var fileLogDecoration: FileLogDecoration
    private lateinit var useLogDecoration: LogDecoration
    private lateinit var customLogDecoration: LogDecoration
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

    /**
     * @isShowLog Switch to display logs , true or false
     * @logOptions : Initialization parameter options
     */
    @JvmStatic
    fun init(
        isShowLog: Boolean = false,
        logOptions: LogOptions = LogOptions()
    ) {
        this.isShowLog = isShowLog
        this.globalTag = logOptions.globalTag
        this.logDir = logOptions.logDir
        this.logFilePrefixName = logOptions.logFileNamePrefix
        this.customLogDecoration = logOptions.customLogDecoration ?: LogDecoration()
        this.useOtherLogEngine = logOptions.otherLogEngine
        if (logOptions.customWrapper) {
            this.logWrapperFlag = 1
        } else {
            this.logWrapperFlag = 0
        }
        this.fileLogEngine = logOptions.fileLogEngine ?: DefaultFleLogEngine(logDir, logFilePrefixName)
        if (fileLogEngine is Log4aFileLogEngine) {
            (fileLogEngine as Log4aFileLogEngine).logDir = logDir!!
            (fileLogEngine as Log4aFileLogEngine).logFileNamePrefix = logFilePrefixName!!
            (fileLogEngine as Log4aFileLogEngine).init()
        }
        fileLogDecoration = FileLogDecoration(fileLogEngine!!)
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
        isShowLog: Boolean = false,
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
        val defaultFleLogEngine = DefaultFleLogEngine(logDir, logFilePrefixName)
        fileLogDecoration =
            FileLogDecoration(defaultFleLogEngine)
        defaultFleLogEngine.init()
    }

    @JvmStatic
    fun v(msg: Any) {
        v(null, msg.toString(), null)
    }

    @JvmStatic
    fun v(tag: String, msg: Any) {
        v(tag, msg.toString(), null)
    }

    @JvmStatic
    fun v(tag: String?, msg: Any, decoration: LogDecoration? = null) {
        if (useOtherLogEngine != null) {
            useOtherLogEngine?.deliver(tag, msg)
            return
        }
        useLogDecoration = decoration ?: customLogDecoration
        if (decoration == null) {
            stackTraceIndex = 6
        } else {
            stackTraceIndex = 5
        }
        printLog(V, tag, msg.toString())
    }

    @JvmStatic
    fun d(msg: Any) {
        d(null, msg.toString(), null)
    }

    @JvmStatic
    fun d(tag: String, msg: Any) {
        d(tag, msg.toString(), null)
    }

    @JvmStatic
    fun d(tag: String?, msg: Any, decoration: LogDecoration? = null) {
        if (useOtherLogEngine != null) {
            useOtherLogEngine?.deliver(tag, msg)
            return
        }
        useLogDecoration = decoration ?: customLogDecoration
        if (decoration == null) {
            stackTraceIndex = 6
        } else {
            stackTraceIndex = 5
        }
        printLog(D, tag, msg.toString())
    }

    @JvmStatic
    fun i(msg: Any) {
        i(null, msg.toString(), null)
    }

    @JvmStatic
    fun i(tag: String, msg: Any) {
        i(tag, msg.toString(), null)
    }

    @JvmStatic
    fun i(tag: String?, msg: Any, decoration: LogDecoration? = null) {
        if (useOtherLogEngine != null) {
            useOtherLogEngine?.deliver(tag, msg)
            return
        }
        useLogDecoration = decoration ?: customLogDecoration
        if (decoration == null) {
            stackTraceIndex = 6
        } else {
            stackTraceIndex = 5
        }
        printLog(I, tag, msg.toString())
    }

    @JvmStatic
    fun w(msg: Any) {
        w(null, msg.toString(), null)
    }

    @JvmStatic
    fun w(tag: String, msg: Any) {
        w(tag, msg.toString(), null)
    }

    @JvmStatic
    fun w(tag: String?, msg: Any, decoration: LogDecoration? = null) {
        if (useOtherLogEngine != null) {
            useOtherLogEngine?.deliver(tag, msg)
            return
        }
        useLogDecoration = decoration ?: customLogDecoration
        if (decoration == null) {
            stackTraceIndex = 6
        } else {
            stackTraceIndex = 5
        }
        printLog(W, tag, msg.toString())
    }


    @JvmStatic
    fun e(msg: Any) {
        e(null, msg.toString(), null)
    }

    @JvmStatic
    fun e(tag: String, msg: Any) {
        e(tag, msg.toString(), null)
    }

    @JvmStatic
    fun e(tag: String?, msg: Any, decoration: LogDecoration? = null) {
        if (useOtherLogEngine != null) {
            useOtherLogEngine?.deliver(tag, msg)
            return
        }
        useLogDecoration = decoration ?: customLogDecoration
        if (decoration == null) {
            stackTraceIndex = 6
        } else {
            stackTraceIndex = 5
        }
        printLog(E, tag, msg.toString())
    }

    @JvmStatic
    fun pretty(msg: Any) {
        pretty(null, msg)
    }

    @JvmStatic
    fun pretty(tag: String? = null, msg: Any) {
        if (useOtherLogEngine != null) {
            useOtherLogEngine?.deliver(tag, msg)
            return
        }
        useLogDecoration = prettyLogDecoration
        if (tag == null) {
            stackTraceIndex = 6
        } else {
            stackTraceIndex = 5
        }
        printLog(D, tag, msg.toString())
    }

    /**Print the Log of the file type. Set logDir and logFilePrefixName in the init() method before calling*/
    @JvmStatic
    fun file(msg: Any) {
        stackTraceIndex = 6
        file(null, msg.toString())
    }

    @JvmStatic
    fun file(tag: String? = null, msg: Any) {
        if (useOtherLogEngine != null) {
            useOtherLogEngine?.deliver(tag, msg)
            return
        }
        require(!(logDir == null || logFilePrefixName == null)) { "logDir = null or logFilePrefixName = null, you should set them before use" }
        useLogDecoration = fileLogDecoration
        if (tag == null) {
            stackTraceIndex = 6
        } else {
            stackTraceIndex = 5
        }
        printLog(D, tag, msg.toString())
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
        stackTraceIndex = 6
        json(null, msg)
    }

    @JvmStatic
    fun json(tag: String? = null, msg: Any) {
        if (useOtherLogEngine != null) {
            useOtherLogEngine?.deliver(tag, msg)
            return
        }
        useLogDecoration = jsonLogDecoration
        if (tag == null) {
            stackTraceIndex = 6
        } else {
            stackTraceIndex = 5
        }
        printLog(D, tag, msg.toString())
    }

    @JvmStatic
    fun stackTrace(msg: Any) {
        stackTraceIndex = 6
        stackTrace(null, msg)
    }

    @JvmStatic
    fun stackTrace(tag: String? = null, msg: Any) {
        if (useOtherLogEngine != null) {
            useOtherLogEngine?.deliver(tag, msg)
            return
        }
        useLogDecoration = stackLogDecoration
        if (tag == null) {
            stackTraceIndex = 6
        } else {
            stackTraceIndex = 5
        }
        printLog(D, tag, msg.toString())
    }

    private fun printLog(type: Int, tagStr: String?, objects: String) {
        if (!isShowLog) {
            return
        }
        val contents = wrapperContent(stackTraceIndex + logWrapperFlag, tagStr, objects)
        val tag = contents[0]
        val msg = contents[1]
        val headString = contents[2]

        when (type) {
            V, D, I, W, E, A -> Util.printDefault(type, tag, headString + msg)
//            JSON -> JsonLog.printJson(tag, msg, headString)
//            XML -> XmlLog.printXml(tag, msg, headString)
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
        val tag = tagStr ?: globalTag ?: className
        val msg = useLogDecoration.process(tag, objects)
        val headString = "[ ($className:$lineNumber)#$methodName ] "
        return arrayOf(tag, msg, headString)
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