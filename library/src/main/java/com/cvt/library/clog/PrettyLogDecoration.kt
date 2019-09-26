package com.cvt.library.clog

/**
 * Date: 2019-09-17 21:09
 * Author: chenchengyin on
 * Email: itmarshon@163.com
 */
class PrettyLogDecoration : BaseLogDecoration() {
    companion object {
        val lineCount = 120
        val line = "══════════════════════════════════════════════════════════"
    }

    override fun process(tag: String, msg: String): String {
        val lineNum = msg.length / lineCount
        val sb = StringBuilder()
        var i = 0
        sb.append("\n╔$line")
        while (lineNum >= i) {
            var end = (i + 1) * lineCount
            if (end > msg.length) {
                end = msg.length
            }
            sb.append("\n║ ${msg.substring(i * lineCount, end)}")
            i++
        }
        sb.append("\n╚$line")
        return sb.toString()
    }
}