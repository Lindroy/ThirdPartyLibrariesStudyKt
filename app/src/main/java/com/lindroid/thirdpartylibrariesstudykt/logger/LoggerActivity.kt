package com.lindroid.thirdpartylibrariesstudykt.logger

import com.lindroid.thirdpartylibrariesstudykt.R
import com.lindroid.thirdpartylibrariesstudykt.base.BaseActivity
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_logger.*

class LoggerActivity : BaseActivity() {

    override fun getContentViewId(): Int = R.layout.activity_logger

    override fun initView() {
        super.initView()
        initToolBar(title = getString(R.string.Logger),isShowArrow = true)
    }

    override fun initOnClick() {
        super.initOnClick()
        btnLog.setOnClickListener {
            Logger.d("这是一条日志")
            Logger.e("这是一条日志")
            Logger.i("这是一条日志")
        }
    }

}
