package com.xunfos.boxorganizationbe.service

import com.xunfos.boxorganizationbe.dto.ItemDTO
import com.xunfos.boxorganizationbe.entity.Object
import com.xunfos.boxorganizationbe.repository.ObjectRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ObjectService(
    private val tagService: TagService,
    private val objectRepository: ObjectRepository,
) {
    suspend fun findAllByBoxId(boxId: UUID): Flow<ItemDTO> {
        return objectRepository.findAllByBoxId(boxId)
            .map { it.toDTO() }
    }

    suspend fun Object.toDTO(): ItemDTO {
        val tags = tagService.findAllByItemId(this.id)
        return ItemDTO(
            id = this.id,
            name = this.name,
            tags = tags.toList()
        )
    }
}
