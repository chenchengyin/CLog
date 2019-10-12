package com.cvt.clog

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val kotlinLogTest = KotlinLogTest()
        kotlinLogTest.testLog(applicationContext)
        Log.e("分割线", "======以下是Java的调用========")

        val javaLogTest = JavaLogTest()
        javaLogTest.testLog(applicationContext)

//        CLog.clearLogFile()
//        CLog.release() //用了Log4aLogEngin时,程序退出要调用这个
    }
}
