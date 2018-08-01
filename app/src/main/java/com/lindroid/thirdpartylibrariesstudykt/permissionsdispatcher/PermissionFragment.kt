package com.lindroid.thirdpartylibrariesstudykt.permissionsdispatcher

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.lindroid.thirdpartylibrariesstudykt.R
import kotlinx.android.synthetic.main.fragment_permission.*
import permissions.dispatcher.*


@RuntimePermissions
class PermissionFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_permission, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnCall.setOnClickListener {
            callPhoneWithPermissionCheck()
        }
    }


/*
    companion object {
        *//**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PermissionFragment.
         *//*
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                PermissionFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }*/
    @NeedsPermission(Manifest.permission.CALL_PHONE)
    fun callPhone() {
        val intent = Intent()
        intent.action = Intent.ACTION_CALL//调用系统的打电话界面
        intent.data = Uri.parse("tel:1008611")
        startActivity(intent)
    }

    @SuppressLint("NeedOnRequestPermissionsResult")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        onRequestPermissionsResult(requestCode, grantResults)
    }

    @OnShowRationale(Manifest.permission.CALL_PHONE)
    fun show(request: PermissionRequest) {
        AlertDialog.Builder(this.context!!)
                .setMessage("使用此功能需要拨打电话权限，下一步将继续请求权限")
                .setPositiveButton("下一步") { dialog, which ->
                    request.proceed()//继续执行请求
                }.setNegativeButton("取消") { dialog, which ->
                    request.cancel()//取消执行请求
                }
                .show()
    }

    @OnPermissionDenied(Manifest.permission.CALL_PHONE)
    fun deny() {
        Toast.makeText(context, "已拒绝一个或以上权限", Toast.LENGTH_SHORT).show()

    }

    @OnNeverAskAgain(Manifest.permission.CALL_PHONE)
    fun neverAsk() {
        Toast.makeText(context, "永久拒绝", Toast.LENGTH_SHORT).show()

    }
}
