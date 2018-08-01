package com.lindroid.thirdpartylibrariesstudykt

import android.content.Intent
import android.widget.ArrayAdapter
import com.lindroid.thirdpartylibrariesstudykt.base.BaseActivity
import com.lindroid.thirdpartylibrariesstudykt.permissionsdispatcher.PermissionActivity
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
        map[getString(R.string.PersmissionDispatcher)] = PermissionActivity::class.java
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
                getString(R.string.PersmissionDispatcher) -> startActivity(map[getString(R.string.PersmissionDispatcher)])
            }
        }
    }

    private fun startActivity(cls: Class<*>?) {
        startActivity(Intent(context, cls))
    }

}
