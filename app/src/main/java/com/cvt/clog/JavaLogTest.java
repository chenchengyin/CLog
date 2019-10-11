package com.cvt.clog;

import android.content.Context;
import com.cvt.library.clog.CLog;
import com.cvt.library.clog.DefaultFleLogEngine;
import com.cvt.library.clog.Log4aFileLogEngine;
import com.cvt.library.clog.LogOptions;

/**
 * Date: 2019-09-07 09:52
 * Author: chenchengyin on
 * Email: itmarshon@163.com
 */
public class JavaLogTest {


    public void testLog(Context applicationContext){
        //日志初始化
        String absolutePath = applicationContext.getCacheDir().getAbsolutePath();
//        CLog.init(true);
        CLog.init(true, "全局TAG", absolutePath, "ccy");
        //打印一条消息
        CLog.v("一条普通的消息");
        CLog.v("TAG","一条普通的消息");
        CLog.i("一条普通的消息");
        CLog.i("TAG","一条普通的消息");
        CLog.d("一条普通的消息");
        CLog.d("TAG","一条普通的消息");
        CLog.w("一条普通的消息");
        CLog.w("TAG","一条普通的消息");
        CLog.e("一条普通的消息");
        CLog.e("一条普通的消息",new RuntimeException("error"));
        CLog.e("TAG","一条普通的消息");
        CLog.e("TAG","一条普通的消息", new MyLogDecoration(),new RuntimeException("error"));

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

        //打印消息到控制台和文件,需初始化时提前将日志文件夹,文件名等信息设置好
        CLog.file("打印到日志到文件");

        //自定义装饰
        CLog.init(true, "全局TAG", absolutePath, "ccy",
                new MyLogDecoration());
        CLog.i("一条带装饰的日志"); //全局
        CLog.i("TAG", "临时的装饰日志", new MyLogDecoration());

        //切换到其他日志引擎
        CLog.init(true, "全局TAG", absolutePath, "ccy",
                null, new UseOtherLogEngine());
        CLog.d("TAG", "一条使用其他日志引擎打印的消息");

        CLog.init(true, "全局TAG", absolutePath, "ccy");
        CLog.d("这是一条普通的消息");
        CLog.d("自定义TAG","这是一条带自定义TAG的消息");
        CLog.d("自定义TAG","这是一条带自定义TAG的消息",new MyLogDecoration());

        //打印消息到控制台和文件,需初始化时提前将日志文件夹,文件名等信息设置好
        CLog.file("Java打印到日志到文件");

        //自定义装饰
        CLog.init(true, "全局TAG", absolutePath, "ccy",new  MyLogDecoration());
        CLog.i("一条带装饰的日志"); //全局
        CLog.i("TAG", "临时的装饰日志",new  MyLogDecoration());

        //切换到其他日志引擎
        CLog.init(true, "全局TAG", absolutePath, "ccy", null,
                new UseOtherLogEngine());
        CLog.d("TAG", "一条使用其他日志引擎打印的消息");

        LogOptions logOptions = new LogOptions();
        logOptions.setLogDir(absolutePath);
        logOptions.setLogFileNamePrefix("ccy");
        logOptions.setFileLogEngine(new Log4aFileLogEngine(applicationContext));
        CLog.init(true, logOptions);
        CLog.file("使用Log4a打印到文件");


        //封装后打印的log
        logOptions.setCustomWrapper(true);
        CLog.init(true, logOptions);
        LogPrinter.INSTANCE.d("二次封装打印");

        //初始化完整示例
        logOptions = new LogOptions();
        logOptions.setGlobalLogEngine(new UseOtherLogEngine()); //UseOtherLogEngine是自定义全局打印引擎,设置则表示全局打印行为采用该引擎(包括文件引擎),不推荐设置
        logOptions.setLogDir(absolutePath);
        logOptions.setLogFileNamePrefix("ccy");
        logOptions.setFileLogEngine(new Log4aFileLogEngine(applicationContext));
        logOptions.setFileLogEngine(new DefaultFleLogEngine( absolutePath, "ccy")); //文件类型打印引擎,可选Log4aFileLogEngine(),注意和globalLogEngine的区别
        logOptions.setCustomWrapper(false);  //默认为false,对需要对CLog做二次封装,则设置为true
        logOptions.setGlobalTag("TAG");  //全局TAG,如不为空则表示所有打印行为都采用此TAG,否则为指定的TAG或当前类名称,默认为当前类名称
        CLog.init(true, logOptions);


    }
}
