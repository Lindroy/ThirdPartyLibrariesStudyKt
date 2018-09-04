package com.lindroid.thirdpartylibrariesstudykt.nicespinner

import com.lindroid.thirdpartylibrariesstudykt.R
import com.lindroid.thirdpartylibrariesstudykt.base.BaseActivity
import kotlinx.android.synthetic.main.activity_nice_spinner.*

/**
 * NiceSpinner
 * https://github.com/arcadefire/nice-spinner
 */
class NiceSpinnerActivity : BaseActivity() {

    private val dataList = listOf("周一","周二","周三","周四","周五","周六","周七")

    override fun getContentViewId(): Int {
        return R.layout.activity_nice_spinner
    }

    override fun initView() {
        super.initView()
        initToolBar(title = getString(R.string.NiceSpinner))
        niceSpinner.attachDataSource(dataList)
    }
}
