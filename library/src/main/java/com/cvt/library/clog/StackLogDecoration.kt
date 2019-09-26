package com.cvt.library.clog

import android.util.Log

/**
 * Date: 2019-09-17 21:09
 * Author: chenchengyin on
 * Email: itmarshon@163.com
 */
class StackLogDecoration : BaseLogDecoration() {
    companion object{
        val  line = "═══════════════════════════════════════════════════════════════════════════════════════"
    }

    override fun process(tag: String, msg: String): String {
        var sb = StringBuilder()
        sb.append("\n╔$line\n")
        sb.append("║ $msg")
        sb.append("\n╚$line\n")
        sb.append("╔$line\n")
        sb.append(Log.getStackTraceString(Throwable("当前调用栈如下:")))
        sb.append("╚$line\n")
        return sb.toString()
    }
}