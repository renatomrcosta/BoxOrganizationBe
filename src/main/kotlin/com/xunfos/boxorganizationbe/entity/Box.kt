package com.xunfos.boxorganizationbe.entity

import java.util.UUID

data class Box(
    val id: UUID,
    val name: String,
    val qrCode: String?,
    val ownerId: UUID
)
