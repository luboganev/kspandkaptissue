package dev.luboganev.kspnumberofloadedfilesissue.remotetime

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

/**
 * This serializer should be used to serialize and deserialize dates
 * using the ISO-8601 date-time format.
 *
 * This class uses [DateTimeFormatter.ISO_OFFSET_DATE_TIME]
 *
 * The format consist of:
 * - The ISO_LOCAL_DATE_TIME
 * - The offset ID.
 *
 * The ID is a minor variation to the standard ISO-8601 formatted string for the offset.
 * There are three formats:
 * 1) Z - for UTC (ISO-8601)
 * 2) +hh:mm or -hh:mm - if the seconds are zero (ISO-8601)
 * 3) +hh:mm:ss or -hh:mm:ss - if the seconds are non-zero (not ISO-8601)
 */
object Iso8601InstantSerializer : KSerializer<Instant> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor(
        serialName = "java.time.Instant",
        kind = PrimitiveKind.STRING
    )

    /**
     * It serializes an Instant using [DateTimeFormatter.ISO_OFFSET_DATE_TIME] into a string
     * with [ZoneOffset.UTC].
     *
     * NOTICE that the resulting string, as per [ZoneOffset.getId] specifications,
     * will always have the Z at the end (e.g. "2023-08-28T23:10:20Z")
     * and not an offset of +00:00 (e.g. "2023-08-29T02:10:20+03:00").
     */
    override fun serialize(encoder: Encoder, value: Instant) {
        val offsetDateTime = value.atOffset(ZoneOffset.UTC)
        val formatted = DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(offsetDateTime)

        return encoder.encodeString(formatted)
    }

    /**
     * It deserializes using [DateTimeFormatter.ISO_OFFSET_DATE_TIME]
     * Therefore it can parse strings like "2023-08-29T02:10:20+03:00" or "2023-08-28T23:10:20Z"
     */
    override fun deserialize(decoder: Decoder): Instant {
        val creationAccessor = DateTimeFormatter.ISO_OFFSET_DATE_TIME.parse(decoder.decodeString())
        return Instant.from(creationAccessor)
    }
}
