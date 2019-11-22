package com.cvt.clog;

import com.cvt.library.clog.CLog;

import org.junit.Test;

/**
 * Date: 2019-11-22 14:55
 * Author: chenchengyin on
 * Email: itmarshon@163.com
 * Desc:多种样式呈现一条Log
 */
public class MultiStylesLogTest {

    @Test
    public void log(){
        CLog.init(true);
        //自带TAG
        CLog.d("我是自定义TAG", "这是一条带自定义TAG的消息");
        //漂亮地呈现一条log
        StringBuilder builder = new StringBuilder();
        int count = 0;
        while (count <= 100) {
            builder.append("这是一条很长的消息"+count);
            count++;
        }
        String toString = builder.toString();
        CLog.pretty(toString);
        CLog.pretty("TAG",toString);

        //打印Json
        CLog.json("{\"key\":\"va\"}");
        CLog.json("TAG","{\"key\":\"va\"}");

        //打印消息,并带上当前的调用栈
        CLog.stackTrace("看一看调用栈");
        CLog.stackTrace("TAG","看一看调用栈");
    }
}
