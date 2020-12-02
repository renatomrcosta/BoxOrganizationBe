package com.xunfos.boxorganizationbe.service

import com.xunfos.boxorganizationbe.dto.BoxDTO
import com.xunfos.boxorganizationbe.entity.Box
import com.xunfos.boxorganizationbe.repository.BoxRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class BoxService(
    private val boxRepository: BoxRepository,
    private val objectService: ObjectService,
) {
    suspend fun getAllBoxesByUserId(userId: UUID): Flow<BoxDTO> =
        boxRepository
            .findAllByUserId(userId)
            .map { it.toDTO() }

    suspend fun Box.toDTO(): BoxDTO {
        val items = objectService.findAllByBoxId(this.id)
        return BoxDTO(
            id = this.id,
            name = this.name,
            qrCode = this.qrCode,
            items = items.toList(),
        )
    }
}



