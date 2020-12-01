package com.xunfos.boxorganizationbe.repository

import com.xunfos.boxorganizationbe.entity.Box
import kotlinx.coroutines.flow.Flow
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface BoxRepository : ReactiveCrudRepository<Box, UUID> {
    @Query("SELECT * from box WHERE owner_id = :userId")
    suspend fun findAllByUserId(userId: UUID): Flow<Box>
}
