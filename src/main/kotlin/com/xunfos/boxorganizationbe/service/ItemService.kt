package com.xunfos.boxorganizationbe.service

import com.xunfos.boxorganizationbe.dto.ItemDTO
import com.xunfos.boxorganizationbe.entity.Item
import com.xunfos.boxorganizationbe.repository.ItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ItemService(
    private val tagService: TagService,
    private val itemRepository: ItemRepository,
) {
    suspend fun findAllByBoxId(boxId: UUID): Flow<ItemDTO> {
        return itemRepository.findAllByBoxId(boxId)
            .map { it.toDTO() }
    }

    suspend fun Item.toDTO(): ItemDTO {
        val tags = tagService.findAllByItemId(this.id)
        return ItemDTO(
            id = this.id,
            name = this.name,
            tags = tags.toList()
        )
    }
}
