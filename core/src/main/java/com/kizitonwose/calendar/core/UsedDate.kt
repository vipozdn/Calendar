package com.kizitonwose.calendar.core

import java.time.LocalDateTime

/**
 * Represents the day of the period on the calendar
 *
 * @param usedDateType [UsedDateType] Type of day belonging to the period
 * @param usedDateTime Time of the day belonging to the period
 */
data class UsedDate(var usedDateType: UsedDateType = UsedDateType.UNDEFINED,
                    var usedDateTime: LocalDateTime = LocalDateTime.now())
