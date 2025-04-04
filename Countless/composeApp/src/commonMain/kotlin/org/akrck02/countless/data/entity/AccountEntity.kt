package org.akrck02.countless.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.akrck02.countless.data.extension.assertNotNull
import org.akrck02.countless.data.extension.assertNullOrPositive
import org.akrck02.countless.data.model.Account
import java.util.UUID

@Entity(tableName = "account")
data class AccountEntity(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "sync_service_external_uuid") var syncServiceExternalUuid: UUID? = null, // UUID for external sync service
)

fun AccountEntity.toModel() = Account(
    id = id,
    syncServiceExternalUuid = syncServiceExternalUuid,
    name = name
)

fun Account.toEntity(): AccountEntity {

    id.assertNullOrPositive("id")
    name.assertNotNull("name")

    return AccountEntity(
        id = id ?: 0,
        syncServiceExternalUuid = syncServiceExternalUuid,
        name = name!!
    )
}
