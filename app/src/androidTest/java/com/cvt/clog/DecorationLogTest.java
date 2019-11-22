package com.cvt.clog;

import com.cvt.library.clog.CLog;

import org.junit.Test;

/**
 * Date: 2019-11-22 15:00
 * Author: chenchengyin on
 * Email: itmarshon@163.com
 * DESC:给日志添加额外装饰或标记
 */
public class DecorationLogTest {

    @Test
    public void log(){

        //自定义装饰
        CLog.init(true, "全局TAG",null,null,
                new MyLogDecoration());
        CLog.i("一条带装饰的日志"); //全局
        CLog.i("TAG", "临时的装饰日志", new MyLogDecoration());
    }
}
