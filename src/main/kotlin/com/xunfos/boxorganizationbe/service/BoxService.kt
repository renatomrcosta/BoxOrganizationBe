package com.xunfos.boxorganizationbe.service

import com.xunfos.boxorganizationbe.dto.BoxDTO
import com.xunfos.boxorganizationbe.entity.Box
import com.xunfos.boxorganizationbe.repository.BoxRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
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
    suspend fun getAllBoxesByUserId(userId: UUID): Flow<BoxDTO> =
        boxRepository
            .findAllByUserId(userId)
            .map { it.toDTO() }

    suspend fun addBox(userId: UUID, dto: BoxDTO): BoxDTO =
        boxRepository.save(
            Box(
                id = UUID.randomUUID(),
                userId = userId,
                name = dto.name,
                qrCode = dto.qrCode,
            ).apply { isNew = true }
        ).awaitFirst().toDTO()

    suspend fun updateBox(userId: UUID, dto: BoxDTO): BoxDTO {
        val box = boxRepository.findById(dto.id ?: error("invalid id to update")).awaitSingle()
        return boxRepository.save(
            Box(
                id = box.id,
                userId = userId,
                name = dto.name,
                qrCode = dto.qrCode,
            )
        ).awaitFirst().toDTO()
    }

    suspend fun removeBox(dto: BoxDTO) {
        val id = dto.id ?: error("invalid null uuid for update")
        val box = boxRepository.findById(id).awaitSingle()
        boxRepository.delete(box).awaitSingleOrNull()
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



