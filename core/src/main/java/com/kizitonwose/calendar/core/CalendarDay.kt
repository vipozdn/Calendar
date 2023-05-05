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
 * @param usedDateTime This field represents the time of the specified date,
 * depending on which the behavior and appearance of the calendar day can change
 */
@Immutable
data class CalendarDay(val date: LocalDate,
                       val position: DayPosition,
                       val usedDateTime: LocalDateTime) : Serializable
