package com.m7.shifttracker

import android.graphics.Color
import java.text.SimpleDateFormat
import java.util.*

data class Shift(
    var role: String,
    var name: String,
    var startDate: String,
    var endDate: String,
    var color: String
) {

    val colorValue = when (color) {
        "red" -> Color.RED
        "green" -> Color.GREEN
        "blue" -> Color.BLUE
        else -> Color.WHITE
    }

    private val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    private val dateToStringFormatter = SimpleDateFormat("EEE, MMM dd", Locale.getDefault())

    private val timeFormatter = SimpleDateFormat("hh:mm:ss", Locale.getDefault())
    private val timeToStringFormatter = SimpleDateFormat("hh", Locale.getDefault())

    val startDateValue =
        dateToStringFormatter.format(dateFormatter.parse(startDate.split(" ").first()))

    val endDateValue =
        dateToStringFormatter.format(dateFormatter.parse(endDate.split(" ").first()))

    val startAndEndTime = try {
        val startDateStartTimeValue =    timeToStringFormatter.format(timeFormatter.parse(startDate.split(" ")[1]))
        val startDateEndTimeValue = startDate.split(" ").last().removePrefix("-")

        val endDateStartTimeValue =
            timeToStringFormatter.format(timeFormatter.parse(endDate.split(" ")[1]))
        val endDateEndTimeValue = endDate.split(" ").last().removePrefix("-")

        "$startDateStartTimeValue - $endDateStartTimeValue"
    } catch (e: Exception) {
        e.printStackTrace()
        "--"
    }

}
