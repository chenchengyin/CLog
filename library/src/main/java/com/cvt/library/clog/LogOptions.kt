package com.cvt.library.clog

/**
 * Date: 2019-09-26 17:02
 * Author: chenchengyin on
 * Email: itmarshon@163.com
 */
class LogOptions {
    /**设置全局标签 set global tag   */
    var globalTag: String? = null
    /**记录日志文件夹 file log dir*/
    var logDir: String? = null
    /**文件日志名称前缀 file log name prefix*/
    var logFileNamePrefix: String? = null
    /**用您喜欢的装饰装饰日志样式 Decorate the log style with your favorite*/
    var customLogDecoration: LogDecoration? = null
    /**使用其他喜欢的打印引擎,如有设置,请同时将customWrapper为true, Use other print engines you like,if you set,please set customWrapper = true also*/
    var globalLogEngine: LogEngine? = null
    /**打印文件的引擎可以是DefaultFleLogEngine或Log4aFileLogEngine The engine for printing files can be either DefaultFleLogEngine or Log4aFileLogEngine*/
    var fileLogEngine: LogEngine? = null
    /**如果重新封装该工具，则设置为true Set to true if the tool is re encapsulated*/
    var customWrapper = false
    /**输出到文件的日志等级,默认为INFO等级,即表示大于或等于Log.INFO等级的日志都会输出到文件中*/
    var fileLogLevel = Integer.MAX_VALUE
}