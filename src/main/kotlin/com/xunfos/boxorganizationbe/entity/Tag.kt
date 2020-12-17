package com.xunfos.boxorganizationbe.entity

import org.springframework.data.annotation.Id
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.annotation.Transient
import java.util.UUID

@Table("item_tag")
data class Tag(
    @JvmField @Id val id: UUID,
    val itemId: UUID,
    val tag: String,
) : Persistable<UUID> {

    @JvmField
    @Transient
    var isNew: Boolean = false

    override fun isNew() = isNew
    override fun getId(): UUID = id
}
