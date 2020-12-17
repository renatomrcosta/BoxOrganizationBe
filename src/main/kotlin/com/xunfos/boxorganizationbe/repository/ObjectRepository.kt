package com.xunfos.boxorganizationbe.repository

import com.xunfos.boxorganizationbe.entity.Object
import kotlinx.coroutines.flow.Flow
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import java.util.UUID

interface ObjectRepository : ReactiveCrudRepository<Object, UUID> {
    @Query("SELECT * FROM item where container_item_id = :boxId AND type = 'Object' ")
    suspend fun findAllByBoxId(boxId: UUID): Flow<Object>

    @Query("SELECT * FROM item where user_id = :userId AND type = 'Object'")
    suspend fun findAllByUserId(userId: UUID): Flow<Object>

    @Query("SELECT * FROM item where user_id = :userId AND id = :id AND type = 'Object'")
    suspend fun findByIdAndUserId(id: UUID, userId: UUID): Object
}
