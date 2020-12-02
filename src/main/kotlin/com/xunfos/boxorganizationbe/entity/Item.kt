package com.xunfos.boxorganizationbe.entity

import java.util.UUID

sealed class Item {
    abstract val id: UUID
    abstract val userId: UUID
    abstract val name: String
    abstract val qrCode: String?
    abstract val itemType: ItemType
}

data class Object(
    override val id: UUID,
    override val userId: UUID,
    override val name: String,
    override val qrCode: String?,
    override val itemType: ItemType = ItemType.Object,
    val containerItemId: UUID,
) : Item()

data class Box(
    override val id: UUID,
    override val userId: UUID,
    override val name: String,
    override val qrCode: String?,
    override val itemType: ItemType = ItemType.Box,
) : Item()
