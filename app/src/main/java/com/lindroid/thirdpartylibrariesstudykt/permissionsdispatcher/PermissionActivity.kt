package com.lindroid.thirdpartylibrariesstudykt.permissionsdispatcher

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AlertDialog
import android.widget.Toast
import com.lindroid.thirdpartylibrariesstudykt.R
import com.lindroid.thirdpartylibrariesstudykt.base.BaseActivity
import kotlinx.android.synthetic.main.activity_permission.*
import permissions.dispatcher.*


/**
 * permissionsdispatcher库
 */
@RuntimePermissions
class PermissionActivity : BaseActivity() {

    override fun getContentViewId(): Int {
        return R.layout.activity_permission
    }

    override fun initView() {
        super.initView()
        initToolBar(title = getString(R.string.PermissionDispatcher))
    }

    override fun initOnClick() {
        super.initOnClick()
        btnActivity.setOnClickListener {
            //方法名是@NeedsPermission中的方法名+WithPermissionCheck
            callPhoneWithPermissionCheck()
        }
    }

    @NeedsPermission(Manifest.permission.CALL_PHONE)
    fun callPhone() {
        val intent = Intent()
        intent.action = Intent.ACTION_CALL//调用系统的打电话界面
        intent.data = Uri.parse("tel:1008611")
        startActivity(intent)
    }

    /**
     * 用于标注申请权限前需要执行的方法，告诉用户需求该权限的原因，只有当第一次请求权限被用户拒绝，下次请求权限之前会调用
     */
    @OnShowRationale(Manifest.permission.CALL_PHONE)
    fun show(request: PermissionRequest) {
        AlertDialog.Builder(this)
                .setMessage("使用此功能需要拨打电话权限，下一步将继续请求权限")
                .setPositiveButton("下一步") { dialog, which ->
                    request.proceed()//继续执行请求
                }.setNegativeButton("取消") { dialog, which ->
                    request.cancel()//取消执行请求
                }
                .show()
    }

    /**
     * 权限请求失败，但是用户还没有勾选不再询问
     */
    @OnPermissionDenied(Manifest.permission.CALL_PHONE)
    fun deny() {
        Toast.makeText(this, "已拒绝一个或以上权限", Toast.LENGTH_SHORT).show()
    }

    /**
     * 不再询问
     */
    @OnNeverAskAgain(Manifest.permission.CALL_PHONE)
    fun neverAsk() {
        Toast.makeText(this, "永久拒绝", Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("NeedOnRequestPermissionsResult")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // NOTE: delegate the permission handling to generated method
        onRequestPermissionsResult(requestCode, grantResults)
    }
}
