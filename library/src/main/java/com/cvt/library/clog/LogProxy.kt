package com.cvt.library.clog

import com.cvt.library.clog.Constants.D
import com.cvt.library.clog.Constants.E
import com.cvt.library.clog.Constants.I
import com.cvt.library.clog.Constants.V
import com.cvt.library.clog.Constants.W

/**
 * Date: 2019-09-18 18:59
 * Author: chenchengyin on
 * Email: itmarshon@163.com
 */
open class LogProxy {

    fun v(msg: Any) {
        CLog.printLog(V, null, msg.toString())
    }

    fun v(tag: String, msg: Any) {
        CLog.printLog(V, tag, msg.toString())
    }

    fun d(msg: Any) {
        CLog.printLog(D, null, msg.toString())
    }


    fun d(tag: String, msg: Any) {
        CLog.printLog(D, tag, msg.toString())
    }

    fun i(msg: Any) {
        CLog.printLog(I, null, msg.toString())
    }

    fun i(tag: String, msg: Any) {
        CLog.printLog(I, tag, msg.toString())
    }

    fun w(msg: Any) {
        CLog.printLog(W, null, msg.toString())
    }

    fun w(tag: String, msg: Any) {
        CLog.printLog(W, tag, msg.toString())
    }

    fun e(msg: Any) {
        CLog.printLog(E, null, msg.toString())
    }

    fun e(tag: String, msg: Any) {
        CLog.printLog(E, tag, msg.toString())
    }
}