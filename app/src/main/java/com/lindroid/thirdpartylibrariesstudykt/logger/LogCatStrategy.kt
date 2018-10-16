package com.lindroid.thirdpartylibrariesstudykt.logger

import android.util.Log
import com.orhanobut.logger.LogStrategy

/**
 * @author Lin
 * @date 2018/10/16
 * @function 解决AS3.1下Logger日志打印错位不整齐的问题
 * @Description
 */
class LogCatStrategy:LogStrategy {
    private var last = 0
    /**
     * This is invoked by Logger each time a log message is processed.
     * Interpret this method as last destination of the log in whole pipeline.
     *r
     * @param priority is the log level e.g. DEBUG, WARNING
     * @param tag is the given tag for the log message.
     * @param message is the given message for the log message.
     */
    override fun log(priority: Int, tag: String?, message: String) {
        Log.println(priority, "${randomKey()}$tag", message)
    }

    private fun randomKey():String{
        var random = (Math.random() * 10).toInt()
        if (random == last){
            random = (random + 1) % 10
        }
        last = random
        return random.toString()
    }
}