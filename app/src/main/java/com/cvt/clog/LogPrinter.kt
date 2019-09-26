package com.cvt.clog

import com.cvt.library.clog.CLog

/**
 * Date: 2019-09-26 22:32
 * Author: chenchengyin on
 * Email: itmarshon@163.com
 */
object LogPrinter {

    fun d(msg:String){
        CLog.i("$msg")
    }

}