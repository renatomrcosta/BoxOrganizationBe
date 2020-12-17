package com.xunfos.boxorganizationbe.service

import com.xunfos.boxorganizationbe.dto.ObjectDTO
import com.xunfos.boxorganizationbe.repository.ObjectRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.reactive.awaitSingle
import kotlinx.coroutines.reactive.awaitSingleOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID
import com.xunfos.boxorganizationbe.entity.Object as ObjectEntity

@Transactional
@Service
class ObjectService(
    private val objectRepository: ObjectRepository,
    private val tagService: TagService,
) {
    suspend fun findAllByBoxId(boxId: UUID): Flow<ObjectDTO> {
        return objectRepository.findAllByBoxId(boxId)
            .map { it.toDTO() }
    }

    suspend fun getAllObjectsByUserId(userId: UUID): Flow<ObjectDTO> =
        objectRepository.findAllByUserId(userId).map { it.toDTO() }

    suspend fun getById(userId: UUID, id: UUID): ObjectDTO =
        objectRepository.findByIdAndUserId(userId = userId, id = id).toDTO()

    @ExperimentalCoroutinesApi
    suspend fun find(userId: UUID, query: String): Flow<ObjectDTO> = coroutineScope {
        val names = async { objectRepository.find(userId, query).map { it.toDTO() } }
        val tags = async { tagService.findItemIds(query).map { getById(userId, it) } }

        merge(names.await(), tags.await())
    }

    suspend fun removeObject(objectDTO: ObjectDTO) {
        val id = objectDTO.id ?: error("invalid object ID to update")
        val obj = objectRepository.findById(id).awaitSingle()
        objectRepository.delete(obj).awaitSingleOrNull()
    }

    suspend fun saveObject(objectDTO: ObjectDTO, userId: UUID, containerId: UUID?) =
        if (objectDTO.id == null) addObject(objectDTO, userId, containerId)
        else updateObject(objectDTO, userId, containerId)

    private suspend fun addObject(
        objectDTO: ObjectDTO,
        userId: UUID,
        containerId: UUID? = null,
    ): ObjectDTO {
        val obj = objectRepository.save(
            ObjectEntity(
                id = UUID.randomUUID(),
                userId = userId,
                name = objectDTO.name,
                containerItemId = containerId
            ).apply { isNew = true }
        ).awaitSingle()

        handleItems(obj, objectDTO)

        return obj.toDTO()
    }

    private suspend fun handleItems(obj: ObjectEntity, dto: ObjectDTO) = coroutineScope {
        dto.tags.forEach {
            launch { tagService.saveTag(it, obj.id) }
        }
    }

    private suspend fun updateObject(
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
            )
        ).awaitSingle().toDTO()
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
