package com.lindroid.thirdpartylibrariesstudykt.materialcalendarview

import android.util.Log
import com.lindroid.thirdpartylibrariesstudykt.R
import com.lindroid.thirdpartylibrariesstudykt.base.BaseActivity
import com.lindroid.thirdpartylibrariesstudykt.materialcalendarview.decorator.WeekendDecorator
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.format.TitleFormatter
import kotlinx.android.synthetic.main.activity_material_calendar.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * GitHub地址：https://github.com/prolificinteractive/material-calendarview
 */
class MaterialCalendarActivity : BaseActivity() {
    private val TAG = "CalendarTag"
    override fun getContentViewId(): Int = R.layout.activity_material_calendar

    override fun initView() {
        super.initView()
        initToolBar(getString(R.string.material_calendarview))
        calendarView.setOnDateChangedListener { materialCalendarView, calendarDay, b ->
            Log.e(TAG,"选择的日期："+"${calendarDay.year}-${calendarDay.month}-${calendarDay.day}")
//            Log.d(TAG, "calendarDay=$calendarDay")
        }
        //设置选择模式：单选、多选和范围选择
        calendarView.selectionMode = MaterialCalendarView.SELECTION_MODE_SINGLE
        calendarView.setTitleFormatter(TitleFormat())

        calendarView.addDecorator(WeekendDecorator())
    }

    class TitleFormat : TitleFormatter {
        override fun format(calendarDay: CalendarDay?): CharSequence {
            val dateFormat = SimpleDateFormat("yyyy年MM月", Locale.getDefault())
            return dateFormat.format(calendarDay?.date)
        }
    }
}
