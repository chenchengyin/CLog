package com.cvt.clog

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.cvt.library.clog.CLog

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val kotlinLogTest = KotlinLogTest()
        kotlinLogTest.testLog(applicationContext)
//
        Log.e("分割线", "======以下是Java的调用========")
        val javaLogTest = JavaLogTest()
        javaLogTest.testLog(applicationContext)

        CLog.clearLogFile()
    }
}
