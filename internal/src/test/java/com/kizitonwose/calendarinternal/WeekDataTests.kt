package com.kizitonwose.calendarinternal

import com.kizitonwose.calendarcore.WeekDayPosition
import com.kizitonwose.calendarcore.daysOfWeek
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.Month
import java.time.YearMonth

class WeekDataTests {

    private val may2019 = YearMonth.of(2019, 5)
    private val november2019 = YearMonth.of(2019, 11)
    private val firstDayOfWeek = DayOfWeek.MONDAY

    /** May and November 2019 with Monday as the first day of week.
    ┌────────────────────┐ ┌────────────────────┐
    │      May 2019      │ │   November 2019    │
    ├──┬──┬──┬──┬──┬──┬──┤ ├──┬──┬──┬──┬──┬──┬──┤
    │Mo│Tu│We│Th│Fr│Sa│Su│ │Mo│Tu│We│Th│Fr│Sa│Su│
    ├──┼──┼──┼──┼──┼──┼──┤ ├──┼──┼──┼──┼──┼──┼──┤
    │29│30│01│02│03│04│05│ │28│29│30│31│01│02│03│
    ├──┼──┼──┼──┼──┼──┼──┤ ├──┼──┼──┼──┼──┼──┼──┤
    │06│07│08│09│10│11│12│ │04│05│06│07│08│09│10│
    ├──┼──┼──┼──┼──┼──┼──┤ ├──┼──┼──┼──┼──┼──┼──┤
    │13│14│15│16│17│18│19│ │11│12│13│14│15│16│17│
    ├──┼──┼──┼──┼──┼──┼──┤ ├──┼──┼──┼──┼──┼──┼──┤
    │20│21│22│23│24│25│26│ │18│19│20│21│22│23│24│
    ├──┼──┼──┼──┼──┼──┼──┤ ├──┼──┼──┼──┼──┼──┼──┤
    │27│28│29│30│31│01│02│ │25│26│27│28│29│30│01│
    └──┴──┴──┴──┴──┴──┴──┘ └──┴──┴──┴──┴──┴──┴──┘
     **/

    @Test
    fun `date range adjustment works as expected`() {
        val may01 = may2019.atDay(1)
        val nov01 = november2019.atDay(1)
        val adjustedWeekRange = getWeekCalendarAdjustedRange(may01, nov01, firstDayOfWeek)

        assertEquals(LocalDate.of(2019, Month.APRIL, 29), adjustedWeekRange.startDateAdjusted)
        assertEquals(LocalDate.of(2019, Month.NOVEMBER, 3), adjustedWeekRange.endDateAdjusted)
    }

    @Test
    fun `week data generation works as expected`() {
        val may01 = may2019.atDay(1)
        val nov01 = november2019.atDay(1)
        val adjustedWeekRange = getWeekCalendarAdjustedRange(may01, nov01, firstDayOfWeek)
        val weekData = getWeekCalendarData(adjustedWeekRange.startDateAdjusted, 0, may01, nov01)

        assertEquals(LocalDate.of(2019, Month.APRIL, 29), weekData.days.first().date)
        assertEquals(LocalDate.of(2019, Month.MAY, 5), weekData.days.last().date)
    }

    @Test
    fun `week in date generation works as expected`() {
        val may01 = may2019.atDay(1)
        val nov01 = november2019.atDay(1)
        val adjustedWeekRange = getWeekCalendarAdjustedRange(may01, nov01, firstDayOfWeek)
        val weekData = getWeekCalendarData(adjustedWeekRange.startDateAdjusted, 0, may01, nov01)

        val inDates = weekData.days.take(2)
        val rangeDays = weekData.days.takeLast(5)
        assertTrue(inDates.all { it.position == WeekDayPosition.InDate })
        assertTrue(rangeDays.all { it.position == WeekDayPosition.RangeDate })
    }

    @Test
    fun `week out date generation works as expected`() {
        val may01 = may2019.atDay(1)
        val may31 = may2019.atDay(31)
        val adjustedWeekRange = getWeekCalendarAdjustedRange(may01, may31, firstDayOfWeek)
        val weekData = getWeekCalendarData(adjustedWeekRange.startDateAdjusted, 4, may01, may31)

        val outDates = weekData.days.takeLast(2)
        val rangeDays = weekData.days.take(5)
        assertTrue(outDates.all { it.position == WeekDayPosition.OutDate })
        assertTrue(rangeDays.all { it.position == WeekDayPosition.RangeDate })
    }

    @Test
    fun `days are in the appropriate week columns`() {
        val may01 = may2019.atDay(2)
        val may31 = may2019.atDay(31)
        val adjustedWeekRange = getWeekCalendarAdjustedRange(may01, may31, firstDayOfWeek)
        val weekData = getWeekCalendarData(adjustedWeekRange.startDateAdjusted, 0, may01, may31)

        val daysOfWeek = daysOfWeek(firstDayOfWeek)
        weekData.days.forEachIndexed { index, day ->
            assertEquals(daysOfWeek[index], day.date.dayOfWeek)
        }
    }

    @Test
    fun `generated week is at the correct offset`() {
        val may01 = may2019.atDay(2)
        val may31 = may2019.atDay(31)
        val adjustedWeekRange = getWeekCalendarAdjustedRange(may01, may31, firstDayOfWeek)
        val weekData = getWeekCalendarData(adjustedWeekRange.startDateAdjusted, 2, may01, may31)

        assertEquals(may2019.atDay(13), weekData.days.first().date)
        assertEquals(may2019.atDay(19), weekData.days.last().date)
    }

    @Test
    fun `week index calculation works as expected`() {
        val may01 = may2019.atDay(2)
        val may31 = may2019.atDay(31)
        val adjustedWeekRange = getWeekCalendarAdjustedRange(may01, may31, firstDayOfWeek)
        val index = getWeekIndex(adjustedWeekRange.startDateAdjusted, may31)

        assertEquals(4, index)
    }

    @Test
    fun `week indices count calculation works as expected`() {
        val may01 = may2019.atDay(2)
        val may31 = may2019.atDay(31)
        val adjustedWeekRange = getWeekCalendarAdjustedRange(may01, may31, firstDayOfWeek)
        val count = getWeekIndicesCount(adjustedWeekRange.startDateAdjusted, may31)

        assertEquals(5, count)
    }
}
