package com.xunfos.boxorganizationbe.dto

import java.util.UUID

data class ItemDTO(
    val id: UUID,
    val name: String,
    val tags: List<TagDTO>,
)
