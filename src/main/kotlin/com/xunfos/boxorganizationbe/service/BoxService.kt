package com.xunfos.boxorganizationbe.service

import com.xunfos.boxorganizationbe.dto.BoxDTO
import com.xunfos.boxorganizationbe.entity.Box
import com.xunfos.boxorganizationbe.repository.BoxRepository
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.reactive.awaitSingle
import kotlinx.coroutines.reactive.awaitSingleOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Transactional
@Service
class BoxService(
    private val boxRepository: BoxRepository,
    private val objectService: ObjectService,
) {
    suspend fun getById(userId: UUID, id: UUID): BoxDTO =
        boxRepository.getByUserIdAndId(userId = userId, id = id).toDTO()

    suspend fun getAllBoxesByUserId(userId: UUID): Flow<BoxDTO> =
        boxRepository
            .findAllByUserId(userId)
            .map { it.toDTO() }

    suspend fun find(userId: UUID, query: String): Flow<BoxDTO> =
        boxRepository.find(userId, query).map { it.toDTO() }

    suspend fun addBox(userId: UUID, dto: BoxDTO): BoxDTO = coroutineScope {
        val box = boxRepository.save(
            Box(
                id = UUID.randomUUID(),
                userId = userId,
                name = dto.name,
                qrCode = dto.qrCode,
            ).apply { isNew = true }
        ).awaitFirst()

        handleItems(dto, userId, box)

        box.toDTO()
    }

    suspend fun updateBox(userId: UUID, dto: BoxDTO): BoxDTO = coroutineScope {
        val box = boxRepository.findById(dto.id ?: error("invalid id to update")).awaitSingle()
        boxRepository.save(
            Box(
                id = box.id,
                userId = userId,
                name = dto.name,
                qrCode = dto.qrCode,
            )
        ).awaitFirst()

        handleItems(dto, userId, box)

        box.toDTO()
    }

    suspend fun removeBox(dto: BoxDTO) {
        val id = dto.id ?: error("invalid null uuid for update")
        val box = boxRepository.findById(id).awaitSingle()
        // TODO add a cascade to the DB itself?
        boxRepository.delete(box).awaitSingleOrNull()
    }

    private suspend fun handleItems(
        dto: BoxDTO,
        userId: UUID,
        box: Box,
    ) = coroutineScope {
        dto.objects.forEach {
            launch { objectService.saveObject(it, userId, box.id) }
        }
    }

    suspend fun Box.toDTO(): BoxDTO {
        val items = objectService.findAllByBoxId(this.id)
        return BoxDTO(
            id = this.id,
            name = this.name,
            qrCode = this.qrCode,
            objects = items.toList(),
        )
    }
}



