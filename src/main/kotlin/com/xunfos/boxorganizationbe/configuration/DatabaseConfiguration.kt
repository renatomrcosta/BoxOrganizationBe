package com.xunfos.boxorganizationbe.configuration

import com.xunfos.boxorganizationbe.repository.BoxRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.springframework.context.annotation.Configuration
import java.util.UUID

@Configuration
class DatabaseConfiguration(
    val boxRepository: BoxRepository
) {
    init {
        runBlocking {
            val results = boxRepository.findAllByUserId(UUID.fromString("528d9c30-7c42-4e73-bdd1-f3869f233dca"))
            println("Hey ya")
            results.collect { println(it) }
        }
    }
}
