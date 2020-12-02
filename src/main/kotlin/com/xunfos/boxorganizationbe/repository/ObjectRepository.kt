package com.xunfos.boxorganizationbe.repository

import kotlinx.coroutines.flow.Flow
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import java.util.UUID
import com.xunfos.boxorganizationbe.entity.Object

interface ObjectRepository : ReactiveCrudRepository<Object, UUID> {
    @Query("SELECT * FROM item where container_item_id = :boxId AND type = 'Object' ")
    suspend fun findAllByBoxId(boxId: UUID): Flow<Object>

    @Query("SELECT * FROM item where user_id = :userId AND type = 'Object'")
    suspend fun findAllByUserId(userId: UUID): Flow<Object>
}
