package org.akrck02.countless.data.model.data

import java.util.UUID

data class Account(
    var id: Int? = null,
    var syncServiceExternalUuid: UUID? = null, // UUID for external sync service
)