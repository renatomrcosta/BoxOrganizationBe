package com.xunfos.boxorganizationbe.service

import com.xunfos.boxorganizationbe.controller.SearchDTO
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.toList
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class SearchService(
    private val boxService: BoxService,
    private val objectService: ObjectService,
) {
    suspend fun search(userId: UUID, query: String): SearchDTO = coroutineScope {
        val boxes = async { boxService.find(userId, query) }
        val objects = async { objectService.find(userId, query) }

        SearchDTO(
            boxes = boxes.await().toList(),
            objects = objects.await().toList(),
        )
    }
}
