package com.xunfos.boxorganizationbe.controller

import com.xunfos.boxorganizationbe.dto.BoxDTO
import com.xunfos.boxorganizationbe.service.BoxService
import kotlinx.coroutines.flow.Flow
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
class BoxController(
    private val boxService: BoxService,
    @Value("\${user.id}")
    private val userId: UUID,
) {
    @GetMapping("/box")
    suspend fun list(): Flow<BoxDTO> {
        return boxService.getAllBoxesByUserId(userId = userId)
    }

    @PostMapping("/box")
    suspend fun add(@RequestBody dto: BoxDTO): BoxDTO {
        return boxService.addBox(userId, dto)
    }

    @PutMapping("/box")
    suspend fun update(@RequestBody dto: BoxDTO): BoxDTO {
        return boxService.updateBox(userId, dto)
    }

    @DeleteMapping("/box")
    suspend fun delete(@RequestBody dto: BoxDTO): Unit {
        return boxService.removeBox(dto)
    }
}
