package com.kizitonwose.calendar.core

/**
 * For example, the period 2023-05-15 13:20:33 - 2023-05-17 17:43:12

 * 2023-05-15 13:20:33 is BEGIN, which means it is the beginning of some period.
 * This date does not belong to any period and is free before 13:20:33
 * and it is busy after 13:20:33

 * 2023-05-16 is DURING and busy all the day

 * 2023-05-17 17:43:12 is END of this period.
 * Which means it is busy before 17:43:12 and
 * free and does not belong to any period after 17:43:12 of 2023-05-17

 * 2023-05-17 is UNDEFINED and does not belong to any period
 *
 */
enum class UsedDateType {


    /**
     * This date type represents the beginning of the period,
     * which means that the given day before the specified time is free
     * and given day after the specified time is busy
     */
    BEGIN,

    /**
     * The end of the period,
     * The given day before the specified time is busy
     * and given day after the specified time is free
     */
    END,

    /**
     * Day during the period. Busy all the time
     */
    DURING,

    /**
     * A day does not belong to any period.
     * Free during the whole time of the day, default value for any day
     */
    UNDEFINED
}
