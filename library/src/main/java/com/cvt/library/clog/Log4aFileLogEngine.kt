package com.cvt.library.clog

import android.content.Context
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
 */
class Log4aFileLogEngine(var context: Context) : LogEngine {
    private val FILE_PREFIX = "Log_"
    private val FILE_FORMAT = ".log"
    lateinit var logDir: String
    lateinit var logFileNamePrefix: String
    private var logDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    private var logFileFormat = SimpleDateFormat("yyyy-MM-dd")
    val BUFFER_SIZE = 1024 * 400 //400k
    val MAX_FILE_COUNT = 100

    fun init() {
        val logger = AppenderLogger.Builder().create()
        Log4a.setLogger(logger)
        logger.addAppender(createLog4aFileAppender())

        //当超过100个,删除一半
        val logFile = File(logDir)
        val listFiles = logFile.listFiles()
        if (listFiles != null && listFiles.size >= MAX_FILE_COUNT){
            val iterator = listFiles.iterator()
            repeat(1){
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

    override fun deliver(tag: String?, msg: Any) {
        if (logDir == null) {
            throw IllegalArgumentException("请调用CLog.init()设置日志文件夹名称")
        }
        if (logFileNamePrefix == null) {
            throw IllegalArgumentException("请调用CLog.init()设置日志文件名前缀")
        }
        Log4a.i(tag, msg.toString())
    }


    private fun getFileName(): String {
        var dateStr = logFileFormat.format(Date())
        return FILE_PREFIX + logFileNamePrefix + "_" + dateStr + FILE_FORMAT
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

    fun release() {
        Log4a.flush()
        Log4a.release()
    }
}