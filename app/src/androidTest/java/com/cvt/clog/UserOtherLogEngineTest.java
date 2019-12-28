package com.cvt.clog;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import com.cvt.library.clog.CLog;

import org.junit.Test;

/**
 * Date: 2019-11-22 15:01
 * Author: chenchengyin on
 * Email: itmarshon@163.com
 * DESC:假如日后你不想用CLog了, 想切换其他库,你会使用到这个,最后的退路.
 */
public class UserOtherLogEngineTest {
    @Test
    public void log(){

        //日志初始化
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        String absolutePath = appContext.getCacheDir().getAbsolutePath();

        //切换到其他日志引擎
        CLog.init(true, "全局TAG", absolutePath, "ccy",
                null, new UseOtherLogEngine());
        CLog.d("TAG", "一条使用其他日志引擎打印的消息");
    }
}
