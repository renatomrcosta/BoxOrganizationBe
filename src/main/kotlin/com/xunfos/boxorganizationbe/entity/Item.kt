package com.xunfos.boxorganizationbe.entity

import java.util.UUID

data class Item(
    val id: UUID,
    val name: String,
    val tags: List<String>
) {
    // val box: Box? by lazy { null }


}
