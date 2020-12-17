package com.xunfos.boxorganizationbe.controller

import com.xunfos.boxorganizationbe.dto.ObjectDTO
import com.xunfos.boxorganizationbe.service.ObjectService
import kotlinx.coroutines.flow.Flow
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
class ObjectController(
    private val objectService: ObjectService,
    @Value("\${user.id}")
    private val userId: UUID,
) {
    @GetMapping("/object")
    suspend fun list(): Flow<ObjectDTO> {
        return objectService.getAllObjectsByUserId(userId = userId)
    }

    @GetMapping("/object/{id}")
    suspend fun get(@PathVariable id: UUID): ObjectDTO {
        return objectService.getById(userId = userId, id = id)
    }

    @PostMapping("/object") // To add objects without boxes
    suspend fun add(@RequestBody dto: ObjectDTO): ObjectDTO {
        return objectService.saveObject(objectDTO = dto, userId = userId, containerId = null)
    }

    @PutMapping("/object") // To update boxless objects too
    suspend fun update(@RequestBody dto: ObjectDTO): ObjectDTO {
        return objectService.saveObject(objectDTO = dto, userId = userId, containerId = null)
    }

    @DeleteMapping("/object")
    suspend fun delete(@RequestBody dto: ObjectDTO): Unit {
        return objectService.removeObject(dto)
    }
}
