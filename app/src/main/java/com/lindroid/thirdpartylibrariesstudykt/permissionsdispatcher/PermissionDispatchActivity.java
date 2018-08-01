package com.lindroid.thirdpartylibrariesstudykt.permissionsdispatcher;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lindroid.thirdpartylibrariesstudykt.R;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class PermissionDispatchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_dispatch);
    }

    public void callPhone(View view) {
        PermissionDispatchActivityPermissionsDispatcher.callWithCheckWithPermissionCheck(this);
    }


    @NeedsPermission(Manifest.permission.CALL_PHONE)
    void callWithCheck() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);//调用系统的打电话界面
        intent.setData(Uri.parse("tel:" + "1008611"));
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionDispatchActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @OnShowRationale(Manifest.permission.CALL_PHONE)
    void showRationale(final PermissionRequest request) {
    }

    @OnPermissionDenied(Manifest.permission.CALL_PHONE)
    void deny() {
    }

    @OnNeverAskAgain(Manifest.permission.CALL_PHONE)
    void neverAsk() {
    }
}
