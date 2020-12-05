package com.xunfos.boxorganizationbe.controller

import com.xunfos.boxorganizationbe.dto.BoxDTO
import com.xunfos.boxorganizationbe.service.BoxService
import kotlinx.coroutines.flow.Flow
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
) {
    @GetMapping("/box")
    suspend fun list(): Flow<BoxDTO> {
        // TODO - Authorize by userId
        val userId = UUID.fromString("2fbd2774-06ec-4de7-b657-9617bd9971c5")
        return boxService.getAllBoxesByUserId(userId = userId)
    }

    @PostMapping("/box")
    suspend fun add(@RequestBody dto: BoxDTO): BoxDTO {
        // TODO - Authorize by userId
        val userId = UUID.fromString("2fbd2774-06ec-4de7-b657-9617bd9971c5")
        return boxService.addBox(userId, dto)
    }

    @PutMapping("/box")
    suspend fun update(@RequestBody dto: BoxDTO): BoxDTO {
        // TODO - Authorize by userId
        val userId = UUID.fromString("2fbd2774-06ec-4de7-b657-9617bd9971c5")
        return boxService.updateBox(userId, dto)
    }

    @DeleteMapping("/box")
    suspend fun delete(@RequestBody dto: BoxDTO): Unit {
        // TODO - Authorize by userId
        val userId = UUID.fromString("2fbd2774-06ec-4de7-b657-9617bd9971c5")
        return boxService.removeBox(dto)
    }
}
