package com.xunfos.boxorganizationbe.repository

import com.xunfos.boxorganizationbe.entity.Tag
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import java.util.UUID

interface TagRepository : ReactiveCrudRepository<Tag, UUID> {
    suspend fun findAllByItemId(itemId: UUID): Flow<Tag>
}
