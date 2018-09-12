package com.lindroid.thirdpartylibrariesstudykt.model

/**
 * @author Lin
 * @date 2018/9/12
 * @function 信息录入页面的实体类
 * @Description
 */
data class FormModel(
        var itemId:Int = 0,
        var title:String = "",
        var value:String = "",
        var options:ArrayList<String> = ArrayList(),
        var type:Int = 0
){
    companion object {
        const val TYPE_SINGLE_INPUT = 0
        const val TYPE_MULTIPLE_INPUT = 1
        const val TYPE_SINGLE_CHOICE = 2
        const val TYPE_MULTIPLE_CHOICE = 3
    }
}