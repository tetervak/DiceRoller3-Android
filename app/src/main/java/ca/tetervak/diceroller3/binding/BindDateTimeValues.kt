package ca.tetervak.diceroller3.binding

import android.widget.TextView
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

private val dateTimeFormatter =
    DateTimeFormatter.ofPattern("EEE, MMM dd, yyyy - h:mm:ss a")

fun formatDateTime(date: Date?): String? {
    return date?.toInstant()
        ?.atZone(ZoneId.systemDefault())
        ?.toLocalDateTime()
        ?.format(dateTimeFormatter)
}

fun bindDateTime(textView: TextView, date: Date?) {
    if (date is Date)
        textView.text = formatDateTime(date)
}