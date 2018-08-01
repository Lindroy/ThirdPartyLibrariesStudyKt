package com.lindroid.thirdpartylibrariesstudykt.base

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.lindroid.thirdpartylibrariesstudykt.R
import kotlinx.android.synthetic.main.toolbar.view.*

/**
 * @author Lin
 * @date 2018/7/31
 * @function
 */
abstract class BaseActivity : AppCompatActivity() {
    lateinit var context:Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        initBefore()
        setContentView(getContentViewId())
        initView()
        initOnClick()
    }

    open fun initOnClick() {

    }

    open fun initBefore() {

    }

    open fun initView() {

    }
    abstract fun getContentViewId(): Int

    fun initToolBar(title: String = getString(R.string.app_name), isShowArrow: Boolean = true) {
        val toolView = window.decorView
        toolView.toolBar.title = title
        //ToolBar的属性设置要在setSupportActionBar方法之前调用
        setSupportActionBar(toolView.toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(isShowArrow)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            android.R.id.home->finish()

        }
        return true
    }

}