package iii_conventions

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

class DateRangeIterator(val startDate: MyDate, val endDate: MyDate): Iterator<MyDate> {

    private var currentDate = startDate

    override fun hasNext(): Boolean {

        if ( startDate > endDate)
            return false

        return currentDate <= endDate
    }

    override fun next(): MyDate {

        val current = currentDate
        currentDate = currentDate.nextDay()
        return current
    }
}

class DateRange(val start: MyDate, val endInclusive: MyDate) {

    operator fun contains(date: MyDate): Boolean = when {
        start > endInclusive -> false
        else -> (date >= start && date <= endInclusive)
    }

    operator fun iterator(): Iterator<MyDate> {
        return DateRangeIterator(start, endInclusive)
    }
}
