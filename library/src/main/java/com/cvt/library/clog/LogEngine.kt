package com.cvt.library.clog

/**
 * Date: 2019-09-21 09:50
 * Author: chenchengyin on
 * Email: itmarshon@163.com
 */
interface LogEngine {
    fun deliver(level:Int,tag : String?,msg:Any)
}