package com.kizitonwose.calendar.core

import androidx.compose.runtime.Immutable
import java.io.Serializable
import java.time.LocalDate
import java.time.LocalDateTime

/**
 * Represents a day on the calendar.
 *
 * @param date the date for this day.
 * @param position the [DayPosition] for this day.
 * @param usedDate This field [UsedDate] represents the day of the period on the calendar
 */
@Immutable
data class CalendarDay(val date: LocalDate,
                       val position: DayPosition,
                       var usedDate: UsedDate = UsedDate()) : Serializable
