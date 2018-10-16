package com.lindroid.thirdpartylibrariesstudykt.logger

import com.lindroid.thirdpartylibrariesstudykt.R
import com.lindroid.thirdpartylibrariesstudykt.base.BaseActivity
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_logger.*

class LoggerActivity : BaseActivity() {
    private val jsonStr = "{\"IsSuccess\":true,\"Decription\":\"操作成功\",\"Data\":{\"TimeStamp\":\"1539655188764\"},\"Flag\":1}"
    private val list = listOf("张三","李四","王五")

    override fun getContentViewId(): Int = R.layout.activity_logger

    override fun initView() {
        super.initView()
        initToolBar(title = getString(R.string.Logger),isShowArrow = true)
    }

    override fun initOnClick() {
        super.initOnClick()
        btnLog.setOnClickListener {
            Logger.d("这是一条日志")
        }
        btnJson.setOnClickListener {
            Logger.t("JsonTag").json(jsonStr)
        }
        btnList.setOnClickListener {
            Logger.d(list)
        }
    }

}
