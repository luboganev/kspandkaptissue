package dev.luboganev.kspnumberofloadedfilesissue.remotetime

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.Clock
import java.time.Instant
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LondonTimeProvider @Inject constructor(
    private val clock: Clock,
    private val remoteTimeApiService: RemoteTimeApiService,
) {
    suspend fun getLondonTime(): String = withContext(Dispatchers.IO) {
        val londonNow = remoteTimeApiService.getLondonTime().datetime
        val todayLocalDate = LocalDateTime.ofInstant(londonNow, clock.zone).toLocalDate()
        val dateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)
            .withZone(clock.zone)
        val formattedDate = dateFormatter.format(todayLocalDate)
        "In London local time, today is $formattedDate"
    }
}
