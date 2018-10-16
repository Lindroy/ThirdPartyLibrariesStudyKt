package com.lindroid.thirdpartylibrariesstudykt.alog

import android.os.Bundle
import com.lindroid.thirdpartylibrariesstudykt.R
import com.lindroid.thirdpartylibrariesstudykt.base.BaseActivity

class ALogActivity : BaseActivity() {
    override fun getContentViewId(): Int=R.layout.activity_alog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alog)
    }
}
