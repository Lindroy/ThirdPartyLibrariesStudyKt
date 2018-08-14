package com.lindroid.thirdpartylibrariesstudykt.xrichtext

import com.lindroid.thirdpartylibrariesstudykt.R
import com.lindroid.thirdpartylibrariesstudykt.base.BaseActivity

class XRichTextActivity : BaseActivity() {

    override fun getContentViewId(): Int = R.layout.activity_xrich_text

    override fun initView() {
        super.initView()
        initToolBar(title = getString(R.string.XRichText))
    }
}
