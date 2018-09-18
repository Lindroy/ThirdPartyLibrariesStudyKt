package com.lindroid.thirdpartylibrariesstudykt.model

/**
 * @author Lin
 * @date 2018/9/17
 * @function 朋友圈列表实体类
 * @Description
 */
data class MomentModel (
        val content:String="",
        val photos:ArrayList<String> = ArrayList()
)