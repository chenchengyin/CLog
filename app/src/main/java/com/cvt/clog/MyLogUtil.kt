package com.cvt.clog

import com.cvt.library.clog.CLog

/**
 * Date: 2019-09-26 22:32
 * Author: chenchengyin on
 * Email: itmarshon@163.com
 */
object MyLogUtil {

    fun d(msg:String){
        CLog.i("$msg")
    }

}