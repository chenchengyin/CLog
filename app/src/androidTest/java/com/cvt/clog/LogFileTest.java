package com.cvt.clog;

import android.content.Context;
import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;

import com.cvt.library.clog.CLog;
import com.cvt.library.clog.FastFileLogEngine;
import com.cvt.library.clog.LogOptions;

import org.junit.Test;

/**
 * Date: 2019-11-22 10:49
 * Author: chenchengyin on
 * Email: itmarshon@163.com
 * Desc:测试打印文件
 */
public class LogFileTest {

    @Test
    public void log(){

        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        String absolutePath = appContext.getCacheDir().getAbsolutePath();
        Log.d("Clog","absolutePath:" + absolutePath);

        LogOptions logOptions = new LogOptions();
        //完整初始化示例
        logOptions.setLogDir(absolutePath);
        logOptions.setLogFileNamePrefix("logfile");
        logOptions.setFileLogEngine(new FastFileLogEngine(appContext));
        // 则设置为比ASSERT更大的值, 推荐Integer.MAX_VALUE,默认不开启
        logOptions.setFileLogLevel(Log.VERBOSE);
        CLog.init(true, logOptions);

        CLog.e("TAG","----测试打印文件----");
        CLog.v("一条普通的消息v");
        CLog.v("TAG","一条普通的消息v");
        CLog.d("一条普通的消息d");
        CLog.d("TAG","一条普通的消息d");
        CLog.i("一条普通的消息i");
        CLog.i("TAG","一条普通的消息i");
        CLog.w("一条普通的消息w");
        CLog.w("TAG","一条普通的消息w");
        CLog.e("一条普通的消息e");
        CLog.e("TAG","一条普通的消息e");
        CLog.e("一条普通的消息e", new RuntimeException("error"));
        CLog.e("TAG","一条普通的消息e",
                new MyLogDecoration(),
                new RuntimeException("error"));

        //将日志写入文件中并清除内存缓存,建议在当前页面消失时调用
        CLog.flush();
        //程序退出时使用,将日志写入文件并释放资源,一旦释放资源,再次使用需要重新init
        CLog.release();
    }
}
