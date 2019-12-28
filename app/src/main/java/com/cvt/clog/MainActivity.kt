package com.cvt.clog

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * 使用方法参照目录: app/src/androidTest/java/com/cvt/clog
         * 简单使用CLog使用 : com.cvt.clog.NormalLogTest.java
         * 多种样式呈现一条Log : com.cvt.clog.MultiStylesLogTest.java
         * 拓展支持打印文件: com.cvt.clog.LogFileTest.java
         * 给日志添加额外装饰或标记 : com.cvt.clog.DecorationLogTest.java
         * 二次封装CLog的做法 : com.cvt.clog.WrapperCLogTest.java
         * 假如日后你不想用CLog了,见: com.cvt.clog.UserOtherLogEngineTest.java
         *
         * DESC:Kotlin使用方式与Java类同
         */
    }
}
