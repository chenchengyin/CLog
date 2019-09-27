package com.cvt.library.clog

/**
 * Date: 2019-09-26 17:02
 * Author: chenchengyin on
 * Email: itmarshon@163.com
 */
class LogOptions {
    /**set global tag*/
    var globalTag: String? = null
    /**file log dir*/
    var logDir: String? = null
    /**file log name prefix*/
    var logFileNamePrefix: String? = null
    /**Decorate the log style with your favorite*/
    var customLogDecoration: LogDecoration? = LogDecoration()
    /**Use other print engines you like,if you set,please set customWrapper = true also*/
    var otherLogEngine: LogEngine? = null
    /**The engine for printing files can be either DefaultFleLogEngine or Log4aFileLogEngine*/
    var fileLogEngine: LogEngine? = DefaultFleLogEngine(logDir, logFileNamePrefix)
    /**Set to true if the tool is re encapsulated*/
    var customWrapper = false

}