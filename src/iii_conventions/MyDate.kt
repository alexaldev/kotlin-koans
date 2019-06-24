package iii_conventions

import com.google.common.collect.Iterators

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int): Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int = when {
        year != other.year -> year - other.year
        month != other.month -> month - other.month
        else -> dayOfMonth - other.dayOfMonth
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = when {
    this > other -> DateRange(other, this)
    else -> DateRange(this, other)
}

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class RepeatedTimeInterval(val timeInterval: TimeInterval, val times: Int)

operator fun TimeInterval.times(times: Int): RepeatedTimeInterval = RepeatedTimeInterval(this, times)

operator fun MyDate.plus(interval: TimeInterval) = this.addTimeIntervals(interval, 1)

operator fun MyDate.plus(repeatedTimeInterval: RepeatedTimeInterval) = this.addTimeIntervals(repeatedTimeInterval.timeInterval, repeatedTimeInterval.times)

class DateRangeIterator(val dateRange: DateRange): Iterator<MyDate> {

    private var currentDate = dateRange.start

    override fun hasNext(): Boolean  = currentDate <= dateRange.endInclusive

    override fun next(): MyDate {

        val current = currentDate
        currentDate = currentDate.nextDay()
        return current
    }
}

class DateRange(val start: MyDate, val endInclusive: MyDate): Iterable<MyDate> {

    operator fun contains(date: MyDate): Boolean = when {
        start > endInclusive -> false
        else -> (date >= start && date <= endInclusive)
    }

    override operator fun iterator(): Iterator<MyDate> {

        if ( start > endInclusive)
            return Iterators.emptyIterator()

        return DateRangeIterator(this)
    }
}
