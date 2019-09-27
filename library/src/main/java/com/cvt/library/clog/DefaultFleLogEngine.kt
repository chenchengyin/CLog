package com.cvt.library.clog

import java.io.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Date: 2019-09-26 16:57
 * Author: chenchengyin on
 * Email: itmarshon@163.com
 */
class DefaultFleLogEngine(var logDir: String?, var logFileNamePrefix: String?) : LogEngine {
    private val FILE_PREFIX = "Log_"
    private val FILE_FORMAT = ".log"

    private var logDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    private var logFileFormat = SimpleDateFormat("yyyy-MM-dd")


    override fun deliver(tag: String?, msg: Any) {
        if (logDir == null) {
            throw IllegalArgumentException("请调用CLog.init()设置日志文件夹名称")
        }
        if (logFileNamePrefix == null) {
            throw IllegalArgumentException("请调用CLog.init()设置日志文件名前缀")
        }
        val file = File(logDir, "Detail_" + getFileName())
        Runtime.getRuntime().exec("logcat -t 20 -f " + file.absoluteFile)
        save(File(logDir), getFileName(), msg.toString())
    }

    private fun save(dic: File, fileName: String, msg: String): Boolean {
        val pid = android.os.Process.myPid()
        var dateStr = logDateFormat.format(Date())
        val file = File(dic, fileName)
        var outputStream: FileOutputStream? = null
        var outputStreamWriter: OutputStreamWriter? = null
        try {
            outputStream = FileOutputStream(file, true)
            outputStreamWriter = OutputStreamWriter(outputStream, "UTF-8")
            outputStreamWriter.write("$dateStr  $pid  $msg \n")
            outputStreamWriter.flush()

            return true
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            return false
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
            return false
        } catch (e: IOException) {
            e.printStackTrace()
            return false
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        } finally {
            outputStream?.close()
            outputStreamWriter?.close()
        }
    }

    private fun getFileName(): String {
        var dateStr = logFileFormat.format(Date())
        return FILE_PREFIX + logFileNamePrefix + "_" + dateStr + FILE_FORMAT
    }
}