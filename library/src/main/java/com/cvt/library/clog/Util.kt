package com.cvt.library.clog

import android.util.Log

/**
 * Created by zhaokaiqiang on 15/11/18.
 */
object Util {

    private val MAX_LENGTH = 4000

    fun printDefault(type: Int, tag: String, msg: String, e: Throwable?) {

        var index = 0
        val length = msg.length
        val countOfSub = length / MAX_LENGTH

        if (countOfSub > 0) {
            for (i in 0 until countOfSub) {
                val sub = msg.substring(index, index + MAX_LENGTH)
                printSub(type, tag, sub, e)
                index += MAX_LENGTH
            }
            printSub(type, tag, msg.substring(index, length),e)
        } else {
            printSub(type, tag, msg,e)
        }
    }

    private fun printSub(type: Int, tag: String, sub: String, e: Throwable?) {
        when (type) {
            Log.VERBOSE -> Log.v(tag, sub)
            Log.DEBUG -> Log.d(tag, sub)
            Log.INFO -> Log.i(tag, sub)
            Log.WARN -> Log.w(tag, sub)
            Log.ERROR -> Log.e(tag, sub,e)
            Log.ASSERT -> Log.wtf(tag, sub)
        }
    }

}
