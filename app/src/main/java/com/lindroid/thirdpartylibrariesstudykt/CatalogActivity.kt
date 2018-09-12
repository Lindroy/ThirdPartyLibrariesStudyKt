package com.lindroid.thirdpartylibrariesstudykt

import android.content.Intent
import android.widget.ArrayAdapter
import com.lindroid.thirdpartylibrariesstudykt.base.BaseActivity
import com.lindroid.thirdpartylibrariesstudykt.bgabaseadapter.FormActivity
import com.lindroid.thirdpartylibrariesstudykt.materialcalendarview.MaterialCalendarActivity
import com.lindroid.thirdpartylibrariesstudykt.nicespinner.NiceSpinnerActivity
import com.lindroid.thirdpartylibrariesstudykt.permissionsdispatcher.PermissionActivity
import com.lindroid.thirdpartylibrariesstudykt.xrichtext.XRichTextActivity
import kotlinx.android.synthetic.main.activity_catalog.*
import java.util.*
import kotlin.collections.ArrayList

/**
 * 目录页
 */
class CatalogActivity : BaseActivity() {

    private val map = TreeMap<String, Class<*>>()
    private val names: MutableList<String> = ArrayList()
    override fun getContentViewId(): Int {
        return R.layout.activity_catalog
    }


    override fun initBefore() {
        super.initBefore()
        map[getString(R.string.PermissionDispatcher)] = PermissionActivity::class.java
        map[getString(R.string.NiceSpinner)] = NiceSpinnerActivity::class.java
        map[getString(R.string.XRichText)] = XRichTextActivity::class.java
        map[getString(R.string.BGABaseAdapter)] = FormActivity::class.java
        map[getString(R.string.material_calendarview)] = MaterialCalendarActivity::class.java
        for (mutableEntry in map) {
            names.add(mutableEntry.key)
        }
    }

    override fun initView() {
        super.initView()
        initToolBar(getString(R.string.catalog), false)
        val arrayAdapter = ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, android.R.id.text1, names)
        list.adapter = arrayAdapter
        list.setOnItemClickListener { parent, view, position, id ->
            when (names[position]) {
                getString(R.string.PermissionDispatcher) -> startActivity(map[getString(R.string.PermissionDispatcher)])
                getString(R.string.NiceSpinner) -> startActivity(map[getString(R.string.NiceSpinner)])
                getString(R.string.XRichText) -> startActivity(map[getString(R.string.XRichText)])
                getString(R.string.BGABaseAdapter) -> startActivity(map[getString(R.string.BGABaseAdapter)])
                getString(R.string.material_calendarview) -> startActivity(map[getString(R.string.material_calendarview)])
            }
        }
    }

    private fun startActivity(cls: Class<*>?) {
        startActivity(Intent(context, cls))
    }


}
