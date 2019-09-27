package com.cvt.library.clog

/**
 * Date: 2019-09-17 21:05
 * Author: chenchengyin on
 * Email: itmarshon@163.com
 */
open class LogDecoration {
    open fun process(tag:String ,msg: String):String {
        return msg
    }
}