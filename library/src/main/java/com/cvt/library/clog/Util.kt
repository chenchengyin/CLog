package com.cvt.library.clog

import android.util.Log
import com.cvt.library.clog.Constants.A
import com.cvt.library.clog.Constants.D
import com.cvt.library.clog.Constants.E
import com.cvt.library.clog.Constants.I
import com.cvt.library.clog.Constants.V
import com.cvt.library.clog.Constants.W

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
            V -> Log.v(tag, sub)
            D -> Log.d(tag, sub)
            I -> Log.i(tag, sub)
            W -> Log.w(tag, sub)
            E -> Log.e(tag, sub,e)
            A -> Log.wtf(tag, sub)
        }
    }

}
