package com.xunfos.boxorganizationbe.service

import com.xunfos.boxorganizationbe.dto.TagDTO
import com.xunfos.boxorganizationbe.entity.Tag
import com.xunfos.boxorganizationbe.repository.TagRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Transactional
@Service
class TagService(
    private val tagRepository: TagRepository,
) {
    suspend fun findAllByItemId(itemId: UUID): Flow<TagDTO> =
        tagRepository.findAllByItemId(itemId)
            .map { it.toDTO() }

    suspend fun findItemIds(query: String): Flow<UUID> =
        tagRepository.find(query).map { it.itemId }

    fun Tag.toDTO(): TagDTO = TagDTO(
        id = this.id, value = this.tag
    )

    private suspend fun addTag(dto: TagDTO, objectId: UUID): TagDTO =
        tagRepository.save(
            Tag(
                id = UUID.randomUUID(),
                itemId = objectId,
                tag = dto.value
            ).apply { isNew = true }
        ).awaitSingle().toDTO()

    private suspend fun updateTag(dto: TagDTO, objectId: UUID): TagDTO =
        tagRepository.save(
            Tag(
                id = dto.id ?: error("invalid id for dto $dto"),
                itemId = objectId,
                tag = dto.value
            )
        ).awaitSingle().toDTO()

    suspend fun saveTag(dto: TagDTO, objectId: UUID): TagDTO =
        if (dto.id == null) addTag(dto, objectId)
        else updateTag(dto, objectId)
}

