package com.lindroid.thirdpartylibrariesstudykt.bgaphotopicker

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Handler
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import cn.bingoogolapple.baseadapter.BGARecyclerViewAdapter
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper
import cn.bingoogolapple.photopicker.activity.BGAPhotoPreviewActivity
import cn.bingoogolapple.photopicker.widget.BGANinePhotoLayout
import com.lindroid.thirdpartylibrariesstudykt.R
import com.lindroid.thirdpartylibrariesstudykt.base.BaseActivity
import com.lindroid.thirdpartylibrariesstudykt.model.MomentModel
import kotlinx.android.synthetic.main.activity_bgaphoto.*
import org.jetbrains.anko.toast
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.OnPermissionDenied
import permissions.dispatcher.RuntimePermissions

@RuntimePermissions
class BGAPhotoActivity : BaseActivity() {
    private val momentList: MutableList<MomentModel> = ArrayList()
    private lateinit var adapter: BGARecyclerViewAdapter<MomentModel>
    private lateinit var curPhotoLayout: BGANinePhotoLayout

    override fun getContentViewId(): Int = R.layout.activity_bgaphoto

    override fun initBefore() {
        super.initBefore()
        momentList.add(MomentModel(content = "1张图片", photos = arrayListOf("http://img5.adesk.com/5b5f4e6ce7bce75ea7b2f66b")))
        momentList.add(MomentModel(content = "2张图片", photos = arrayListOf("http://img5.adesk.com/5b8390d2e7bce75e7ef49a8b", "http://img5.adesk.com/5b73c41ee7bce75db261dde2")))
        momentList.add(MomentModel(content = "3张图片", photos = arrayListOf("http://img5.adesk.com/5b56d649e7bce706337a8e62", "http://img5.adesk.com/5ae035fee7bce735802a6a51", "http://img5.adesk.com/5ad9d0a1e7bce736590a775f")))
        momentList.add(MomentModel(content = "4张图片", photos = arrayListOf("http://img5.adesk.com/5ac8b996e7bce7356a197c88", "http://img5.adesk.com/5a80fcf0e7bce7366de28469"
                , "http://img5.adesk.com/5a8000d6e7bce73542def99b", "http://img5.adesk.com/5a45eecee7bce736a953c099")))
        momentList.add(MomentModel(content = "5张图片", photos = arrayListOf("http://img5.adesk.com/5a2677aee7bce735ec41a319", "http://img5.adesk.com/5b757153e7bce75db261de1f", "http://img5.adesk.com/59fab32fe7bce73542def0fe"
                , "http://img5.adesk.com/59a6618ee7bce77b1b837dd7", "http://img5.adesk.com/5992ad35e7bce77b3df8404a")))
        momentList.add(MomentModel(content = "6张图片", photos = arrayListOf("http://img5.adesk.com/5b56d649e7bce706337a8e62", "http://img5.adesk.com/5ae035fee7bce735802a6a51", "http://img5.adesk.com/5ad9d0a1e7bce736590a775f"
                , "http://img5.adesk.com/59a6618ee7bce77b1b837dd7", "http://img5.adesk.com/5992ad35e7bce77b3df8404a", "http://img5.adesk.com/594cd95de7bce77ab0512d8a")))
        momentList.add(MomentModel(content = "7张图片", photos = arrayListOf("http://img5.adesk.com/5b5f4e6ce7bce75ea7b2f66b", "http://img5.adesk.com/5ae035fee7bce735802a6a51", "http://img5.adesk.com/5ad9d0a1e7bce736590a775f"
                , "http://img5.adesk.com/59a6618ee7bce77b1b837dd7", "http://img5.adesk.com/5992ad35e7bce77b3df8404a", "http://img5.adesk.com/594cd95de7bce77ab0512d8a", "http://img5.adesk.com/58f499cbe7bce70dc2ca9a1b")))
        momentList.add(MomentModel(content = "8张图片", photos = arrayListOf("http://img5.adesk.com/5b8cd00de7bce75db261df62", "http://img5.adesk.com/5b5f4e6ce7bce75ea7b2f66b", "http://img5.adesk.com/5ae035fee7bce735802a6a51", "http://img5.adesk.com/5ad9d0a1e7bce736590a775f"
                , "http://img5.adesk.com/59a6618ee7bce77b1b837dd7", "http://img5.adesk.com/5992ad35e7bce77b3df8404a", "http://img5.adesk.com/594cd95de7bce77ab0512d8a", "http://img5.adesk.com/58f499cbe7bce70dc2ca9a1b")))
        momentList.add(MomentModel(content = "9张图片", photos = arrayListOf("http://img5.adesk.com/5b8611d7e7bce75e0214b88c", "http://img5.adesk.com/5b8cd00de7bce75db261df62", "http://img5.adesk.com/5b5f4e6ce7bce75ea7b2f66b", "http://img5.adesk.com/5ae035fee7bce735802a6a51", "http://img5.adesk.com/5ad9d0a1e7bce736590a775f"
                , "http://img5.adesk.com/59a6618ee7bce77b1b837dd7", "http://img5.adesk.com/5992ad35e7bce77b3df8404a", "http://img5.adesk.com/5b76510be7bce75e2ae8ddf1", "http://img5.adesk.com/58f499cbe7bce70dc2ca9a1b")))

    }

    override fun initView() {
        super.initView()
        initToolBar(getString(R.string.moment))
        initAdapter()
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary)
        swipeRefresh.setOnRefreshListener {
            Handler(Handler.Callback {
                swipeRefresh.isRefreshing = false
                false
            }).sendEmptyMessageDelayed(0, 2000)
        }
        rvMoment.layoutManager = LinearLayoutManager(context)
        rvMoment.addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
        rvMoment.adapter = adapter
    }

    private fun initAdapter() {
        adapter = object : BGARecyclerViewAdapter<MomentModel>(rvMoment, R.layout.item_moment) {
            override fun fillData(helper: BGAViewHolderHelper, position: Int, model: MomentModel) {
                helper.setText(R.id.tvName, model.name)
                helper.setText(R.id.tvContent, model.content)
                val photoLayout = helper.getView<BGANinePhotoLayout>(R.id.photoLayout)
                photoLayout.data = model.photos
                photoLayout.setDelegate(PhotoLayoutListener())
            }
        }.apply {
            data = momentList
        }
    }

    inner class PhotoLayoutListener : BGANinePhotoLayout.Delegate {
        override fun onClickNinePhotoItem(ninePhotoLayout: BGANinePhotoLayout, view: View?, position: Int, model: String?, models: MutableList<String>?) {
            curPhotoLayout = ninePhotoLayout
            previewPhotoWithPermissionCheck()
        }

    }

    override fun initOnClick() {
        super.initOnClick()
        btnAdd.setOnClickListener {
            startActivityForResult(Intent(context, MomentAddActivity::class.java), 100)
        }
    }


    @NeedsPermission(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    fun previewPhoto() {
        val builder: BGAPhotoPreviewActivity.IntentBuilder = BGAPhotoPreviewActivity.IntentBuilder(context)
        when (curPhotoLayout.itemCount == 1) {
            true -> builder.previewPhoto(curPhotoLayout.currentClickItem)
            false ->builder.previewPhotos(curPhotoLayout.data).currentPosition(curPhotoLayout.currentClickItemPosition)
        }
        startActivity(builder.build())
    }

    @SuppressLint("NeedOnRequestPermissionsResult")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        onRequestPermissionsResult(requestCode, grantResults)
    }

    @OnPermissionDenied(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    fun denyPreview() {
        toast("权限被拒绝")
    }
}
