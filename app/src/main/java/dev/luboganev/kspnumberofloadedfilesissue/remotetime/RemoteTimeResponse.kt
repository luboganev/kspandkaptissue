@file:UseSerializers(Iso8601InstantSerializer::class)

package dev.luboganev.kspnumberofloadedfilesissue.remotetime

import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.time.Instant

@Serializable
data class RemoteTimeResponse(
    val datetime: Instant,
)
