package com.cvt.clog;

import com.cvt.library.clog.CLog;

import org.junit.Test;

/**
 * Date: 2019-11-22 14:49
 * Author: chenchengyin on
 * Email: itmarshon@163.com
 * Desc:简单使用CLog
 */
public class NormalLogTest {

    @Test
    public void log(){
        CLog.init(true);
//        CLog.init(true, "全局TAG", absolutePath, "ccy");
        //打印一条消息
        CLog.v("一条普通的消息");
        CLog.v("TAG","一条普通的消息");
        CLog.d("一条普通的消息");
        CLog.d("TAG","一条普通的消息");
        CLog.i("一条普通的消息");
        CLog.i("TAG","一条普通的消息");
        CLog.w("一条普通的消息");
        CLog.w("TAG","一条普通的消息");
        CLog.e("一条普通的消息");
        CLog.e("TAG","一条普通的消息");
        CLog.e("一条普通的消息", new RuntimeException("error"));
        CLog.e("TAG","一条普通的消息", new MyLogDecoration(),new RuntimeException("error"));
    }
}
