package com.cvt.library.clog

import android.content.Context
import android.util.Log
import me.pqpo.librarylog4a.Log4a
import me.pqpo.librarylog4a.appender.Appender
import me.pqpo.librarylog4a.appender.FileAppender
import me.pqpo.librarylog4a.logger.AppenderLogger
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

/**
 * Date: 2019-09-26 16:57
 * Author: chenchengyin on
 * Email: itmarshon@163.com
 * desc:推荐使用
 */
class FastFileLogEngine(var context: Context) : LogEngine {
    private val FILE_FORMAT = ".log"
    lateinit var logDir: String
    lateinit var logFileNamePrefix: String
    private var logDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    private var logFileFormat = SimpleDateFormat("yyyy-MM-dd")
    val BUFFER_SIZE = 1024 * 400 //400k
    val MAX_FILE_COUNT = 100

    fun init() {
        val logger = AppenderLogger.Builder()
            .addAppender(createLog4aFileAppender())
            .create()
        Log4a.setLogger(logger)

        val logFile = File(logDir)
        if (!logFile.exists()) {
            logFile.mkdir()
        }
        //当超过100个,删除一半
        val listFiles = logFile.listFiles()
        if (listFiles != null && listFiles.size >= MAX_FILE_COUNT) {
            val iterator = listFiles.iterator()
            repeat(MAX_FILE_COUNT / 2) {
                iterator.next().delete()
            }
        }
    }

    private fun createLog4aFileAppender(): Appender {
        val logFile = File(logDir, getFileName())
        val cacheFile = File(getCacheLogDir(context), "test.logCache")
        cacheFile.delete()
        val fileBuild = FileAppender.Builder(context)
            .setLogFilePath(logFile.getAbsolutePath())
            .setBufferSize(BUFFER_SIZE)
            .setBufferFilePath(cacheFile.getAbsolutePath())
        return fileBuild.create()
    }

    override fun deliver(type: Int, tag: String?, msg: Any) {
        if (logDir == null) {
            throw IllegalArgumentException("请调用CLog.init()设置日志文件夹名称")
        }
        if (logFileNamePrefix == null) {
            throw IllegalArgumentException("请调用CLog.init()设置日志文件名前缀")
        }
        val sb = StringBuilder()
        sb.append("[${logDateFormat.format(Date())}]")
            .append(" [${android.os.Process.myPid()}]")
            .append(" ${context.packageName}: ")
            .append(msg)
        when (type) {
            Log.VERBOSE -> Log4a.v(tag, sb.toString())
            Log.INFO -> Log4a.i(tag, sb.toString())
            Log.DEBUG -> Log4a.d(tag, sb.toString())
            Log.WARN -> Log4a.w(tag, sb.toString())
            Log.ERROR -> Log4a.e(tag, sb.toString())
        }
    }

    private fun getFileName(): String {
        var dateStr = logFileFormat.format(Date())
        return logFileNamePrefix + "_" + dateStr + FILE_FORMAT
    }

    fun getCacheLogDir(context: Context): File {
        var log = context.getExternalFilesDir("logs")
        if (log == null) {
            log = File(context.filesDir, "logs")
        }
        if (!log.exists()) {
            log.mkdir()
        }
        return log
    }

    fun flush() {
        Log4a.flush()
    }

    fun release() {
        Log4a.release()
    }
}