package com.xunfos.boxorganizationbe.repository

import com.xunfos.boxorganizationbe.entity.Image
import kotlinx.coroutines.flow.Flow
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import java.util.UUID

interface ImageRepository : ReactiveCrudRepository<Image, UUID> {
    @Query(IMAGE_BY_ITEM_ID_QUERY)
    suspend fun findAllByItemId(itemId: UUID): Flow<Image>

    companion object {
        private const val IMAGE_BY_ITEM_ID_QUERY = """
            SELECT
                img.id, img.url
            FROM
                image img
            INNER JOIN item_image iimg
                ON img.id = iimg.image_id
            WHERE iimg.item_id = :itemId
        """
    }
}
