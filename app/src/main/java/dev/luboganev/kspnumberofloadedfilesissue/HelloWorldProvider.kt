package dev.luboganev.kspnumberofloadedfilesissue

import java.time.Clock
import java.time.Instant
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HelloWorldProvider @Inject constructor(
    private val clock: Clock,
) {
    fun getMessage(): String {
        val now = Instant.now(clock)
        val todayLocalDate = LocalDateTime.ofInstant(now, clock.zone).toLocalDate()
        val dateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)
            .withZone(clock.zone)
        val formattedDate = dateFormatter.format(todayLocalDate)
        return "Hello world! Today is $formattedDate"
    }
}
