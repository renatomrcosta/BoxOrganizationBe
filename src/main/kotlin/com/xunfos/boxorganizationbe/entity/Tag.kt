package com.xunfos.boxorganizationbe.entity

import org.springframework.data.relational.core.mapping.Table
import java.util.UUID

@Table("item_tag")
data class Tag(
    val id: UUID,
    val itemId: UUID,
    val tag: String,
)
