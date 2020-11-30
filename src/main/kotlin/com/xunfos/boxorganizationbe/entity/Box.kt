package com.xunfos.boxorganizationbe.entity

import java.util.UUID

data class Box(
    val id: UUID,
    val name: String,
    val qrCode: String?,
    val ownerId: String
) {
    // val items: List<Item> by lazy { emptyList() }
    // val images: List<Image> by lazy { emptyList() }
}
