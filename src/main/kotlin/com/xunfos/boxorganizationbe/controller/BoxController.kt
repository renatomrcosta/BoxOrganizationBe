package com.xunfos.boxorganizationbe.controller

import com.xunfos.boxorganizationbe.dto.BoxDTO
import com.xunfos.boxorganizationbe.service.BoxService
import kotlinx.coroutines.flow.Flow
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
class BoxController(
    private val boxService: BoxService,
) {
    @GetMapping("/box")
    suspend fun list(): Flow<BoxDTO> {
        // TODO - Authorize by userId
        val userId = UUID.fromString("528d9c30-7c42-4e73-bdd1-f3869f233dca")
        return boxService.getAllBoxesByUserId(userId = userId)
    }
}
