package com.xunfos.boxorganizationbe.dto

import java.util.UUID

data class ObjectDTO(
    val id: UUID? = null,
    val name: String,
    val tags: List<TagDTO>,
)
