package com.xunfos.boxorganizationbe.controller

import com.xunfos.boxorganizationbe.dto.BoxDTO
import com.xunfos.boxorganizationbe.dto.ObjectDTO
import com.xunfos.boxorganizationbe.service.SearchService
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
class SearchController(
    private val searchService: SearchService,
    @Value("\${user.id}")
    private val userId: UUID,
) {
    @GetMapping("/search")
    suspend fun search(@RequestParam query: String): SearchDTO = searchService.search(userId, query)
}

data class SearchDTO(
    val boxes: List<BoxDTO>,
    val objects: List<ObjectDTO>,
)
