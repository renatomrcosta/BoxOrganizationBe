package com.xunfos.boxorganizationbe.repository

import com.xunfos.boxorganizationbe.entity.Item
import kotlinx.coroutines.flow.Flow
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import java.util.UUID

interface ItemRepository : ReactiveCrudRepository<Item, UUID> {
    @Query("SELECT * FROM item where box_id = :boxId ")
    suspend fun findAllByBoxId(boxId: UUID): Flow<Item>

    @Query("SELECT * FROM item where owner_id = :userId")
    suspend fun findAllByUserId(userId: UUID): Flow<Item>
}
