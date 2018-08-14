package com.lindroid.thirdpartylibrariesstudykt.bgabaseadapter

import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import cn.bingoogolapple.baseadapter.BGADivider
import cn.bingoogolapple.baseadapter.BGARecyclerViewAdapter
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper
import com.lindroid.thirdpartylibrariesstudykt.R
import com.lindroid.thirdpartylibrariesstudykt.base.BaseActivity
import kotlinx.android.synthetic.main.activity_base_adapter.*

class BaseAdapterActivity : BaseActivity() {
    private val titles: MutableList<String> = ArrayList()

    override fun getContentViewId(): Int = R.layout.activity_base_adapter

    override fun initBefore() {
        super.initBefore()
        for (i in 1 until 20) {
            titles.add("标题$i")
        }
    }

    override fun initView() {
        super.initView()
        initToolBar(title = getString(R.string.BGABaseAdapter))
        rv.layoutManager = LinearLayoutManager(context)
        //添加分割线
        rv.addItemDecoration(BGADivider.newBitmapDivider())
        rv.adapter = object : BGARecyclerViewAdapter<String>(rv, android.R.layout.simple_list_item_1) {
            override fun fillData(helper: BGAViewHolderHelper, position: Int, string: String?) {
                helper.setText(android.R.id.text1, string)
                //点击事件
                helper.setItemChildClickListener(android.R.id.text1)
                helper.setOnItemChildClickListener { parent, childView, position ->
                    Toast.makeText(context,"你点击了$position",Toast.LENGTH_SHORT).show()
                }
            }
        }.apply {
            data = titles
        }
    }
}
