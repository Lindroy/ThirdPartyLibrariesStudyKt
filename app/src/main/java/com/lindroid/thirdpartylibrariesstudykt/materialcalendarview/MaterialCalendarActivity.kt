package com.lindroid.thirdpartylibrariesstudykt.materialcalendarview

import android.util.Log
import com.lindroid.thirdpartylibrariesstudykt.R
import com.lindroid.thirdpartylibrariesstudykt.base.BaseActivity
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.format.TitleFormatter
import kotlinx.android.synthetic.main.activity_material_calendar.*
import java.text.SimpleDateFormat
import java.util.*

class MaterialCalendarActivity : BaseActivity() {
    private val TAG = "CalendarTag"
    override fun getContentViewId(): Int = R.layout.activity_material_calendar

    override fun initView() {
        super.initView()
        initToolBar(getString(R.string.material_calendarview))
        calendarView.setOnDateChangedListener { materialCalendarView, calendarDay, b ->
            Log.d(TAG, "calendarDay=$calendarDay")
        }
        //设置选择模式：单选、多选和范围选择
        calendarView.selectionMode = MaterialCalendarView.SELECTION_MODE_SINGLE
        calendarView.setTitleFormatter(TitleFormat())
    }

    class TitleFormat : TitleFormatter {
        override fun format(calendarDay: CalendarDay?): CharSequence {
            val dateFormat = SimpleDateFormat("yyyy年MM月", Locale.getDefault())
            return dateFormat.format(calendarDay?.date)
        }
    }
}
