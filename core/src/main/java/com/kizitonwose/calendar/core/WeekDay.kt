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
 * @param usedDateTime This field represents the time of the specified date,
 * depending on which the behavior and appearance of the calendar day can change
 */
@Immutable
data class WeekDay(val date: LocalDate,
                   val position: WeekDayPosition,
                   val usedDateTime: LocalDateTime) : Serializable
