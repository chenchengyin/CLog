package com.cvt.library.clog

/**
 * Date: 2019-09-17 21:09
 * Author: chenchengyin on
 * Email: itmarshon@163.com
 */
class FileLogDecoration(
    var fileLogEngine: LogEngine
) : BaseLogDecoration() {


    override fun process(tag: String, msg: String): String {
        fileLogEngine.deliver(tag,msg)
        return msg
    }
}