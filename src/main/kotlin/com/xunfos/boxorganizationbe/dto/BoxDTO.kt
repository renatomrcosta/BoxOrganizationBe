package com.xunfos.boxorganizationbe.dto

import java.util.UUID

data class BoxDTO(
    val id: UUID,
    val name: String,
    val qrCode: String?,
    val items: List<ItemDTO>,
)
