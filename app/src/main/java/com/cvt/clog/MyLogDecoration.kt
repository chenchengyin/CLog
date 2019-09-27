package com.cvt.clog

import com.cvt.library.clog.LogDecoration

/**
 * Date: 2019-09-21 09:36
 * Author: chenchengyin on
 * Email: itmarshon@163.com
 */
class MyLogDecoration : LogDecoration() {

    override fun process(tag: String, msg: String): String {
        return "[我是Log修饰] 内容:$msg"
    }

}