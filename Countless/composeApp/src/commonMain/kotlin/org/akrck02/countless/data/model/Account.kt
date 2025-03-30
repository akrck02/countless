package org.akrck02.countless.data.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class Account(
    var id: Int? = null,
    var name: String? = null,
    @Contextual var syncServiceExternalUuid: UUID? = null, // UUID for external sync service
)