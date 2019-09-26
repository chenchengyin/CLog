package com.cvt.library.clog

import org.json.JSONArray
import org.json.JSONObject

/**
 * Date: 2019-09-17 21:20
 * Author: chenchengyin on
 * Email: itmarshon@163.com
 */
open class JsonLogDecoration : BaseLogDecoration() {
    val JSON_INDENT = 4
    override fun process(tag: String, msg: String): String {
        var message: String
        if (msg.startsWith("{")) {
            var jsonObject = JSONObject(msg)
            message = jsonObject.toString(JSON_INDENT)
        } else if (msg.startsWith("[")) {
            var jsonArray = JSONArray(msg)
            message = jsonArray.toString(JSON_INDENT)
        } else {
            message = msg
        }
        return "\n"+message
    }
}