package com.kizitonwose.calendar.core

import androidx.compose.runtime.Immutable
import java.io.Serializable
import java.time.LocalDate
import java.time.LocalDateTime

/**
 * Represents a day on the week calendar.
 *
 * @param date the date for this day.
 * @param position the [WeekDayPosition] for this day.
 * @param usedDate This field [UsedDate] represents the day of the period on the calendar
 */
@Immutable
data class WeekDay(val date: LocalDate,
                   val position: WeekDayPosition,
                   var usedDate: UsedDate = UsedDate()) : Serializable
