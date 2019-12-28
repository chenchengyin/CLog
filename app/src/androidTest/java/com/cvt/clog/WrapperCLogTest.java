package com.cvt.clog;

import com.cvt.library.clog.CLog;
import com.cvt.library.clog.LogOptions;

import org.junit.Test;

/**
 * Date: 2019-11-22 15:05
 * Author: chenchengyin on
 * Email: itmarshon@163.com
 * DESC:二次封装CLog的做法
 */
public class WrapperCLogTest {

    @Test
    public void log(){
        //对库进行二次封装
        LogOptions logOptions = new LogOptions();
        logOptions.setCustomWrapper(true);
        CLog.init(true, logOptions);
        MyLogUtil.INSTANCE.d("二次封装打印");
    }
}
