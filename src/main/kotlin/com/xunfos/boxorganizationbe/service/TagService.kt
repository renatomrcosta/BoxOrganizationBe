package com.xunfos.boxorganizationbe.service

import com.xunfos.boxorganizationbe.dto.TagDTO
import com.xunfos.boxorganizationbe.entity.ItemTag
import com.xunfos.boxorganizationbe.repository.ItemTagRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class TagService(
    private val tagRepository: ItemTagRepository,
) {
    suspend fun findAllByItemId(itemId: UUID): Flow<TagDTO> =
        tagRepository.findAllByItemId(itemId)
            .map { it.toDTO() }

    fun ItemTag.toDTO(): TagDTO = TagDTO(
        itemId = this.itemId, value = this.tag
    )
}

