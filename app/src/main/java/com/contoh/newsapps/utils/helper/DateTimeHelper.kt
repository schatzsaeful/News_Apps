package com.contoh.newsapps.utils.helper

import com.contoh.newsapps.utils.ext.callOrNull
import com.contoh.newsapps.utils.ext.orZero
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateTimeHelper {
    const val API_DATE_FORMAT = "yyyy-MM-dd"
    const val API_TZ_FORMAT_SIMPLER = "yyyy-MM-dd'T'HH:mm:ss'Z'"
    const val DISPLAY_SIMPLE_DATE_FORMAT = "dd MMMM yyyy"
    const val DISPLAY_FULL_DATE_FORMAT = "dd MMM yyyy, HH:mm"
    const val DAY_ONLY = "dd"
    const val MONT_ONLY = "MM"
    const val MONTH_YEAR_ONLY = "MMM/yy"

    private val locale by lazy {
        Locale("id", "ID")
    }

    fun getTime(
        dateTime: String,
        fromFormat: String = API_DATE_FORMAT
    ): Long? = callOrNull {
        SimpleDateFormat(fromFormat, locale).parse(dateTime)?.time
    }

    fun translateTime(
        time: Long?,
        expectedFormat: String = DISPLAY_SIMPLE_DATE_FORMAT
    ): String? = callOrNull {
        SimpleDateFormat(expectedFormat, locale).format(Date(time.orZero()))
    }

    fun convertTimeStr(
        time: String,
        fromFormat: String,
        expectedFormat: String
    ): String? = callOrNull {
        translateTime(getTime(time, fromFormat), expectedFormat)
    }
}

