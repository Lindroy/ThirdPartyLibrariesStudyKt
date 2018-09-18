package com.lindroid.thirdpartylibrariesstudykt.bgaphotopicker

import android.content.Intent
import com.lindroid.thirdpartylibrariesstudykt.R
import com.lindroid.thirdpartylibrariesstudykt.base.BaseActivity
import kotlinx.android.synthetic.main.activity_bgaphoto.*

class BGAPhotoActivity : BaseActivity() {

    override fun getContentViewId(): Int =R.layout.activity_bgaphoto

    override fun initView() {
        super.initView()
        initToolBar(getString(R.string.moment))
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary)
        swipeRefresh.setOnRefreshListener {

        }
    }

    override fun initOnClick() {
        super.initOnClick()
        btnAdd.setOnClickListener {
            startActivityForResult(Intent(context,MomentAddActivity::class.java),100)
        }
    }
}
