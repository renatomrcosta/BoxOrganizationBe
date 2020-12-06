package com.xunfos.boxorganizationbe.dto

import java.util.UUID

data class BoxDTO(
    val id: UUID? = null,
    val name: String,
    val qrCode: String?,
    val objects: List<ObjectDTO> = emptyList(),
)
