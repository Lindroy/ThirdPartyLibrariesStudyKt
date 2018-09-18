package com.lindroid.thirdpartylibrariesstudykt.model

import java.io.Serializable

/**
 * @author Lin
 * @date 2018/9/17
 * @function 朋友圈列表实体类
 * @Description
 */
data class MomentModel (
        val name:String="匿名用户",
        val content:String="默认发送的状态",
        val photos:ArrayList<String> = ArrayList()
):Serializable