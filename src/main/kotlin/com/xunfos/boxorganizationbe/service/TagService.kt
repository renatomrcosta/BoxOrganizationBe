package com.xunfos.boxorganizationbe.service

import com.xunfos.boxorganizationbe.dto.TagDTO
import com.xunfos.boxorganizationbe.entity.Tag
import com.xunfos.boxorganizationbe.repository.TagRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class TagService(
    private val tagRepository: TagRepository,
) {
    suspend fun findAllByItemId(itemId: UUID): Flow<TagDTO> =
        tagRepository.findAllByItemId(itemId)
            .map { it.toDTO() }

    fun Tag.toDTO(): TagDTO = TagDTO(
        id = this.id, value = this.tag
    )
}

