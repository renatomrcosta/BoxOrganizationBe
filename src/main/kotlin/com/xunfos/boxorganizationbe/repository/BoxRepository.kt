package com.xunfos.boxorganizationbe.repository

import com.xunfos.boxorganizationbe.entity.Box
import kotlinx.coroutines.flow.Flow
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface BoxRepository : ReactiveCrudRepository<Box, UUID> {
    @Query("SELECT * from item WHERE user_id = :userId AND type = 'Box'")
    suspend fun findAllByUserId(userId: UUID): Flow<Box>

    @Query("SELECT * from item WHERE user_id = :userId AND id = :id AND type = 'Box'")
    suspend fun getByUserIdAndId(userId: UUID, id: UUID): Box
}
