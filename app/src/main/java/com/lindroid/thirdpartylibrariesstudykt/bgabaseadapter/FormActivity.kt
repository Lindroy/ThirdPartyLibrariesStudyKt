package com.lindroid.thirdpartylibrariesstudykt.bgabaseadapter

import android.support.v7.widget.LinearLayoutManager
import com.lindroid.thirdpartylibrariesstudykt.R
import com.lindroid.thirdpartylibrariesstudykt.base.BaseActivity
import com.lindroid.thirdpartylibrariesstudykt.bgabaseadapter.adapter.FormAdapter
import com.lindroid.thirdpartylibrariesstudykt.model.FormModel
import kotlinx.android.synthetic.main.activity_form.*

/**
 * 信息录入
 */
class FormActivity : BaseActivity() {

    private val formList:MutableList<FormModel> = ArrayList()

    override fun getContentViewId() = R.layout.activity_form

    override fun initBefore() {
        super.initBefore()
        formList.add(FormModel(itemId = 100,title = "单行01",type = FormModel.TYPE_SINGLE_INPUT))
        formList.add(FormModel(itemId = 101,title = "单行02",type = FormModel.TYPE_SINGLE_INPUT))
        formList.add(FormModel(itemId = 102,title = "单行03",type = FormModel.TYPE_SINGLE_INPUT))
        formList.add(FormModel(itemId = 103,title = "多行01",type = FormModel.TYPE_MULTIPLE_INPUT))
        formList.add(FormModel(itemId = 104,title = "多行02",type = FormModel.TYPE_MULTIPLE_INPUT))
        formList.add(FormModel(itemId = 105,title = "单选01",type = FormModel.TYPE_SINGLE_CHOICE))
        formList.add(FormModel(itemId = 106,title = "单选02",type = FormModel.TYPE_SINGLE_CHOICE))
        formList.add(FormModel(itemId = 107,title = "单选03",type = FormModel.TYPE_SINGLE_CHOICE))
        formList.add(FormModel(itemId = 108,title = "多选01",type = FormModel.TYPE_MULTIPLE_CHOICE))
        formList.add(FormModel(itemId = 109,title = "多选02",type = FormModel.TYPE_MULTIPLE_CHOICE))
    }

    override fun initView() {
        super.initView()
        initToolBar(title = "信息录入")
        rvForm.layoutManager = LinearLayoutManager(context)
        rvForm.adapter = FormAdapter(rvForm,formList)

    }
}
