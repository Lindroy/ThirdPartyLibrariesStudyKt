package com.lindroid.thirdpartylibrariesstudykt.materialcalendarview.decorator

import android.graphics.Color
import android.text.style.ForegroundColorSpan
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import java.util.*

/**
 * @author Lin
 * @date 2018/8/31
 * @function 为周末文字设置颜色的装饰类
 * @Description
 */
class WeekendDecorator:DayViewDecorator{
    private val calendar = Calendar.getInstance(Locale.getDefault())
    private val color = Color.parseColor("#ff0000")

    override fun shouldDecorate(day: CalendarDay?): Boolean {
        day?.copyTo(calendar)
        val weekDay= calendar.get(Calendar.DAY_OF_WEEK)
        return weekDay == Calendar.SATURDAY || weekDay == Calendar.SUNDAY
    }

    override fun decorate(view: DayViewFacade) {
        view.addSpan(ForegroundColorSpan(color))
    }
}