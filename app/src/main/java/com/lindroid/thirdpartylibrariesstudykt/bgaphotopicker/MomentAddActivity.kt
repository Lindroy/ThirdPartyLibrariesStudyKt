package com.lindroid.thirdpartylibrariesstudykt.bgaphotopicker

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Environment
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import cn.bingoogolapple.photopicker.activity.BGAPhotoPickerActivity
import cn.bingoogolapple.photopicker.activity.BGAPhotoPickerPreviewActivity
import cn.bingoogolapple.photopicker.widget.BGASortableNinePhotoLayout
import com.lindroid.thirdpartylibrariesstudykt.R
import com.lindroid.thirdpartylibrariesstudykt.base.BaseActivity
import com.lindroid.thirdpartylibrariesstudykt.model.MomentModel
import kotlinx.android.synthetic.main.activity_moment_add.*
import org.jetbrains.anko.toast
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.OnPermissionDenied
import permissions.dispatcher.RuntimePermissions
import java.io.File
import java.util.*

/**
 * 发表朋友圈
 */
@RuntimePermissions
class MomentAddActivity : BaseActivity() {
    private val RC_CHOOSE_PHOTO = 100
    private val RC_PHOTO_PREVIEW = 200

    companion object {
        const val ADD_MOMENT = "ADD_MOMENT"
    }

    override fun getContentViewId(): Int = R.layout.activity_moment_add

    override fun initView() {
        super.initView()
        initToolBar(getString(R.string.add_moment))
        initNineLayout()
    }

    override fun initOnClick() {
        super.initOnClick()
        btnPublish.setOnClickListener {
            if(TextUtils.isEmpty(etContent.text.toString().trim()) && photoLayout.data.isEmpty()){
                toast("请填写内容或选择图片")
                return@setOnClickListener
            }
            val intent = Intent()
            intent.putExtra(ADD_MOMENT,MomentModel(content = etContent.text.toString(), photos = photoLayout.data))
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

    private fun initNineLayout() {
        photoLayout.setDelegate(object : BGASortableNinePhotoLayout.Delegate {
            override fun onClickNinePhotoItem(sortableNinePhotoLayout: BGASortableNinePhotoLayout?, view: View?, position: Int, model: String?, models: ArrayList<String>?) {
                val intent = BGAPhotoPickerPreviewActivity.IntentBuilder(context)
                        .previewPhotos(models)
                        .selectedPhotos(models)
                        .maxChooseCount(photoLayout.maxItemCount)
                        .currentPosition(position)
                        .isFromTakePhoto(false)
                        .build()
                startActivityForResult(intent, RC_PHOTO_PREVIEW)
            }

            override fun onClickAddNinePhotoItem(sortableNinePhotoLayout: BGASortableNinePhotoLayout?, view: View?, position: Int, models: ArrayList<String>?) {
                choosePhotoWithPermissionCheck()
            }

            override fun onNinePhotoItemExchanged(sortableNinePhotoLayout: BGASortableNinePhotoLayout?, fromPosition: Int, toPosition: Int, models: ArrayList<String>?) {
                toast("顺序改变了")
            }

            override fun onClickDeleteNinePhotoItem(sortableNinePhotoLayout: BGASortableNinePhotoLayout?, view: View?, position: Int, model: String?, models: ArrayList<String>?) {
                photoLayout.removeItem(position)
            }

        })
    }

    @NeedsPermission(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    fun choosePhoto() {
        val takePhotoDir = File(Environment.getExternalStorageDirectory(), "MyPickerTakePhoto")
        val intent = BGAPhotoPickerActivity.IntentBuilder(context)
                .cameraFileDir(takePhotoDir)
                .maxChooseCount(photoLayout.maxItemCount - photoLayout.itemCount)
                .selectedPhotos(null)
                .pauseOnScroll(false)
                .build()
        startActivityForResult(intent, RC_CHOOSE_PHOTO)
    }

    /**
     * 权限请求失败，但是用户还没有勾选不再询问
     */
    @OnPermissionDenied(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    fun deny() {
        Toast.makeText(this, "已拒绝一个或以上权限", Toast.LENGTH_SHORT).show()
        toast("权限已被拒绝")
    }

    @SuppressLint("NeedOnRequestPermissionsResult")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        onRequestPermissionsResult(requestCode, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) {
            return
        }

        when (requestCode) {
            RC_CHOOSE_PHOTO -> {
                photoLayout.addMoreData(BGAPhotoPickerActivity.getSelectedPhotos(data))
            }
            RC_PHOTO_PREVIEW -> {
                photoLayout.data = BGAPhotoPickerPreviewActivity.getSelectedPhotos(data)
            }
        }
    }
}
