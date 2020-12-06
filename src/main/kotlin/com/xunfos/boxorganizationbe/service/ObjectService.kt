package com.xunfos.boxorganizationbe.service

import com.xunfos.boxorganizationbe.dto.ObjectDTO
import com.xunfos.boxorganizationbe.repository.ObjectRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.awaitSingle
import kotlinx.coroutines.reactive.awaitSingleOrNull
import org.springframework.stereotype.Service
import java.util.UUID
import com.xunfos.boxorganizationbe.entity.Object as ObjectEntity

@Service
class ObjectService(
    private val objectRepository: ObjectRepository,
    private val tagService: TagService,
) {
    suspend fun findAllByBoxId(boxId: UUID): Flow<ObjectDTO> {
        return objectRepository.findAllByBoxId(boxId)
            .map { it.toDTO() }
    }

    suspend fun addObject(
        objectDTO: ObjectDTO,
        userId: UUID,
        containerId: UUID? = null,
    ) =
        objectRepository.save(
            ObjectEntity(
                id = UUID.randomUUID(),
                userId = userId,
                name = objectDTO.name,
                containerItemId = containerId
            ).apply { isNew = true }
        ).awaitSingle().toDTO()

    suspend fun updateObjectEntity(
        objectDTO: ObjectDTO,
        userId: UUID,
        containerId: UUID? = null,
    ): ObjectDTO {
        val id = objectDTO.id ?: error("invalid object ID to update")
        val obj = objectRepository.findById(id).awaitSingle()
        return objectRepository.save(
            ObjectEntity(
                id = obj.id,
                userId = userId,
                name = objectDTO.name,
                containerItemId = containerId,
            ).apply { isNew = true }
        ).awaitSingle().toDTO()
    }

    suspend fun removeObject(objectDTO: ObjectDTO) {
        val id = objectDTO.id ?: error("invalid object ID to update")
        val obj = objectRepository.findById(id).awaitSingle()
        objectRepository.delete(obj).awaitSingleOrNull()
    }

    suspend fun ObjectEntity.toDTO(): ObjectDTO {
        val tags = tagService.findAllByItemId(this.id)
        return ObjectDTO(
            id = this.id,
            name = this.name,
            tags = tags.toList()
        )
    }
}
