package com.lindroid.thirdpartylibrariesstudykt.bgabaseadapter.adapter

import android.support.v7.widget.RecyclerView
import cn.bingoogolapple.baseadapter.BGARecyclerViewAdapter
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper
import com.lindroid.thirdpartylibrariesstudykt.R
import com.lindroid.thirdpartylibrariesstudykt.model.FormModel

/**
 * @author Lin
 * @date 2018/9/12
 * @function
 * @Description
 */
class FormAdapter(rv: RecyclerView, formList: MutableList<FormModel>) : BGARecyclerViewAdapter<FormModel>(rv) {

    init {
        data = formList
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position).type) {
            FormModel.TYPE_SINGLE_INPUT -> R.layout.item_form_single_input
            FormModel.TYPE_MULTIPLE_INPUT -> R.layout.item_form_mult_input
            FormModel.TYPE_SINGLE_CHOICE -> R.layout.item_form_single_choice
            FormModel.TYPE_MULTIPLE_CHOICE -> R.layout.item_form_single_input
            else -> {
                R.layout.item_form_single_input
            }
        }
    }


    /**
     * 填充item数据
     *
     * @param helper
     * @param position
     * @param model
     */
    override fun fillData(helper: BGAViewHolderHelper, position: Int, model: FormModel) {
        helper.setText(R.id.tvTitle, model.title)
        /*when (model.type) {
            FormModel.TYPE_SINGLE_INPUT -> {
                val etSingle = helper.getView<EditText>(R.id.etSingle)
            }
            FormModel.TYPE_MULTIPLE_INPUT -> R.layout.item_form_mult_input
            FormModel.TYPE_SINGLE_CHOICE -> R.layout.item_form_single_choice
            FormModel.TYPE_MULTIPLE_CHOICE -> R.layout.item_form_single_input
        }*/
    }

}