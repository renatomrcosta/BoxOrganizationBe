package com.xunfos.boxorganizationbe.repository

import com.xunfos.boxorganizationbe.entity.ItemTag
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import java.util.UUID

interface ItemTagRepository : ReactiveCrudRepository<ItemTag, UUID> {
    suspend fun findAllByItemId(itemId: UUID): Flow<ItemTag>
}
