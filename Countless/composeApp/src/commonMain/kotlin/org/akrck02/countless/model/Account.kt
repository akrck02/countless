package org.akrck02.countless.model

import java.util.UUID

data class Account(
    var syncServiceExternalUuid: UUID? = null, // UUID for external sync service
    var id: Int? = null
)