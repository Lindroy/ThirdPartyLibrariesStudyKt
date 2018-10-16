package com.lindroid.thirdpartylibrariesstudykt.base

import android.app.Application
import com.lindroid.thirdpartylibrariesstudykt.logger.LogCatStrategy
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy

/**
 * @author Lin
 * @date 2018/10/15
 * @function
 * @Description
 */
class App : Application() {

    companion object {
        lateinit var instance: App
    }

    init {
        instance = this
    }


    override fun onCreate() {
        super.onCreate()
        val formatStrategy = PrettyFormatStrategy.newBuilder()
                .logStrategy(LogCatStrategy())
                .tag("MyLog")
                .build()
        Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))

    }
}