package com.lindroid.thirdpartylibrariesstudykt.base

import android.app.Application

/**
 * @author Lin
 * @date 2018/10/15
 * @function
 * @Description
 */
class App :Application()  {

    companion object {
        lateinit var instance:App
    }

    init {
        instance = this
    }



    override fun onCreate() {
        super.onCreate()

    }
}