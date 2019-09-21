package com.cvt.clog

import android.util.Log
import com.cvt.library.clog.LogEngine

/**
 * Date: 2019-09-21 09:51
 * Author: chenchengyin on
 * Email: itmarshon@163.com
 */
class UseOtherLogEngine :LogEngine{
    override fun deliver(tag: String?, msg: Any) {
        Log.i(tag, "来自其他打印库的日志:${msg.toString()}")
    }
}