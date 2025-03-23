package org.akrck02.countless.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.akrck02.countless.data.model.data.Account
import java.util.UUID

@Entity(tableName = "account")
data class AccountEntity(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    @ColumnInfo(name = "name") var name: String? = null,
    @ColumnInfo(name = "sync_service_external_uuid") var syncServiceExternalUuid: UUID? = null, // UUID for external sync service
)

fun AccountEntity.toModel() = Account(
    id = id,
    syncServiceExternalUuid = syncServiceExternalUuid
)

fun Account.toEntity() = AccountEntity(
    id = id,
    syncServiceExternalUuid = syncServiceExternalUuid
)
