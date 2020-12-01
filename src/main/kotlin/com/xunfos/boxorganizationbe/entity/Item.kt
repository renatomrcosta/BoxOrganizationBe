package com.xunfos.boxorganizationbe.entity

import java.util.UUID

data class Item(
    val id: UUID,
    val ownerId: UUID,
    val boxId: UUID?,
    val name: String,
)
