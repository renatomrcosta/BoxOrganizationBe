package com.xunfos.boxorganizationbe.entity

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Table
import java.util.UUID

sealed class Item {
    abstract val id: UUID
    abstract val userId: UUID
    abstract val name: String
    abstract val qrCode: String?
    abstract val type: ItemType
}

@Table("item")
data class Object(
    @JvmField @Id override val id: UUID,
    override val userId: UUID,
    override val name: String,
    override val qrCode: String?,
    override val type: ItemType = ItemType.Object,
    val containerItemId: UUID,
) : Item(), Persistable<UUID> {
    @JvmField
    @Transient
    var isNew: Boolean = false

    override fun getId(): UUID = id
    override fun isNew() = isNew
}

@Table("item")
data class Box(
    @JvmField @Id override val id: UUID,
    override val userId: UUID,
    override val name: String,
    override val qrCode: String?,
    override val type: ItemType = ItemType.Box,
) : Item(), Persistable<UUID> {
    @JvmField
    @Transient
    var isNew: Boolean = false

    override fun getId(): UUID = id
    override fun isNew() = isNew
}
